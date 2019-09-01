package com.firefly.fireflysns.injection;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.firefly.fireflysns.view.FeedCreationLauncherView;
import com.firefly.fireflysns.view.LoginView;
import com.firefly.fireflysns.view.ToastView;
import com.firefly.fireflysns.viewimpl.FeedCreationLauncherViewImpl;
import com.firefly.fireflysns.viewimpl.LoginViewImpl;
import com.firefly.fireflysns.viewimpl.ToastViewImpl;

/**
 * @author firefly2.kim
 * @since 19. 8. 25
 */
public class ViewInjection {

    private ViewInjection() {
        // No instance
    }

    public static LoginView provideLoginView(View view, LifecycleOwner lifecycleOwner) {
        return new LoginViewImpl(view, lifecycleOwner);
    }

    public static ToastView provideToastView() {
        return new ToastViewImpl();
    }

    public static FeedCreationLauncherView provideFeedCreationLauncherView(View view, LifecycleOwner lifecycleOwner) {
        return new FeedCreationLauncherViewImpl(view, lifecycleOwner);
    }
}
