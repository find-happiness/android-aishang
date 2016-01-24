package com.aishang.app.injection.component;

import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.aishang.app.ui.TravelList.TravelListActivity;
import com.aishang.app.ui.hotel.HotelListActivity;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.aishang.app.ui.login.LoginActivity;
import dagger.Component;
import com.aishang.app.injection.PerActivity;
import com.aishang.app.injection.module.ActivityModule;
import com.aishang.app.ui.insale.InSaleActivity;
import com.aishang.app.ui.main.MainActivity;
import com.aishang.app.ui.main.main.MainFmFragment;
import com.aishang.app.ui.main.mine.MineFragment;
import com.aishang.app.ui.main.more.MoreFragment;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(MainFmFragment mainFmFragment);
    void inject(InSaleActivity inSaleActivity);
    void inject(InSaleDetailActivity activity);
    void inject(HotelListActivity activity);
    void inject(HotelDetailActivity activity);
    void inject(LoginActivity activity);
    void inject(TravelListActivity activity);
    void inject(MineFragment fragment);
    void inject(MoreFragment fragment);
}
