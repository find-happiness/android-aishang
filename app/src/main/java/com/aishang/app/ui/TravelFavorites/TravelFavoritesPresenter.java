package com.aishang.app.ui.TravelFavorites;

import android.util.Log;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.model.JCollectResult;
import com.aishang.app.data.model.JNewsListResult;
import com.aishang.app.data.model.JParticipationReslut;
import com.aishang.app.data.model.JReleaseResult;
import com.aishang.app.data.model.MyTravel;
import com.aishang.app.ui.base.BasePresenter;
import com.aishang.app.util.Constants;
import com.aishang.app.util.NetWorkType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by song on 2016/3/13.
 */
public class TravelFavoritesPresenter extends BasePresenter<TravelFavoritesMvpView> {
  private static final String TAG = "TravelDetailPresenter";

  private final DataManager mDataManager;
  private Subscription mCollectSubscription;
  private Subscription mReleaseSubscription;
  private Subscription mParticipationSubscription;

  @Inject public TravelFavoritesPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attachView(TravelFavoritesMvpView mvpView) {
    super.attachView(mvpView);
  }

  @Override public void detachView() {
    super.detachView();
    if (mCollectSubscription != null) mCollectSubscription.unsubscribe();
    if (mReleaseSubscription != null) mReleaseSubscription.unsubscribe();
    if (mParticipationSubscription != null) mParticipationSubscription.unsubscribe();
  }

  public void loadTravelCollect(int version, String json, NetWorkType type) {
    loadTravelCollect(false, version, json, type);
  }

  public void loadTravelCollect(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mCollectSubscription != null && !mCollectSubscription.isUnsubscribed()) {
      mCollectSubscription.unsubscribe();
    }

    mCollectSubscription = mDataManager.syncTravelCollect(version, json)
        .map(new Func1<JCollectResult, AdapterContentModel>() {
          @Override public AdapterContentModel call(JCollectResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              List<MyTravel> myTravels = new ArrayList<>();
              for (JCollectResult.ListCollectEntity entity : result.getListCollect()) {
                MyTravel travel =
                    new MyTravel(entity.getTitle(), entity.getShortDesc(), entity.getImageUrl(),
                        entity.getCreateDate(), entity.getHits(), entity.getSupports(),
                        entity.getStaticUrl(), entity.getID(), entity.getAuthorID());

                myTravels.add(travel);
              }

              return new AdapterContentModel(result.getTotalCount(), myTravels);
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
              return null;
            }
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<AdapterContentModel>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            Log.e(TAG, "loadTravelParticipation onError: " + e.toString());
          }

          @Override public void onNext(AdapterContentModel myTravels) {

            if (myTravels == null) {
              return;
            }

            if (myTravels.getMyTravels().size() <= 0 && type == NetWorkType.refresh) {
              getMvpView().showEmpty();
            } else {
              switch (type) {
                case refresh:
                  getMvpView().refreshListCollect(myTravels.getMyTravels(), myTravels.getTotal());
                  break;
                case loadMore:
                  getMvpView().loadMoreListCollect(myTravels.getMyTravels(), myTravels.getTotal());
                  break;
              }
            }
          }
        });
  }

  public void loadTravelRelease(int version, String json, NetWorkType type) {
    loadTravelRelease(false, version, json, type);
  }

  public void loadTravelRelease(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mReleaseSubscription != null && !mReleaseSubscription.isUnsubscribed()) {
      mReleaseSubscription.unsubscribe();
    }

    mReleaseSubscription = mDataManager.syncTravelRelease(version, json)
        .map(new Func1<JReleaseResult, AdapterContentModel>() {
          @Override public AdapterContentModel call(JReleaseResult result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              List<MyTravel> myTravels = new ArrayList<>();
              for (JReleaseResult.ListNewsEntity entity : result.getListNews()) {
                MyTravel travel =
                    new MyTravel(entity.getTitle(), entity.getShortDesc(), entity.getImageUrl(),
                        entity.getCreateDate(), entity.getHits(), entity.getSupports(),
                        entity.getStaticUrl(), entity.getID(), entity.getAuthorID());

                myTravels.add(travel);
              }

              return new AdapterContentModel(result.getTotalCount(), myTravels);
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
              return null;
            }
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<AdapterContentModel>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            Log.e(TAG, "loadTravelParticipation onError: " + e.toString());
          }

          @Override public void onNext(AdapterContentModel myTravels) {

            if (myTravels == null) {
              return;
            }

            if (myTravels.getMyTravels().size() <= 0) {
              getMvpView().showEmpty();
            } else {
              switch (type) {
                case refresh:
                  getMvpView().refreshListRelease(myTravels.getMyTravels(), myTravels.getTotal());
                  break;
                case loadMore:
                  getMvpView().loadMoreListRelease(myTravels.getMyTravels(), myTravels.getTotal());
                  break;
              }
            }
          }
        });
  }

  public void loadTravelParticipation(int version, String json, NetWorkType type) {
    loadTravelParticipation(false, version, json, type);
  }

  public void loadTravelParticipation(boolean allowMemoryCacheVersion, int version, String json,
      final NetWorkType type) {
    checkViewAttached();

    if (mParticipationSubscription != null && !mParticipationSubscription.isUnsubscribed()) {
      mParticipationSubscription.unsubscribe();
    }

    mParticipationSubscription = mDataManager.syncTravelParticipation(version, json)
        .map(new Func1<JParticipationReslut, AdapterContentModel>() {
          @Override public AdapterContentModel call(JParticipationReslut result) {
            if (result.getResult().toUpperCase().equals(Constants.RESULT_SUCCESS.toUpperCase())) {
              List<MyTravel> myTravels = new ArrayList<>();
              for (JParticipationReslut.ListparticipationEntity entity : result.getListparticipation()) {
                MyTravel travel =
                    new MyTravel(entity.getTitle(), entity.getShortDesc(), entity.getImageUrl(),
                        entity.getCreateDate(), entity.getHits(), entity.getSupports(),
                        entity.getStaticUrl(), entity.getID(), entity.getAuthorID());

                myTravels.add(travel);
              }

              return new AdapterContentModel(result.getTotalCount(), myTravels);
            } else {
              getMvpView().showError(result.getResult());
              //getMvpView().showEmpty();
              return null;
            }
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<AdapterContentModel>() {
          @Override public void onCompleted() {
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError("网络异常");
            Log.e(TAG, "loadTravelParticipation onError: " + e.toString());
          }

          @Override public void onNext(AdapterContentModel myTravels) {

            if (myTravels == null) return;

            if (myTravels.getMyTravels().size() <= 0 && type == NetWorkType.refresh) {
              getMvpView().showEmpty();
            } else {
              switch (type) {
                case refresh:
                  getMvpView().refreshListParticipation(myTravels.getMyTravels(),
                      myTravels.getTotal());
                  break;
                case loadMore:
                  getMvpView().loadMoreListParticipation(myTravels.getMyTravels(),
                      myTravels.getTotal());
                  break;
              }
            }
          }
        });
  }
}
