package com.firefly.fireflysns.viewmodelimpl.screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.firefly.fireflysns.model.entity.UserInfoModel;
import com.firefly.fireflysns.view.FeedCreationLauncherView;
import com.firefly.fireflysns.viewmodel.screen.FeedScreenViewModel;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public class FeedScreenViewModelImpl extends ViewModel implements FeedScreenViewModel {

    private FeedCreationLauncherView mFeedCreationLauncherView;

    @Override
    public void setFeedCreationLauncherView(FeedCreationLauncherView view) {
        view.setActionListener(this);
        mFeedCreationLauncherView = view;
    }

    @Override
    public void setUserProfileLiveData(LiveData<UserInfoModel> liveData) {
        mFeedCreationLauncherView.setUserProfileLiveData(liveData);
    }

    @Override
    public void launchNewPostActivity() {

    }

    @Override
    public void launchNewImageActivity() {

    }
}
