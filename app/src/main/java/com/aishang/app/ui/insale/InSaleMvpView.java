package com.aishang.app.ui.insale;

import com.aishang.app.data.model.JHotelListResult;
import com.aishang.app.data.model.JHotelPriceCatListResult;
import com.aishang.app.data.model.JLoupanPriceCatListResult;
import com.aishang.app.data.model.JLoupanProductCatListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.LoupanProduct;
import com.aishang.app.ui.base.MvpView;

import java.util.List;

/**
 * Created by song on 2016/1/18.
 */
public interface InSaleMvpView extends MvpView {
    void refreshLoupanProduct(List<LoupanProduct> loupanProducts);

    void loadMoreLoupanProduct(List<LoupanProduct> loupanProducts, int total);

    void showSysZoneDialog(List<JSysZoneResult.Zone> zones);

    void showSysPriceDialog(List<JLoupanPriceCatListResult.Cat> prices);

    void showLoupanProductCatDialog(List<JLoupanProductCatListResult.Cat> cats);

    void showError(String error);

    void showLoupanProductEmpty();
}
