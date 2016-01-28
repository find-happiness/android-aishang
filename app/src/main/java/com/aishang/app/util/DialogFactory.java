package com.aishang.app.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import android.widget.ListAdapter;
import com.aishang.app.R;
import java.util.List;

public final class DialogFactory {

  public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setPositiveButton(R.string.dialog_action_ok, null);

    return alertDialog.create();
  }

  public static Dialog createSimpleOkErrorDialog(Context context, @StringRes int titleResource,
      @StringRes int messageResource) {

    return createSimpleOkErrorDialog(context, context.getString(titleResource),
        context.getString(messageResource));
  }

  public static Dialog createSimpleDialog(Context context, String title, String message,
      String positiveText, String negativeText, DialogInterface.OnClickListener okClick,
      DialogInterface.OnClickListener cancelClick) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveText, okClick)
        .setNegativeButton(negativeText, cancelClick);

    return alertDialog.create();
  }

  public static Dialog createSimpleDialog(Context context, @StringRes int title,
      @StringRes int message, @StringRes int positiveText, @StringRes int negativeText,
      DialogInterface.OnClickListener okClick, DialogInterface.OnClickListener cancelClick) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveText, okClick)
        .setNegativeButton(negativeText, cancelClick);

    return alertDialog.create();
  }

  public static Dialog createGenericErrorDialog(Context context, String message) {
    AlertDialog.Builder alertDialog =
        new AlertDialog.Builder(context).setTitle(context.getString(R.string.dialog_error_title))
            .setMessage(message)
            .setPositiveButton(R.string.dialog_action_ok, null);
    return alertDialog.create();
  }

  public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
    return createGenericErrorDialog(context, context.getString(messageResource));
  }

  public static ProgressDialog createProgressDialog(Context context, String message) {
    ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message);
    return progressDialog;
  }

  public static ProgressDialog createProgressDialog(Context context,
      @StringRes int messageResource) {
    return createProgressDialog(context, context.getString(messageResource));
  }

  public static Dialog createSingleChoiceDialog(Context context, String[] items, int checkedItem,
      DialogInterface.OnClickListener itemClickLister, String title) {

    AlertDialog.Builder alertDialog =
        new AlertDialog.Builder(context).setSingleChoiceItems(items, checkedItem, itemClickLister)
            .setTitle(title);
    return alertDialog.create();
  }

  public static Dialog createSingleChoiceDialog(Context context, @StringRes int items,
      int checkedItem, DialogInterface.OnClickListener itemClickLister, @StringRes int title) {

    AlertDialog.Builder alertDialog =
        new AlertDialog.Builder(context).setSingleChoiceItems(items, checkedItem, itemClickLister)
            .setTitle(title);
    return alertDialog.create();
  }
}
