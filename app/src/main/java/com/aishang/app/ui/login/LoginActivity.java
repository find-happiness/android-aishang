package com.aishang.app.ui.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.aishang.app.R;
import com.aishang.app.data.model.JMemberLoginParam;
import com.aishang.app.ui.base.BaseActivity;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.DialogFactory;
import com.google.gson.Gson;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginMvpView,
        PasswordFragment.OnPasswordFragmentInteractionListener,
        VerificationCodeFragment.OnVerificationCodeFragmentInteractionListener {

    private static final String TAG = "LoginActivity";

    @Inject
    LoginPresenter presenter;

    @Bind(R.id.tl_login)
    TabLayout tabLayout;

    @Bind(R.id.vp_content)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.attachView(this);
        initTabLayout();
    }

    private void initTabLayout() {
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public void login() {

        JMemberLoginParam param = new JMemberLoginParam();
        param.setMemberAccount("13883224451");
        param.setPassword(CommonUtil.getEncodeMD5("224451"));
        presenter.Login(2, new Gson().toJson(param));
    }

    @Override
    public void showError(String error) {
        Log.i(TAG, "showError: " + error);
        DialogFactory.createSimpleOkErrorDialog(this, "SORRY", error).show();
    }

    @Override
    public void loginScuess() {
        Log.i(TAG, "loginScuess: " + "loginScuess");
        presenter.intentToMain();
    }

    @Override
    public void onPswLogin(String phone, String psw) {

    }

    @Override
    public void onVerificationCodeLogin(String phone, String code) {

    }

    @Override
    public void onRegister() {
        presenter.intentToRegister();
    }

    @Override
    public void onForgetPsw() {
        presenter.intentToForgetPsw();
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 2;
        private String tabTitles[] = new String[]{"密码登录", "验证码登录"};
        private Context context;

        public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return PasswordFragment.newInstance("", "");
                case 1:
                    return VerificationCodeFragment.newInstance("", "");
            }
            return null;

        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
