package com.aishang.app.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aishang.app.BoilerplateApplication;
import com.aishang.app.injection.component.ActivityComponent;
import com.aishang.app.injection.component.DaggerActivityComponent;
import com.aishang.app.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
