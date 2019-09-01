package com.firefly.fireflysns.viewmodel.usecase;

import androidx.lifecycle.LiveData;

import com.firefly.fireflysns.model.FirestoreRepository;
import com.firefly.fireflysns.model.entity.UserInfoModel;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public interface FetchMyProfileUsecaseExecutor {

    void setFirebaseRepository(FirestoreRepository repository);

    LiveData<UserInfoModel> getUserInfoLiveData();

    void fetchProfileData();
}
