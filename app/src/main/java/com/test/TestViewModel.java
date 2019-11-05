package com.test;

import android.os.Handler;

import com.test.base.BaseViewModel;
import com.test.base.MultiStateViewShow;


public class TestViewModel extends BaseViewModel {


    public void showTest() {


        setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.LOADING));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.CONENT));
            }
        }, 3000);

    }

    public void showTest1() {


        showProgressBar(true);

        setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.LOADING));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                showProgressBar(false);
                setViewState(new MultiStateViewShow(MultiStateViewShow.ViewState.ERRORWITHRETRY, null));
            }
        }, 3000);

    }

}
