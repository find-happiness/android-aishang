package com.aishang.app.util;

import com.aishang.app.ui.base.MvpView;

/**
 * Created by song on 2016/2/23.
 */
public interface FileUploadMvpView extends MvpView {
  void showUploadFileError(String error);

  void showUploadFileSuccess();
}
