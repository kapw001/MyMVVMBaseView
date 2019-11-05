package com.test.uiview.login;

import android.arch.lifecycle.MutableLiveData;
import com.test.base.BaseViewModel;
import com.test.common.CallbackWrapper;
import com.test.network.RetrofitAdapter;
import com.test.network.request.LoginRequest;
import com.test.network.response.LoginResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoggedIn;


    public MutableLiveData<Boolean> getIsLoggedIn() {

        if (isLoggedIn == null) isLoggedIn = new MutableLiveData<>();

        return isLoggedIn;
    }


    public void onLogin(String email, String password) {

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);


        addDisposable(RetrofitAdapter.getUserApiServiceClient()
                .login(loginRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<LoginResponse>(this, true) {
                    @Override
                    protected void onSuccess(LoginResponse response) {

                        setMessage(response.getMessage());

                    }
                }));

    }



}
