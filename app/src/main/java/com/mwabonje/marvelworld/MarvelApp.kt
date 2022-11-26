package com.mwabonje.marvelworld

import com.mwabonje.marvelworld.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MarvelApp: DaggerApplication()  {

    private val applicationInjector =
        DaggerAppComponent.builder().application(this).build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        applicationInjector

}