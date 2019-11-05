package com.test.base;

public interface NetworkCallBack {

    void showNoInternet(boolean isMultiStateViewEnabled);

    void showTimeOut(boolean isMultiStateViewEnabled);

    void showNetworkError(String s, boolean isMultiStateViewEnabled);

    void parseNetworkError(String s, boolean isMultiStateViewEnabled);

}
