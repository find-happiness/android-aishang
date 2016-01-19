package com.aishang.app.ui.hotel;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import java.util.ArrayList;

public class HotelListActivity extends BaseActivity {
  private SearchBox search;
  private Toolbar toolbar;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hotel_list);
    search = (SearchBox) findViewById(R.id.searchbox);
    search.enableVoiceRecognition(this);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    this.setSupportActionBar(toolbar);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        openSearch();
        return true;
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_hotel, menu);
    return true;
  }

  public void openSearch() {
    toolbar.setTitle("");
    search.revealFromMenuItem(R.id.action_search, this);
    for (int x = 0; x < 10; x++) {
      SearchResult option = new SearchResult("Result "
          + Integer.toString(x), getResources().getDrawable(
          R.mipmap.ic_history));
      search.addSearchable(option);
    }
    search.setMenuListener(new SearchBox.MenuListener() {

      @Override
      public void onMenuClick() {
        // Hamburger has been clicked
        Toast.makeText(HotelListActivity.this, "Menu click",
            Toast.LENGTH_LONG).show();
      }

    });
    search.setSearchListener(new SearchBox.SearchListener() {

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
        Toast.makeText(HotelListActivity.this, searchTerm + " Searched",
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

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 1234 && resultCode == RESULT_OK) {
      ArrayList<String> matches = data
          .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
      search.populateEditText(matches.get(0));
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  protected void closeSearch() {
    search.hideCircularly(this);
    if(search.getSearchText().isEmpty())toolbar.setTitle("");
  }
}
