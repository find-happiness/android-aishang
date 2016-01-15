package com.aishang.app.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.aishang.app.injection.component.ApplicationComponent;
import com.aishang.app.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
