package com.firefly.fireflysns.component;

import androidx.lifecycle.LiveData;

import com.firefly.fireflysns.model.entity.UserInfoModel;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public interface MainActivityActionListener {
    LiveData<UserInfoModel> getUserInfoLiveData();
}
