package com.firefly.fireflysns.viewmodelimpl.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.firefly.fireflysns.model.FirestoreRepository;
import com.firefly.fireflysns.model.entity.UserInfoModel;
import com.firefly.fireflysns.viewmodel.usecase.FetchMyProfileUsecaseExecutor;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public class FetchMyProfileUsecaseExecutorImpl implements FetchMyProfileUsecaseExecutor {

    private MutableLiveData<UserInfoModel> mUserInfoLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> mThrowableLiveData = new MutableLiveData<>();
    private FirestoreRepository mFirestoreRepository;

    @Override
    public void setFirebaseRepository(FirestoreRepository repository) {
        mFirestoreRepository = repository;
    }

    @Override
    public LiveData<UserInfoModel> getUserInfoLiveData() {
        return mUserInfoLiveData;
    }

    @Override
    public void fetchProfileData() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        mFirestoreRepository.fetchUserInfo(auth.getCurrentUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfo -> mUserInfoLiveData.setValue(userInfo),
                        thr -> mThrowableLiveData.setValue(thr));
    }
}
