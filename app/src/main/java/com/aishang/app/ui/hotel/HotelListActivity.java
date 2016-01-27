package com.aishang.app.ui.hotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class HotelListActivity extends BaseActivity {

  private static final String TAG = "HotelListActivity";
  @Bind(R.id.searchbox) SearchBox search;
  @Bind(R.id.toolbar) Toolbar toolbar;

  @Inject HotelPresenter presenter;
  @Bind(R.id.toolbar_title) TextView toolbarTitle;
  @Bind(R.id.tv_position) TextView tvPosition;
  @Bind(R.id.tv_check_in_date) TextView tvCheckInDate;
  @Bind(R.id.tv_check_out_date) TextView tvCheckOutDate;
  @Bind(R.id.tv_price) TextView tvPrice;
  @Bind(R.id.swipe_refresh) XRecyclerView mRecyclerView;

  private int refreshTime = 0;
  private int times = 0;
  private RefreshAdapter mAdapter;
  private List<String> listData;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_hotel_list);
    ButterKnife.bind(this);
    initToolbar();
    initRecyclerViewHotel();
    initRefreshLayout();
  }

  private void initToolbar() {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override public boolean onMenuItemClick(MenuItem item) {
        Log.i(TAG, "onMenuItemClick: menu click" + item.getItemId());
        if (!search.getSearchOpen()) {
          openSearch();
          return true;
        }
        return false;
      }
    });

    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  @Override public void onBackPressed() {
    if (search.getSearchOpen()) {
      closeSearch();
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_hotel, menu);
    return true;
  }

  private void initRecyclerViewHotel() {

  }

  public void openSearch() {
    toolbar.setTitle("");
    search.revealFromMenuItem(R.id.action_search, this);
    //    for (int x = 0; x < 10; x++) {
    //      SearchResult option = new SearchResult("Result "
    //          + Integer.toString(x), getResources().getDrawable(
    //          R.mipmap.ic_history));
    //      search.addSearchable(option);
    //    }
    search.setMenuListener(new SearchBox.MenuListener() {

      @Override public void onMenuClick() {
        // Hamburger has been clicked
        Toast.makeText(HotelListActivity.this, "Menu click", Toast.LENGTH_LONG).show();
      }
    });
    search.setSearchListener(new SearchBox.SearchListener() {

      @Override public void onSearchOpened() {
        // Use this to tint the screen

      }

      @Override public void onSearchClosed() {
        // Use this to un-tint the screen
        closeSearch();
      }

      @Override public void onSearchTermChanged(String term) {
        // React to the search term changing
        // Called after it has updated results
      }

      @Override public void onSearch(String searchTerm) {
        Toast.makeText(HotelListActivity.this, searchTerm + " Searched", Toast.LENGTH_LONG).show();
        toolbar.setTitle(searchTerm);
      }

      @Override public void onResultClick(SearchResult result) {
        //React to result being clicked
      }

      @Override public void onSearchCleared() {

      }
    });
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 1234 && resultCode == RESULT_OK) {
      ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
      search.populateEditText(matches.get(0));
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  protected void closeSearch() {
    search.hideCircularly(this);
  }

  private void initRefreshLayout() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);

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
        refreshTime++;
        times = 0;
        new Handler().postDelayed(new Runnable() {
          public void run() {

            listData.clear();
            for (int i = 0; i < 15; i++) {
              listData.add("item" + i + "after " + refreshTime + " times of refresh");
            }
            mAdapter.notifyDataSetChanged();
            mRecyclerView.refreshComplete();
          }
        }, 1000);            //refresh data here
      }

      @Override public void onLoadMore() {
        if (times < 2) {
          new Handler().postDelayed(new Runnable() {
            public void run() {
              mRecyclerView.loadMoreComplete();
              int size = listData.size();
              for (int i = 0; i < 15; i++) {
                listData.add("item" + (i + size));
              }
              mAdapter.notifyDataSetChanged();
              mRecyclerView.refreshComplete();
            }
          }, 1000);
        } else {
          new Handler().postDelayed(new Runnable() {
            public void run() {
              mAdapter.notifyDataSetChanged();
              mRecyclerView.loadMoreComplete();
            }
          }, 1000);
        }
        times++;
      }
    });

    listData = new ArrayList<String>();
    //for (int i = 0; i < 15; i++) {
    //  listData.add("item" + (i + listData.size()));
    //}
    mAdapter = new RefreshAdapter(listData,this);
    //
    mRecyclerView.setAdapter(mAdapter);
  }


  class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.ViewHolder> {

    List<String> mList;
    Context context;

    public RefreshAdapter(List<String> mList, Context context) {
      this.mList = mList;
      this.context = context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
      return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
      holder.textView.setText(mList.get(position));
    }

    @Override public int getItemCount() {
      return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView textView;

      public ViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_item);
      }
    }
  }
}
