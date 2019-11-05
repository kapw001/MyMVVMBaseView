package com.test.base;

import android.support.annotation.StringRes;
import android.view.View;

public interface BaseView {

    void showToast(String msg);

    void showToast(@StringRes int id);

    void showSnackBar(View view, String msg);

    void showSnackBar(View view, @StringRes int id);

    void showSnackBarWithAction(View view, String actionTitle, String msg, View.OnClickListener onClickListener);

    void showProgressBar(String title);

    void showProgressBar();

    void hideProgressBar();

    void showLogDebug(String msg);

    void showLogDebug(@StringRes int id);

    void showLogError(String msg);

    void showLogError(@StringRes int id);

}
