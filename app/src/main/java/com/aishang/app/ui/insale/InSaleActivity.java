package com.aishang.app.ui.insale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import javax.inject.Inject;

public class InSaleActivity extends BaseActivity implements InSaleMvpView{

  @Inject
  InSalePresenter mPersenter;

  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.searchbox)
  SearchBox searchBox;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getActivityComponent().inject(this);
    setContentView(R.layout.activity_in_sale);
    ButterKnife.bind(this);
    mPersenter.attachView(this);
    initToolbar();
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        openSearch();
        return true;
      }
    });
  }
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_hotel, menu);
    return true;
  }

  private void initToolbar()
  {
    toolbar.setTitle("");
    this.setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(R.mipmap.iconfont_livesvg);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
  }

  public void openSearch() {
    searchBox.revealFromMenuItem(R.id.action_search, this);
//    for (int x = 0; x < 10; x++) {
//      SearchResult option = new SearchResult("Result "
//          + Integer.toString(x), getResources().getDrawable(
//          R.mipmap.ic_history));
//      search.addSearchable(option);
//    }
    searchBox.setMenuListener(new SearchBox.MenuListener() {

      @Override
      public void onMenuClick() {
        // Hamburger has been clicked
        Toast.makeText(InSaleActivity.this, "Menu click",
                Toast.LENGTH_LONG).show();
      }

    });
    searchBox.setSearchListener(new SearchBox.SearchListener() {

      @Override
      public void onSearchOpened() {
        // Use this to tint the screen

      }

      @Override
      public void onSearchClosed() {
        // Use this to un-tint the screen
        closeSearch();
      }

      @Override
      public void onSearchTermChanged(String term) {
        // React to the search term changing
        // Called after it has updated results
      }

      @Override
      public void onSearch(String searchTerm) {
        Toast.makeText(InSaleActivity.this, searchTerm + " Searched",
                Toast.LENGTH_LONG).show();
        toolbar.setTitle(searchTerm);

      }

      @Override
      public void onResultClick(SearchResult result) {
        //React to result being clicked
      }

      @Override
      public void onSearchCleared() {

      }
    });
  }
  protected void closeSearch() {
    searchBox.hideCircularly(this);
    if(searchBox.getSearchText().isEmpty())toolbar.setTitle("");
  }
}

