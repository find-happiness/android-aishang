package com.aishang.app.injection.component;

import dagger.Component;
import com.aishang.app.injection.PerActivity;
import com.aishang.app.injection.module.ActivityModule;
import com.aishang.app.ui.insale.InSaleActivity;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.ui.main.main.MainFmFragment;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(MainFmFragment mainFmFragment);
    void inject(InSaleActivity inSaleActivity);
}
