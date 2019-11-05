package com.test.uiview;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.R;
import com.test.base.BaseMultiStateFragment;
import com.test.base.OnRetryCallback;
import com.test.customview.MultiStateView;
import com.test.uiview.login.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseMultiStateFragment implements OnRetryCallback {


    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    Unbinder unbinder;
    @BindView(R.id.multistateview)
    MultiStateView multistateview;

    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        showProgressBar(loginViewModel.getProgressBar());
        showMessage(view, loginViewModel.getMessage());

        setViewState(loginViewModel.getViewState(), multistateview, this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login)
    public void onLoginViewClicked() {

        String email = getString(mEmail);
        String password = getString(mPassword);
        loginViewModel.onLogin(email, password);


    }


    private String getString(EditText editText) {


        return !TextUtils.isEmpty(editText.getText().toString()) ? editText.getText().toString() : "";

    }


    @Override
    public void OnRetry() {


        showContent(multistateview);


    }
}
