package com.firefly.fireflysns.viewimpl;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.firefly.fireflysns.R;
import com.firefly.fireflysns.common.Logger;
import com.firefly.fireflysns.view.LoginView;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public class LoginViewImpl implements LoginView {

    private static final String TAG = LoginViewImpl.class.getSimpleName();

    private View mMainView;
    private LifecycleOwner mLifecycleOwner;
    private ActionListener mActionListener;

    public LoginViewImpl(View view, LifecycleOwner lifecycleOwner) {
        mMainView = view;
        mLifecycleOwner = lifecycleOwner;

        SignInButton signInButton = mMainView.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(v -> mActionListener.onRequestedSignIn());
    }

    @Override
    public void setFirebaseUserLiveData(LiveData<FirebaseUser> liveData) {
        liveData.observe(mLifecycleOwner, data -> {
            if (liveData.getValue() == null) {
                showLoginButton();
                return;
            }
            mActionListener.onNotifySignInSuccess();
        });
    }

    @Override
    public void setThrowableUserLiveData(LiveData<Throwable> liveData) {
        liveData.observe(mLifecycleOwner, data -> {
            Logger.e(TAG, data.getMessage());
            mActionListener.onRenderToast(data.getMessage());
            showLoginButton();
        });
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }

    private void showLoginButton() {
        mMainView.setVisibility(View.VISIBLE);
    }
}
