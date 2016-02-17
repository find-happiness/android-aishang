package com.aishang.app.injection.component;

import com.aishang.app.ui.BrokerCenter.BrokerCenterActivity;
import com.aishang.app.ui.CashWithDrawApply.CashWithDrawApplyActivity;
import com.aishang.app.ui.CashWithDrawApply.CashWithDrawFragment;
import com.aishang.app.ui.HotelDetail.HotelDetailActivity;
import com.aishang.app.ui.KanFanTuan.KanFanTuanActivity;
import com.aishang.app.ui.MyBuyAndSale.RentFragment;
import com.aishang.app.ui.MyBuyAndSale.SaleFragment;
import com.aishang.app.ui.MyHouse.MyHouseActivity;
import com.aishang.app.ui.MyOrder.MyOrderActivity;
import com.aishang.app.ui.RecommenCustomer.RecommenCustomerActivity;
import com.aishang.app.ui.TravelFavorites.FavoritesFragment;
import com.aishang.app.ui.TravelList.TravelListActivity;
import com.aishang.app.ui.hotel.HotelListActivity;
import com.aishang.app.ui.insaleDetail.InSaleDetailActivity;
import com.aishang.app.ui.login.LoginActivity;
import com.aishang.app.ui.register.RegisterActivity;
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
@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
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

  void inject(RegisterActivity activity);

  void inject(MyOrderActivity activity);

  void inject(BrokerCenterActivity activity);

  void inject(RecommenCustomerActivity activity);

  void inject(MyHouseActivity activity);

  void inject(CashWithDrawApplyActivity activity);

  void inject(CashWithDrawFragment fragment);

  void inject(FavoritesFragment fragment);

  void inject(RentFragment fragment);

  void inject(SaleFragment fragment);

  void inject(KanFanTuanActivity activity);
}
