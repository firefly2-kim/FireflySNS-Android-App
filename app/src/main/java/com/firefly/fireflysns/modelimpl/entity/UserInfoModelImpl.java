package com.firefly.fireflysns.modelimpl.entity;

import com.firefly.fireflysns.model.entity.UserInfoModel;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public class UserInfoModelImpl implements UserInfoModel {

    private String photoUrl;

    @Override
    public void setPhotoUrl(String url) {
        photoUrl = url;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }
}
