package com.test.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.test.BuildConfig;

public abstract class BaseFragment extends Fragment implements BaseView {

    private static final String TAG = "BaseFragment";

    private BaseActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            baseActivity = (BaseActivity) context;
        } catch (ClassCastException e) {

            Log.e(TAG, "onAttach: " + e.getMessage());
        }

    }

    @Override
    public void showToast(String msg) {

        if (baseActivity != null) baseActivity.showToast(msg);
        else showActivityError();


    }

    @Override
    public void showToast(int id) {

        if (baseActivity != null) baseActivity.showToast(id);
        else showActivityError();

    }

    @Override
    public void showSnackBar(View view, String msg) {

        if (baseActivity != null) baseActivity.showSnackBar(view, msg);
        else showActivityError();
    }

    @Override
    public void showSnackBar(View view, int id) {

        if (baseActivity != null) baseActivity.showSnackBar(view, id);
        else showActivityError();
    }

    @Override
    public void showSnackBarWithAction(View view, String actionTitle, String msg, View.OnClickListener onClickListener) {

        if (baseActivity != null)
            baseActivity.showSnackBarWithAction(view, actionTitle, msg, onClickListener);
        else showActivityError();

    }

    @Override
    public void showProgressBar(String title) {

        if (baseActivity != null) baseActivity.showProgressBar(title);
        else showActivityError();

    }

    @Override
    public void showProgressBar() {

        if (baseActivity != null) baseActivity.showProgressBar();
        else showActivityError();

    }

    @Override
    public void hideProgressBar() {

        if (baseActivity != null) baseActivity.hideProgressBar();
        else showActivityError();

    }

    @Override
    public void showLogDebug(String msg) {

        if (baseActivity != null) baseActivity.showLogDebug(msg);
        else showActivityError();

    }

    @Override
    public void showLogDebug(int id) {

        if (baseActivity != null) baseActivity.showLogDebug(id);
        else showActivityError();

    }

    @Override
    public void showLogError(String msg) {

        if (baseActivity != null) baseActivity.showLogError(msg);
        else showActivityError();

    }

    @Override
    public void showLogError(int id) {

        if (baseActivity != null) baseActivity.showLogError(id);
        else showActivityError();

    }


    private void showActivityError() {

        if (BuildConfig.DEBUG)
            Log.e(TAG, "Please check fragement's activity is extends BaseActivity");
    }
}
