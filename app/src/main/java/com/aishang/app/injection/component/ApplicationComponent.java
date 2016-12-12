package com.aishang.app.injection.component;

import android.app.Application;
import android.content.Context;

import com.aishang.app.ui.login.LoginActivity;
import com.aishang.app.util.EventPosterHelper;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import com.aishang.app.data.DataManager;
import com.aishang.app.data.SyncService;
import com.aishang.app.data.local.DatabaseHelper;
import com.aishang.app.data.local.PreferencesHelper;
import com.aishang.app.data.remote.AiShangService;
import com.aishang.app.injection.ApplicationContext;
import com.aishang.app.injection.module.ApplicationModule;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(SyncService syncService);

  void inject(LoginActivity activity);

  @ApplicationContext Context context();

  Application application();

  AiShangService ribotsService();

  PreferencesHelper preferencesHelper();

  DatabaseHelper databaseHelper();

  DataManager dataManager();

  Bus eventBus();
}
