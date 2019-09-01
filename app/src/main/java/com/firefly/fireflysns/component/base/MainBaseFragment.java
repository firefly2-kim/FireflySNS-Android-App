package com.firefly.fireflysns.component.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.firefly.fireflysns.component.MainActivityActionListener;

import javax.annotation.Nullable;

/**
 * @author firefly2.kim
 * @since 19. 9. 1
 */
public class MainBaseFragment extends Fragment {

    @Nullable
    protected MainActivityActionListener mMainActivityActionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MainActivityActionListener) {
            mMainActivityActionListener = (MainActivityActionListener) getActivity();
        }
    }
}
