package com.aishang.app.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.BoilerplateApplication;
import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginResult;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.Constants;
import com.aishang.app.util.DialogFactory;
import com.aishang.app.util.RegexUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import javax.inject.Inject;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class LoginActivity extends BaseActivity
    implements LoginMvpView, PasswordFragment.OnPasswordFragmentInteractionListener,
    VerificationCodeFragment.OnVerificationCodeFragmentInteractionListener {
  private static final String TAG = "LoginActivity";
  @Inject LoginPresenter presenter;
  @Bind(R.id.tl_login) TabLayout tabLayout;
  @Bind(R.id.vp_content) ViewPager viewPager;
  @Bind(R.id.layoutRoot) CoordinatorLayout layoutRoot;
  @Bind(R.id.toolbar) Toolbar toolbar;
  ProgressDialog progress;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.getActivityComponent().inject(this);
    BoilerplateApplication.get(this).getComponent().inject(this);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    presenter.attachView(this);
    initView();
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  private void initView() {
    initToolbar();
    initTabLayout();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  private void initTabLayout() {
    SimpleFragmentPagerAdapter adapter =
        new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    tabLayout.setTabsFromPagerAdapter(adapter);
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
  }

  @Override public void showError(String error) {
    //Log.i(TAG, "showError: " + error);
    DialogFactory.createSimpleOkErrorDialog(this, "SORRY", error).show();
  }

  @Override public void loginScuess(JMemberLoginResult result) {
    //Log.i(TAG, "loginScuess: " + "loginScuess");
    //presenter.intentToMain();

    BoilerplateApplication.get(this).setMemberLoginResult(result);
    onBackPressed();
  }

  /**
   * 普通用户和CRM用户登录失败后统一的的回调函数，根据错误做相应的处理
   */
  public void loginFaild(String errorStr) {
    CommonUtil.hideSoftInput(this);
    if (Constants.RESULT_MBNOTEXISTORPSWERROR.equals(errorStr)) {// 用户名或密码错误

      CommonUtil.showSnackbar(R.string.login_nameorpsw_wrong, layoutRoot);
    } else if (Constants.RESULT_MBLOCKED.equals(errorStr)) {// 用户被锁定

      // Toast.makeText(this, R.string.login_memberLocked,
      // Toast.LENGTH_LONG)
      // .show();
      CommonUtil.showSnackbar(R.string.login_memberLocked, layoutRoot);
    } else if (Constants.RESULT_MBNOTACTIVED.equals(errorStr)) {
      CommonUtil.showSnackbar(R.string.login_memberNotActived, layoutRoot);
      // Toast.makeText(this, R.string.login_memberNotActived,
      // Toast.LENGTH_LONG).show();
    } else if (Constants.RESULT_MBCHANGEPSW.equals(errorStr)) {
      //intentToChangePsw();
      Snackbar.make(layoutRoot, R.string.login_changePassword, Snackbar.LENGTH_LONG)
          .setAction(R.string.dialog_action_ok, new View.OnClickListener() {
            @Override public void onClick(View v) {
              presenter.intentToChangePsw();
            }
          })
          .setDuration(Snackbar.LENGTH_LONG)
          .show();
    } else {
      //BaseHelper.toastError(this, errorStr);
      Log.i(TAG, "loginFaild: " + errorStr);
      CommonUtil.showSnackbar(errorStr, layoutRoot);
    }
  }

  @Override public void saveLoginData(String phone, String psw) {
    BoilerplateApplication.get(this).setMemberAccount(phone);
    BoilerplateApplication.get(this).setMemberPsw(psw);
  }

  @Override public void showGetVerificationSuccess() {
    CommonUtil.hideSoftInput(this);
    CommonUtil.showSnackbar("短信已发送，请注意查收", layoutRoot);
  }

  @Override public void dismissDialog() {
    if (progress != null && progress.isShowing()) {
      progress.dismiss();
    }
  }

  @Override public void showNetDialog() {
    progress = DialogFactory.createProgressDialog(this, R.string.listview_loading);
    progress.show();
  }

  @Override public void onPswLogin(String phone, String psw) {
    CommonUtil.hideSoftInput(this);
    presenter.Login(2, AiShangUtil.gennerLogin(psw, phone));
  }

  @Override public void onVerificationCodeLogin(final String strPhone, final String code) {
    CommonUtil.hideSoftInput(this);
    if (TextUtils.isEmpty(strPhone) || !RegexUtils.checkMobile(strPhone)) {
      CommonUtil.showSnackbar(R.string.error_phone, layoutRoot);
      return;
    }

    if (TextUtils.isEmpty(code)) {
      CommonUtil.showSnackbar("验证码不能为空", layoutRoot);
      return;
    }

    Observable.from(
        ((MemoryCookieStore) OkHttpUtils.getInstance().getOkHttpClient().cookieJar()).get(
            HttpUrl.parse(AiShangService.AiShangHost + "mobile/member/sendCode.ashx")))
        .map(new Func1<Cookie, String>() {
          @Override public String call(Cookie cookie) {
            if (cookie == null) {
              new IllegalArgumentException("验证码有误，请重新获取！");
            }
            return cookie.toString();
          }
        })
        .subscribe(new Subscriber<String>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            CommonUtil.showSnackbar(e.toString(), layoutRoot);
          }

          @Override public void onNext(String s) {
            presenter.codeLogin(2, AiShangUtil.generCodeLoginParam(strPhone, code), s);
          }
        });
  }

  @Override public void onRegister() {
    presenter.intentToRegister();
  }

  public void getVerificationCode(String strPhone) {
    CommonUtil.hideSoftInput(this);
    if (TextUtils.isEmpty(strPhone) || !RegexUtils.checkMobile(strPhone)) {
      CommonUtil.showSnackbar(R.string.error_phone, layoutRoot);
    } else {
      clearSession();
      presenter.getVerificationCode(1, AiShangUtil.generSendCodeParam(strPhone, "2", true));
    }
  }

  public void clearSession() {
    CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
    if (cookieJar instanceof CookieJarImpl) {
      ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
    }
  }

  @Override public void onForgetPsw() {
    presenter.intentToForgetPsw();
  }

  public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "密码登录", "验证码登录" };
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
      super(fm);
      this.context = context;
    }

    @Override public Fragment getItem(int position) {

      switch (position) {
        case 0:
          return PasswordFragment.newInstance("", "");
        case 1:
          return VerificationCodeFragment.newInstance("", "");
      }
      return null;
    }

    @Override public int getCount() {
      return PAGE_COUNT;
    }

    @Override public CharSequence getPageTitle(int position) {
      return tabTitles[position];
    }
  }
}
