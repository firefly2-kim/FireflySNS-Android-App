package com.firefly.fireflysns.viewimpl;

import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bumptech.glide.Glide;
import com.firefly.fireflysns.R;
import com.firefly.fireflysns.model.entity.UserInfoModel;
import com.firefly.fireflysns.view.FeedCreationLauncherView;

import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public class FeedCreationLauncherViewImpl implements FeedCreationLauncherView {

    private View mView;
    private LifecycleOwner mLifecycleOwner;
    private ActionListener mActionListener;

    public FeedCreationLauncherViewImpl(View view, LifecycleOwner lifecycleOwner) {
        mView = view;
        mLifecycleOwner = lifecycleOwner;
    }

    @Override
    public void setUserProfileLiveData(LiveData<UserInfoModel> liveData) {
        liveData.observe(mLifecycleOwner, data -> {
            ImageView profileImg = mView.findViewById(R.id.profile_img);

            int borderSize = (int) mView.getResources().getDimension(R.dimen.common_profile_img_border);
            int borderColor = ContextCompat.getColor(mView.getContext(), R.color.secondary_text_color_black);
            Glide.with(mView.getContext())
                    .load(data.getPhotoUrl())
                    .transform(new CropCircleWithBorderTransformation(borderSize, borderColor))
                    .into(profileImg);
        });
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }
}
