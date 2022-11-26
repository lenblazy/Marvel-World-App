package com.mwabonje.marvelworld.di.injectables

import com.mwabonje.marvelworld.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector()
    abstract fun contributeActivityAndroidInjector(): MainActivity

}