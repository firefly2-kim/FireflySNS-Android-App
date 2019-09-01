package com.firefly.fireflysns.injection;

import android.app.Activity;

import com.firefly.fireflysns.viewmodel.usecase.FetchMyProfileUsecaseExecutor;
import com.firefly.fireflysns.viewmodel.usecase.LoginUsecaseExecutor;
import com.firefly.fireflysns.viewmodelimpl.usecase.FetchMyProfileUsecaseExecutorImpl;
import com.firefly.fireflysns.viewmodelimpl.usecase.LoginUsecaseExecutorImpl;

/**
 * @author firefly2.kim
 * @since 19. 8. 25
 */
public class ViewModelInjection {

    private ViewModelInjection() {
        // No instance
    }

    public static LoginUsecaseExecutor provideLoginUsecaseExecutor(Activity activity) {
        return new LoginUsecaseExecutorImpl(activity);
    }

    public static FetchMyProfileUsecaseExecutor provideFetchMyProfileUsecaseExecutor() {
        return new FetchMyProfileUsecaseExecutorImpl();
    }
}
