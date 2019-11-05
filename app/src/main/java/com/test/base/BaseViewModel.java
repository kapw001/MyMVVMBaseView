package com.test.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {


    private MutableLiveData<Boolean> isShowProgressBar;

    private MutableLiveData<MultiStateViewShow> viewState;


    public LiveData<Boolean> getProgressBar() {

        if (isShowProgressBar == null) isShowProgressBar = new MutableLiveData<>();

        return isShowProgressBar;
    }

    public LiveData<MultiStateViewShow> getViewState() {

        if (viewState == null) viewState = new MutableLiveData<>();

        return viewState;
    }

    public void showProgressBar(boolean isShow) {

        isShowProgressBar.setValue(isShow);

    }

    public void setViewState(MultiStateViewShow multiStateViewShow) {

        viewState.setValue(multiStateViewShow);

    }


}
