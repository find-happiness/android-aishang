package com.aishang.app.ui.MyBuyAndSale;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.data.model.JRentalListResult;
import com.aishang.app.util.AiShangUtil;
import com.aishang.app.util.CommonUtil;
import com.aishang.app.util.NetWorkType;
import com.aishang.app.util.NetworkUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.shizhefei.fragment.LazyFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link SaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SaleFragment extends LazyFragment implements RentSaleMvpView{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;
    @Bind(R.id.avloadingIndicatorView) AVLoadingIndicatorView avloadingIndicatorView;
    @Bind(R.id.no_data_in_sale) TextView noData;
    @Bind(R.id.layoutRoot) FrameLayout layoutRoot;
    @Inject RentSalePresenter presenter;
    @Inject RentAdapter adapter;
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SaleFragment.
     */
    public static SaleFragment newInstance() {
        SaleFragment fragment = new SaleFragment();
        return fragment;
    }
    public SaleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BuyAndSaleActivity) getActivity()).getActivityComponent().inject(this);
        presenter.attachView(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_sale);

        ButterKnife.bind(this, this.getContentView());
        initRefreshLayout();
        proLoad();
    }

    @Override public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Override public void onDetach() {
        super.onDetach();
    }

    @Override public void showError(String error) {
        CommonUtil.showSnackbar(error, layoutRoot);
    }

    @Override public void refreshList(List<JRentalListResult.RentalItem> items) {
        if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
            avloadingIndicatorView.setVisibility(View.GONE);
        }

        if (noData.getVisibility() == View.VISIBLE) {
            noData.setVisibility(View.GONE);
        }

        adapter.getItems().clear();
        adapter.getItems().addAll(items);
        adapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();
    }

    @Override public void showEmpty() {
        if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
            avloadingIndicatorView.setVisibility(View.GONE);
        }

        if (noData.getVisibility() != View.VISIBLE) {
            noData.setVisibility(View.VISIBLE);
        }

        mRecyclerView.refreshComplete();
        adapter.getItems().clear();
        adapter.notifyDataSetChanged();
    }

    @Override public void loadMoreList(List<JRentalListResult.RentalItem> items, int total) {

        if (noData.getVisibility() == View.VISIBLE) {
            noData.setVisibility(View.GONE);
        }

        mRecyclerView.loadMoreComplete();
        adapter.getItems().addAll(items);
        adapter.notifyDataSetChanged();
        mRecyclerView.refreshComplete();

        if (adapter.getItemCount() >= total) {
            adapter.notifyDataSetChanged();
            mRecyclerView.loadMoreComplete();
        }
    }

    private void asynRental(NetWorkType type) {
        asynRental(1, "", 0, 10, 0, 0, 0, 0, 0, 0, type);
    }

    private void asynRental(int filterType, String filterWords, int recStart, int recCount,
        int fZoneID, int fPriceCatID, float fPriceMin, float fPriceMax, int fRoomTypeID, int beDetail,
        NetWorkType type) {

        String json =
            AiShangUtil.generRentalListParam(filterType, filterWords, recStart, recCount, fZoneID,
                fPriceCatID, fPriceMin, fPriceMax, fRoomTypeID, beDetail);

        presenter.loadRentSale(1, json, type);
    }

    private void initRefreshLayout() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(
            new HorizontalDividerItemDecoration.Builder(this.getActivity()).colorResId(
                android.R.color.darker_gray).sizeResId(R.dimen.divider).build());
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallScaleRipple);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        //mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        //
        //View header = LayoutInflater.from(this)
        //    .inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content),
        //        false);
        // mRecyclerView.addHeaderView(header);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override public void onRefresh() {
                if (NetworkUtil.isNetworkConnected(SaleFragment.this.getActivity())) {
                    //asynBusiness(NetWorkType.refresh);
                } else {
                    mRecyclerView.refreshComplete();
                    CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
                }
            }

            @Override public void onLoadMore() {
                if (NetworkUtil.isNetworkConnected(SaleFragment.this.getActivity())) {
                    //asynBusiness(0, "", "", adapter.getItems().size(), 10, "asc", 0, "",
                    //    NetWorkType.loadMore);
                } else {
                    //mRecyclerView.loadMoreComplete();
                    mRecyclerView.cancelLoadMore();
                    CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void proLoad() {
        if (NetworkUtil.isNetworkConnected(this.getActivity())) {
            if (avloadingIndicatorView.getVisibility() != View.VISIBLE) {
                avloadingIndicatorView.setVisibility(View.VISIBLE);
            }

            if (noData.getVisibility() == View.VISIBLE) {
                noData.setVisibility(View.GONE);
            }

            asynRental(NetWorkType.refresh);
        } else {

            if (avloadingIndicatorView.getVisibility() == View.VISIBLE) {
                avloadingIndicatorView.setVisibility(View.GONE);
            }

            if (noData.getVisibility() == View.VISIBLE) {
                noData.setVisibility(View.GONE);
            }
            CommonUtil.showSnackbar(R.string.no_net, layoutRoot);
        }
    }
}
