package com.aishang.app.ui.MemberGiftcard;

import com.aishang.app.data.model.JMemberBankAccount;
import com.aishang.app.data.model.JMemberGiftcardResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/16.
 */
public interface MemberGiftcarMvpView extends MvpView {
  void showError(String error);

  void loadMemberGiftcardSuccess(JMemberGiftcardResult result);

  void loadMemberGiftcardEmpty();
}
