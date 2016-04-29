package com.aishang.app.ui.TravelList;

import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JSysZoneResult;
import com.aishang.app.data.model.News;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func5;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/2/17.
 */
public class TravelListPresenter extends BasePresenter<TravelListMvpView> {
  private static final String TAG = "TravelListPresenter";

  private final DataManager mDataManager;
  private Subscription mSubscription;
  private Subscription mZoneSubscription;

  @Inject public TravelListPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(TravelListMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mSubscription != null) mSubscription.unsubscribe();
    if (mZoneSubscription != null) mZoneSubscription.unsubscribe();
  }

  public void loadTravel(int version, String json, NetWorkType type) {
    loadTravel(false, version, json, type);
  }

  public void loadTravel(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }

    mSubscription = mDataManager.syncTravel(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JNewsListResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            //getMvpView().showError(e.getMessage());
            System.out.print(e.toString());
          }

          @Override public void onNext(final JNewsListResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {

              if (result.getNewsList().size() <= 0 && type == NetWorkType.refresh) {
                getMvpView().showEmpty();
              } else {

                Observable.zip(Observable.from(result.getNewsList()),
                    Observable.from(result.getUserImageUrlList()),
                    Observable.from(result.getZoneName()),
                    Observable.from(result.getEnshrinedCountList()),
                    Observable.from(result.getUserNameList()),
                    new Func5<JNewsListResult.NewsListEntity, String, String, Integer, String, News>() {
                      @Override public News call(JNewsListResult.NewsListEntity newsListEntity,
                          String userImage, String zoneName, Integer enshrinedCount,
                          String userName) {
                        return new News(newsListEntity, enshrinedCount, userImage, zoneName,
                            userName);
                      }
                    }).toList().subscribe(new Subscriber<List<News>>() {
                  @Override public void onCompleted() {

                  }

                  @Override public void onError(Throwable e) {

                  }

                  @Override public void onNext(List<News> newses) {
                    switch (type) {
                      case refresh:
                        getMvpView().refreshList(newses);
                        break;
                      case loadMore:
                        getMvpView().loadMoreList(new ArrayList<News>(newses),
                            result.getTotalCount());
                        break;
                    }
                  }
                });
              }
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
            }
          }
        });
  }

  public void loadZone(boolean allowMemoryCacheVersion, int version, String json) {
    checkViewAttached();

    if (mZoneSubscription != null && !mZoneSubscription.isUnsubscribed()) {
      mZoneSubscription.unsubscribe();
    }

    mZoneSubscription = mDataManager.sysZone(version, json)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<JSysZoneResult>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            //getMvpView().showError(e.getMessage());
            System.out.print(e.toString());
          }

          @Override public void onNext(JSysZoneResult zoneResult) {
            if (zoneResult.getResult()
                .toUpperCase()
                .equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              getMvpView().showSysZoneDialog(
                  new ArrayList<JSysZoneResult.Zone>(Arrays.asList(zoneResult.getZoneList())));
            } else {
              getMvpView().showError(zoneResult.getResult());
            }
          }
        });
  }
}
