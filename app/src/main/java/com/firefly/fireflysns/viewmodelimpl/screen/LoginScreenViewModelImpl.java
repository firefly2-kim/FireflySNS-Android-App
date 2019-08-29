package com.firefly.fireflysns.viewmodelimpl.screen;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.firefly.fireflysns.component.MainActivity;
import com.firefly.fireflysns.model.FirestoreRepository;
import com.firefly.fireflysns.view.LoginView;
import com.firefly.fireflysns.view.ToastView;
import com.firefly.fireflysns.viewmodel.screen.LoginScreenViewModel;
import com.firefly.fireflysns.viewmodel.usecase.LoginUsecaseExecutor;

import java.lang.ref.WeakReference;

/**
 * @author firefly2.kim
 * @since 19. 8. 23
 */
public class LoginScreenViewModelImpl extends ViewModel implements LoginScreenViewModel {

    private static final int REQ_CODE_SIGN_IN = 1;
    private WeakReference<Activity> mActivityRef;

    // View
    private ToastView mToastView;

    // Model
    private FirestoreRepository mFirestoreRepository;

    // LiveData
    private LoginUsecaseExecutor mLoginUsecaseExecutor;

    public LoginScreenViewModelImpl() {
    }

    @Override
    public void setParentContext(Activity parentContext) {
        mActivityRef = new WeakReference<>(parentContext);
    }

    @Override
    public void setFirestoreRepository(FirestoreRepository repository) {
        mFirestoreRepository = repository;
    }

    @Override
    public void setLoginUsecaseExecutor(LoginUsecaseExecutor executor) {
        executor.setFirestoreRepository(mFirestoreRepository);
        mLoginUsecaseExecutor = executor;
    }

    @Override
    public void setToastView(ToastView view) {
        mToastView = view;
    }

    @Override
    public void setLoginView(LoginView view) {
        view.setActionListener(this);
        view.setFirebaseUserLiveData(mLoginUsecaseExecutor.getUserLiveData());
        view.setThrowableUserLiveData(mLoginUsecaseExecutor.getThrowableLiveData());
    }

    @Override
    public void loadUserData() {
        mLoginUsecaseExecutor.loadUserData();
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_SIGN_IN) {
            mLoginUsecaseExecutor.firebaseAuthWithGoogle(data, mActivityRef.get());
            return true;
        }
        return false;
    }

    @Override
    public void onNotifySignInSuccess() {
        if (mActivityRef.get() == null) {
            return;
        }
        Intent intent = new Intent(mActivityRef.get(), MainActivity.class);
        mActivityRef.get().startActivity(intent);
        finishActivity();
    }

    @Override
    public void onRequestedSignIn() {
        Intent signInIntent = mLoginUsecaseExecutor.getSignInIntent();
        if (mActivityRef.get() != null) {
            mActivityRef.get().startActivityForResult(signInIntent, REQ_CODE_SIGN_IN);
        }
    }

    @Override
    public void onRenderToast(String msg) {
        if (mActivityRef.get() != null) {
            mToastView.render(mActivityRef.get().getApplicationContext(), msg);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mActivityRef = null;
    }

    private void finishActivity() {
        if (mActivityRef.get() != null) {
            mActivityRef.get().finish();
        }
    }
}
