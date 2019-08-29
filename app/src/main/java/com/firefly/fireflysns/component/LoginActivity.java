package com.firefly.fireflysns.component;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.firefly.fireflysns.R;
import com.firefly.fireflysns.component.base.BaseActivity;
import com.firefly.fireflysns.injection.ModelInjection;
import com.firefly.fireflysns.injection.ViewInjection;
import com.firefly.fireflysns.injection.ViewModelInjection;
import com.firefly.fireflysns.view.LoginView;
import com.firefly.fireflysns.viewmodel.screen.LoginScreenViewModel;
import com.firefly.fireflysns.viewmodelimpl.screen.LoginScreenViewModelImpl;

public class LoginActivity extends BaseActivity {

    private LoginScreenViewModel mScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mScreenViewModel = ViewModelProviders.of(this).get(LoginScreenViewModelImpl.class);
        mScreenViewModel.setParentContext(this);

        injectModel(mScreenViewModel);
        injectViewModel(mScreenViewModel);
        injectView(mScreenViewModel);
    }

    private void injectModel(LoginScreenViewModel viewModel) {
        viewModel.setFirestoreRepository(ModelInjection.provideFirestoreRepository());
    }

    private void injectViewModel(LoginScreenViewModel viewModel) {
        viewModel.setLoginUsecaseExecutor(ViewModelInjection.provideLoginUsecaseExecutor(this));
    }

    private void injectView(LoginScreenViewModel viewModel) {
        viewModel.setToastView(ViewInjection.provideToastView());

        LoginView loginView = ViewInjection.provideLoginView(findViewById(R.id.login_trigger_container), this);
        viewModel.setLoginView(loginView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mScreenViewModel.loadUserData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mScreenViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
