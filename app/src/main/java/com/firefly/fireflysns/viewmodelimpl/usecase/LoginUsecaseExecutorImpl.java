package com.firefly.fireflysns.viewmodelimpl.usecase;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.firefly.fireflysns.R;
import com.firefly.fireflysns.model.FirestoreRepository;
import com.firefly.fireflysns.viewmodel.usecase.LoginUsecaseExecutor;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author firefly2.kim
 * @since 19. 8. 23
 */
public class LoginUsecaseExecutorImpl implements LoginUsecaseExecutor {

    private MutableLiveData<FirebaseUser> mFirebaseUserLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> mThrowableLiveData = new MutableLiveData<>();
    private FirestoreRepository mFirestoreRepository;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    public LoginUsecaseExecutorImpl(Activity activity) {
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }

    @Override
    public void setFirestoreRepository(FirestoreRepository repository) {
        mFirestoreRepository = repository;
    }

    @Override
    public void loadUserData() {
        FirebaseUser user = mAuth.getCurrentUser();
        mFirebaseUserLiveData.setValue(user);
    }

    @Override
    public LiveData<FirebaseUser> getUserLiveData() {
        return mFirebaseUserLiveData;
    }

    @Override
    public LiveData<Throwable> getThrowableLiveData() {
        return mThrowableLiveData;
    }

    @Override
    public void firebaseAuthWithGoogle(Intent data, Activity activity) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account;
        try {
            account = task.getResult(ApiException.class);
        } catch (ApiException e) {
            e.printStackTrace();
            mThrowableLiveData.setValue(e);
            return;
        }
        if (account == null) {
            mThrowableLiveData.setValue(new NullPointerException("Failed to retrieve a GoogleSignInAccount"));
            return;
        }
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(activity, paramTask -> {
            if (paramTask.isSuccessful()) {
                uploadUserInfoToRemoteDb();
                return;
            }
            mThrowableLiveData.setValue(task.getException());
        });
    }

    @Override
    public Intent getSignInIntent() {
        return mGoogleSignInClient.getSignInIntent();
    }

    private void uploadUserInfoToRemoteDb() {
        mFirestoreRepository.addUserIfNotExists(mAuth.getCurrentUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(unused -> loadUserData(), thr -> mThrowableLiveData.setValue(thr));
    }
}
