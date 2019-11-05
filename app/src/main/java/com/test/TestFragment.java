package com.test;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.base.BaseFragment;
import com.test.base.BaseMultiStateFragment;
import com.test.base.MultiStateViewShow;
import com.test.base.OnRetryCallback;
import com.test.customview.MultiStateView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseMultiStateFragment {


    public TestFragment() {
        // Required empty public constructor
    }

    private TestViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        final MultiStateView multiStateView = view.findViewById(R.id.multistateview);

        showProgressBar(viewModel.getProgressBar());

        setViewState(viewModel.getViewState(), multiStateView, null);

        Button snackbar = view.findViewById(R.id.snackbar_action);

        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSnackBarWithAction(v, "Ok", getString(R.string.app_name), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showSnackBar(v, R.string.app_finished);

                    }
                });

            }
        });

        Button button = view.findViewById(R.id.test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.showTest();


            }
        });


        Button button1 = view.findViewById(R.id.test1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewModel.showTest1();


            }
        });

    }

    @Override
    public void OnRetry() {

//        showToast("On Retry Called");

        MultiStateView view = (MultiStateView) getView();

        Button multiStateView = null;
        if (view != null) {
//            multiStateView = view.findViewById(R.id.test);
//
//            showToast(multiStateView.getText().toString());
            showContent(view);
        } else {

            showToast("No");

        }


    }
}
