package com.test.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.test.R;
import com.test.customview.MultiStateView;

public abstract class BaseMultiStateActivity extends BaseActivity implements BaseMultiStateView {


    @Override
    public void showLoading(MultiStateView view) {
        if (view != null) view.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void showErrorMsg(MultiStateView view, String msg) {

        if (view != null) {

            view.setViewState(MultiStateView.VIEW_STATE_ERROR);
            View errorView = view.getView(MultiStateView.VIEW_STATE_ERROR);
            if (errorView != null) {
                TextView eMsgView = errorView.findViewById(R.id.errorMsg);
                Button retry = errorView.findViewById(R.id.retry);
                eMsgView.setVisibility(View.VISIBLE);
//            retry.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onRetryOrCallApi();
//                }
//            });
                if (!TextUtils.isEmpty(msg))
                    eMsgView.setText(msg);
            }


        } else showLogError("MultistateView not initialized");

    }

    @Override
    public void showErrorMsg(MultiStateView view, String msg, final OnRetryCallback onRetryCallback) {

        if (view != null) {

            view.setViewState(MultiStateView.VIEW_STATE_ERROR);
            View errorView = view.getView(MultiStateView.VIEW_STATE_ERROR);
            if (errorView != null) {
                TextView eMsgView = errorView.findViewById(R.id.errorMsg);
                Button retry = errorView.findViewById(R.id.retry);
                eMsgView.setVisibility(View.VISIBLE);
                retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onRetryCallback != null) onRetryCallback.OnRetry();else showLogError("OnRetryCallBack not initialized in activity or fragment");
                    }
                });
                if (!TextUtils.isEmpty(msg))
                    eMsgView.setText(msg);
            }


        } else showLogError("MultistateView not initialized");

    }

    @Override
    public void showEmptyMsg(MultiStateView view, String msg) {


        if (view != null) {
            view.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            View emptyView = view.getView(MultiStateView.VIEW_STATE_EMPTY);
            if (emptyView != null) {
                TextView empty = view.findViewById(R.id.emptyMsg);
                if (!TextUtils.isEmpty(msg))
                    empty.setText(msg);
            }
        } else showLogError("MultistateView not initialized");

    }

    @Override
    public void showContent(MultiStateView view) {

        if (view != null) view.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        else showLogError("MultistateView not initialized");

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
    public void setViewState(final LiveData<MultiStateViewShow> viewState, final MultiStateView multiStateView, final OnRetryCallback onRetryCallback) {

        viewState.observe(this, new Observer<MultiStateViewShow>() {
            @Override
            public void onChanged(@Nullable MultiStateViewShow multiStateViewShow) {

                if (multiStateViewShow != null) {
                    switch (multiStateViewShow.getViewState()) {

                        case EMPTY:

                            showEmptyMsg(multiStateView, multiStateViewShow.getMsg());

                            break;

                        case CONENT:

                            showContent(multiStateView);

                            break;

                        case ERROR:

                            showErrorMsg(multiStateView, multiStateViewShow.getMsg());

                            break;

                        case ERRORWITHRETRY:

                            showErrorMsg(multiStateView, multiStateViewShow.getMsg(), onRetryCallback);

                            break;

                        case LOADING:

                            showLoading(multiStateView);

                            break;

                    }
                }
            }
        });

    }


}
