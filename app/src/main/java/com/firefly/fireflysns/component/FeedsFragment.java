package com.firefly.fireflysns.component;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.firefly.fireflysns.R;
import com.firefly.fireflysns.component.base.MainBaseFragment;
import com.firefly.fireflysns.injection.ViewInjection;
import com.firefly.fireflysns.view.FeedCreationLauncherView;
import com.firefly.fireflysns.viewmodel.screen.FeedScreenViewModel;
import com.firefly.fireflysns.viewmodelimpl.screen.FeedScreenViewModelImpl;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public class FeedsFragment extends MainBaseFragment {

    private FeedScreenViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedScreenViewModelImpl.class);

        // view
        injectView(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mMainActivityActionListener != null) {
            mViewModel.setUserProfileLiveData(mMainActivityActionListener.getUserInfoLiveData());
        }
    }

    private void injectView(View view) {
        View creationView = view.findViewById(R.id.create_post_container);
        mViewModel.setFeedCreationLauncherView(ViewInjection.provideFeedCreationLauncherView(creationView, this));
    }
}
