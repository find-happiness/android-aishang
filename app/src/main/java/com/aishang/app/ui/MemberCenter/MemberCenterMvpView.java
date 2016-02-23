package com.aishang.app.ui.MemberCenter;

import com.aishang.app.data.model.JMemberProfileEditResult;
import com.aishang.app.data.model.JMemberProfileResult;
import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/15.
 */
public interface MemberCenterMvpView extends MvpView {
  void showError(String error);

  //JMemberProfileResult
  void showGetProfileSuccess(JMemberProfileResult profile);

  void postSuccess(JMemberProfileEditResult result);

  void showUploadFileError(String error);

  void showUploadFileSuccess();
}
