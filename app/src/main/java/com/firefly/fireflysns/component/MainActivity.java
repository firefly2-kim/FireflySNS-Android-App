package com.firefly.fireflysns.component;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.firefly.fireflysns.R;
import com.firefly.fireflysns.component.base.BaseActivity;
import com.firefly.fireflysns.injection.ModelInjection;
import com.firefly.fireflysns.injection.ViewModelInjection;
import com.firefly.fireflysns.model.entity.UserInfoModel;
import com.firefly.fireflysns.viewmodel.usecase.FetchMyProfileUsecaseExecutor;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public class MainActivity extends BaseActivity implements MainActivityActionListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        injectViewModel();
        mMainViewModel.loadUserData();

        initView();
    }

    @Override
    public LiveData<UserInfoModel> getUserInfoLiveData() {
        return mMainViewModel.getUserLiveData();
    }

    private void injectViewModel() {
        FetchMyProfileUsecaseExecutor fetchMyProfileUsecaseExecutor = ViewModelInjection.provideFetchMyProfileUsecaseExecutor();
        fetchMyProfileUsecaseExecutor.setFirebaseRepository(ModelInjection.provideFirestoreRepository());
        mMainViewModel.setFetchMyProfileUsecaseExecutor(fetchMyProfileUsecaseExecutor);
    }

    private void initView() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.feeds, FeedsFragment.class)
                .add(R.string.friends, FriendsFragment.class)
                .add(R.string.profile, MyProfileFragment.class)
                .create());

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

    public static class MainViewModel extends ViewModel {
        private FetchMyProfileUsecaseExecutor mFetchMyProfileUsecaseExecutor;

        public void setFetchMyProfileUsecaseExecutor(FetchMyProfileUsecaseExecutor executor) {
            mFetchMyProfileUsecaseExecutor = executor;
        }

        public void loadUserData() {
            mFetchMyProfileUsecaseExecutor.fetchProfileData();
        }

        public LiveData<UserInfoModel> getUserLiveData() {
            return mFetchMyProfileUsecaseExecutor.getUserInfoLiveData();
        }
    }
}
