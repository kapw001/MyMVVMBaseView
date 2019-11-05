package com.test.base;

import android.arch.lifecycle.LiveData;

import com.test.customview.MultiStateView;

import java.util.List;

public interface BaseMultiStateView {

    void showLoading(MultiStateView view);

    void showContent(MultiStateView view);

    void showErrorMsg(MultiStateView view, String msg);

    void showErrorMsg(MultiStateView view, String msg, OnRetryCallback onRetryCallback);

    void showEmptyMsg(MultiStateView view, String msg);

    void showProgressBar(LiveData<Boolean> liveData);

    void setViewState(LiveData<MultiStateViewShow> viewState, MultiStateView multiStateView, OnRetryCallback onRetryCallback);


}
