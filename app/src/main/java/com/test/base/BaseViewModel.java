package com.test.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.test.common.CallbackWrapper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel implements NetworkCallBack {

    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<Boolean> isShowProgressBar;

    private MutableLiveData<MultiStateViewShow> viewState;

    private MutableLiveData<String> message;


    public LiveData<String> getMessage() {

        if (message == null) message = new MutableLiveData<>();

        return message;
    }

    public LiveData<Boolean> getProgressBar() {

        if (isShowProgressBar == null) isShowProgressBar = new MutableLiveData<>();

        return isShowProgressBar;
    }

    public LiveData<MultiStateViewShow> getViewState() {

        if (viewState == null) viewState = new MutableLiveData<>();

        return viewState;
    }

    public void setMessage(String msg) {

        message.setValue(msg);
    }

    public void showProgressBar(boolean isShow) {

        if (isShowProgressBar != null) isShowProgressBar.setValue(isShow);

    }

    public void setViewState(MultiStateViewShow multiStateViewShow) {

        if (viewState != null) viewState.setValue(multiStateViewShow);

    }


    public void addDisposable(Disposable disposable) {

        this.disposable.add(disposable);

    }

    @Override
    public void showNoInternet(boolean isMultiStateViewEnabled) {

        String msg = "No internet connection";

        if (isMultiStateViewEnabled)
            setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.ERRORWITHRETRY, msg));
        else
            setMessage(msg);

    }

    @Override
    public void showTimeOut(boolean isMultiStateViewEnabled) {

        String msg = "TimeOut";

        if (isMultiStateViewEnabled)
            setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.ERRORWITHRETRY, msg));
        else
            setMessage(msg);


    }

    @Override
    public void showNetworkError(String s, boolean isMultiStateViewEnabled) {

        if (isMultiStateViewEnabled)
            setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.ERRORWITHRETRY, s));
        else
            setMessage(s);

    }

    @Override
    public void parseNetworkError(String s, boolean isMultiStateViewEnabled) {

        if (isMultiStateViewEnabled) {

            setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.EMPTY, CallbackWrapper.getErrorMessage(s)));

        } else {

            setMessage(CallbackWrapper.getErrorMessage(s));
        }

    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null) disposable.clear();
    }
}
