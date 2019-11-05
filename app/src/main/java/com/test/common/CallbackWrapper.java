package com.test.common;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.test.base.BaseViewModel;
import com.test.base.MultiStateViewShow;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

public abstract class CallbackWrapper<T> extends DisposableObserver<T> {

    //Reference link https://medium.com/mindorks/rxjava2-and-retrofit2-error-handling-on-a-single-place-8daf720d42d6
    //BaseView is just a reference of a View in MVP
    private WeakReference<BaseViewModel> weakReference;
    private boolean isMultiStateViewEnabled = false;

    public CallbackWrapper(BaseViewModel baseViewModel) {
        this.weakReference = new WeakReference<>(baseViewModel);
        BaseViewModel networkCallBack = weakReference.get();
        networkCallBack.showProgressBar(true);
    }

    public CallbackWrapper(BaseViewModel baseViewModel, boolean isMultiStateViewEnabled) {
        this.weakReference = new WeakReference<>(baseViewModel);
        this.isMultiStateViewEnabled = isMultiStateViewEnabled;
        BaseViewModel networkCallBack = weakReference.get();

        if (isMultiStateViewEnabled)
            networkCallBack.setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.LOADING));
        else
            networkCallBack.showProgressBar(false);

    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        //You can return StatusCodes of different cases from your API and handle it here. I usually include these cases on BaseResponse and iherit it from every Response
        onSuccess(t);
        BaseViewModel networkCallBack = weakReference.get();
        if (isMultiStateViewEnabled)
            networkCallBack.setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.CONENT));
        else networkCallBack.showProgressBar(false);


    }

    @Override
    public void onError(Throwable e) {
        BaseViewModel networkCallBack = weakReference.get();

        if (!isMultiStateViewEnabled)
            networkCallBack.showProgressBar(false);

        if (e instanceof HttpException) {

            try {
                String s = ((HttpException) e).response().errorBody().string();

                if (networkCallBack != null)
                    networkCallBack.parseNetworkError(s, isMultiStateViewEnabled);

            } catch (IOException e1) {
                if (networkCallBack != null)
                    networkCallBack.showNetworkError("Something went wrong", isMultiStateViewEnabled);
            }

        } else if (e instanceof SocketTimeoutException) {

            if (networkCallBack != null)
                networkCallBack.showTimeOut(isMultiStateViewEnabled);

        } else if (e instanceof IOException) {
            if (networkCallBack != null)
                networkCallBack.showNoInternet(isMultiStateViewEnabled);
        } else {

            if (networkCallBack != null)
                networkCallBack.showNetworkError("Something went wrong", isMultiStateViewEnabled);
        }


    }

    @Override
    public void onComplete() {
        BaseViewModel networkCallBack = weakReference.get();
        networkCallBack.showProgressBar(false);
    }

    public static String getErrorMessage(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}