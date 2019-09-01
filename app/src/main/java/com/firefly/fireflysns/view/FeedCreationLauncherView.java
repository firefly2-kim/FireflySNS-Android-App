package com.firefly.fireflysns.view;

import androidx.lifecycle.LiveData;

import com.firefly.fireflysns.model.entity.UserInfoModel;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public interface FeedCreationLauncherView {

    void setUserProfileLiveData(LiveData<UserInfoModel> liveData);

    void setActionListener(ActionListener actionListener);

    interface ActionListener {
        void launchNewPostActivity();

        void launchNewImageActivity();
    }
}
