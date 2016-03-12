package com.aishang.app.util;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.NumberPicker;
import com.aishang.app.R;
import com.happiness.alterview.AlertView;
import com.happiness.alterview.OnItemClickListener;
import java.util.Calendar;

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

  public static Dialog createGenericSuccessDialog(Context context, String message) {
    AlertDialog.Builder alertDialog =
        new AlertDialog.Builder(context).setTitle(context.getString(R.string.dialog_Ok_title))
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

  public static DatePickerDialog createDatePickerDialog(Activity ctx, Calendar calendar,
      DatePickerDialog.OnDateSetListener callback) {

    return new DatePickerDialog(ctx, callback, calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
  }

  public static AlertDialog createNumberPicker(Activity ctx, int number, int maxValue, int minValue,
      String title, final NumberPickerDelegate callback) {
    final NumberPicker numberPicker = new NumberPicker(ctx);
    numberPicker.setMaxValue(maxValue);
    numberPicker.setMinValue(minValue);
    numberPicker.setValue(number);
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx).setView(numberPicker)
        .setTitle(title)
        .setPositiveButton(R.string.dialog_action_ok, new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            callback.OnPick(numberPicker, numberPicker.getValue());
          }
        });
    return alertDialog.create();
  }

  public static final AlertView createIosSheetAlertDialog(Activity ctx, String title,
      String[] sheet, OnItemClickListener callback) {
    return new AlertView(title, null, "取消", null, sheet, ctx, AlertView.Style.ActionSheet,
        callback);
  }

  public static Dialog createSimpleDialog(Context context, @StringRes int titleResource,
      @StringRes int messageResource, DialogInterface.OnClickListener okCallback,
      DialogInterface.OnClickListener cancelCallback) {

    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setTitle(titleResource)
        .setMessage(messageResource)
        .setPositiveButton(R.string.dialog_action_ok, okCallback)
        .setNegativeButton(R.string.dialog_action_cancel, cancelCallback);

    return alertDialog.create();
  }

  //
  //public static TimePickerView createTimePickView(Context ctx) {
  //  //时间选择器
  //  TimePickerView pvTime = new TimePickerView(ctx, TimePickerView.Type.YEAR_MONTH_DAY);
  //  //控制时间范围
  //  //        Calendar calendar = Calendar.getInstance();
  //  //        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
  //  pvTime.setTime(new Date());
  //  pvTime.setCyclic(false);
  //  pvTime.setCancelable(true);
  //  return pvTime;
  //}
  //
  //public static TimePickerView createTimePickView(Context ctx,Date date) {
  //  //时间选择器
  //  TimePickerView pvTime = new TimePickerView(ctx, TimePickerView.Type.YEAR_MONTH_DAY);
  //  //控制时间范围
  //  //        Calendar calendar = Calendar.getInstance();
  //  //        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
  //  pvTime.setTime(date);
  //  pvTime.setCyclic(false);
  //  pvTime.setCancelable(true);
  //  return pvTime;
  //}
}
