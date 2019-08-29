package com.firefly.fireflysns.viewmodel.screen;

import android.app.Activity;
import android.content.Intent;

import com.firefly.fireflysns.model.FirestoreRepository;
import com.firefly.fireflysns.view.LoginView;
import com.firefly.fireflysns.view.ToastView;
import com.firefly.fireflysns.viewmodel.usecase.LoginUsecaseExecutor;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public interface LoginScreenViewModel extends LoginView.ActionListener {

    void setParentContext(Activity parentContext);

    void setFirestoreRepository(FirestoreRepository repository);

    void setLoginUsecaseExecutor(LoginUsecaseExecutor executor);

    void setToastView(ToastView view);

    void setLoginView(LoginView view);

    void loadUserData();

    boolean onActivityResult(int requestCode, int resultCode, Intent data);
}
