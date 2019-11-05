package com.test.base;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.test.BuildConfig;
import com.test.customview.MultiStateView;

public abstract class BaseMultiStateFragment extends BaseFragment implements BaseMultiStateView, OnRetryCallback {

    private static final String TAG = "BaseMultiStateFragment";

    private BaseMultiStateActivity baseActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            baseActivity = (BaseMultiStateActivity) context;
        } catch (ClassCastException e) {

            Log.e(TAG, "onAttach: " + e.getMessage());
        }

    }

    @Override
    public void showLoading(MultiStateView view) {

        if (baseActivity != null) baseActivity.showLoading(view);
        else showActivityError();

    }

    @Override
    public void showContent(MultiStateView view) {

        if (baseActivity != null) baseActivity.showContent(view);
        else showActivityError();

    }

    @Override
    public void showErrorMsg(MultiStateView view, String msg) {

        if (baseActivity != null) baseActivity.showErrorMsg(view, msg);
        else showActivityError();

    }

    @Override
    public void showErrorMsg(MultiStateView view, String msg, OnRetryCallback onRetryCallback) {

        if (baseActivity != null) baseActivity.showErrorMsg(view, msg, onRetryCallback);
        else showActivityError();

    }

    @Override
    public void showEmptyMsg(MultiStateView view, String msg) {

        if (baseActivity != null) baseActivity.showEmptyMsg(view, msg);
        else showActivityError();

    }


    @Override
    public void showProgressBar(LiveData<Boolean> liveData) {

        if (baseActivity != null) baseActivity.showProgressBar(liveData);
        else showActivityError();
    }

    @Override
    public void setViewState(final LiveData<MultiStateViewShow> viewState, final MultiStateView multiStateView, final OnRetryCallback onRetryCallback) {

        if (baseActivity != null)
            baseActivity.setViewState(viewState, multiStateView, onRetryCallback);
        else showActivityError();

    }


    private void showActivityError() {

        if (BuildConfig.DEBUG)
            Log.e(TAG, "Please check fragement's activity is extends BaseActivity");
    }


}
