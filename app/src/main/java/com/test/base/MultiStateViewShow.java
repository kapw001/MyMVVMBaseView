package com.test.base;

public class MultiStateViewShow {

    public enum ViewState {
        LOADING, CONENT, EMPTY, ERROR, ERRORWITHRETRY
    }

    private ViewState viewState;
    private String msg;

    public MultiStateViewShow(ViewState viewState) {
        this.viewState = viewState;
    }

    public MultiStateViewShow(ViewState viewState, String msg) {
        this.viewState = viewState;
        this.msg = msg;
    }

    public ViewState getViewState() {
        return viewState;
    }

    public void setViewState(ViewState viewState) {
        this.viewState = viewState;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
