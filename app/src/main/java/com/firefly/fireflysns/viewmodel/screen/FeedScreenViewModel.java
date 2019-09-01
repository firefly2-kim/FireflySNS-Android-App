package com.firefly.fireflysns.viewmodel.screen;

import androidx.lifecycle.LiveData;

import com.firefly.fireflysns.model.entity.UserInfoModel;
import com.firefly.fireflysns.view.FeedCreationLauncherView;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public interface FeedScreenViewModel extends FeedCreationLauncherView.ActionListener {

    void setFeedCreationLauncherView(FeedCreationLauncherView view);

    void setUserProfileLiveData(LiveData<UserInfoModel> liveData);
}
