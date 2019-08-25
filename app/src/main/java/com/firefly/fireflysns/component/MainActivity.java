package com.firefly.fireflysns.component;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.firefly.fireflysns.R;
import com.firefly.fireflysns.component.base.BaseActivity;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
