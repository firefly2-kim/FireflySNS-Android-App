package com.firefly.fireflysns.viewmodel.usecase;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public interface LoginUsecaseExecutor {

    void loadUserData();

    LiveData<FirebaseUser> getUserLiveData();

    LiveData<Throwable> getThrowableLiveData();

    void firebaseAuthWithGoogle(Intent data, Activity activity);

    Intent getSignInIntent();
}
