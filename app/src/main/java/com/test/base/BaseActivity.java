package com.test.base;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.BuildConfig;
import com.test.utils.ProgressUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private static final String TAG = "BaseActivity";

    private static final String PROGRESS_TITLE = "Loading";

    @Override
    public void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showToast(int id) {

        Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showSnackBar(View view, String msg) {

        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showSnackBar(View view, int id) {

        Snackbar.make(view, getString(id), Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showSnackBarWithAction(View view, String actionTitle, String msg, View.OnClickListener onClickListener) {

        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(actionTitle, onClickListener).show();

    }

    @Override
    public void showProgressBar(String title) {

        if (TextUtils.isEmpty(title)) title = PROGRESS_TITLE;

        ProgressUtils.showProgress(BaseActivity.this, title);

    }

    @Override
    public void showProgressBar() {

        showProgressBar("");

    }

    @Override
    public void hideProgressBar() {

        ProgressUtils.hideProgress();

    }


    @Override
    public void showLogDebug(String msg) {

        if (BuildConfig.DEBUG) Log.d(TAG, "showLogDebug: " + msg);

    }

    @Override
    public void showLogDebug(int id) {

        if (BuildConfig.DEBUG) Log.d(TAG, "showLogDebug: " + getString(id));

    }

    @Override
    public void showLogError(String msg) {

        if (BuildConfig.DEBUG) Log.e(TAG, "showLogError: " + msg);

    }

    @Override
    public void showLogError(int id) {

        if (BuildConfig.DEBUG) Log.e(TAG, "showLogError: " + getString(id));

    }

    @Override
    public void showProgressBar(LiveData<Boolean> liveData) {

        liveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

                if (aBoolean != null && aBoolean) showProgressBar();
                else hideProgressBar();
            }
        });

    }

    @Override
    public void showMessage(final View view, LiveData<String> liveData) {

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                showSnackBar(view, s);
            }
        });

    }

//    @Override
//    public void showNoInternet(boolean isMultiStateViewEnabled) {
//
//        showToast("No internet connection");
//
//    }
//
//    @Override
//    public void showNetworkError(String s, boolean isMultiStateViewEnabled) {
//
//        showToast(s);
//
//    }
//
//    @Override
//    public void showTimeOut(boolean isMultiStateViewEnabled) {
//
//        showToast("Time Out");
//
//    }
}
