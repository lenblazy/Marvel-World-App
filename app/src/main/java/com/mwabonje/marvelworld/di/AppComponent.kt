package com.mwabonje.marvelworld.di

import android.app.Application
import com.mwabonje.marvelworld.di.injectables.ActivitiesModule
import com.mwabonje.marvelworld.di.injectables.FragmentModule
import com.mwabonje.marvelworld.MarvelApp
import com.mwabonje.marvelworld.database.DatabaseModule
import com.mwabonje.marvelworld.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ActivitiesModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<MarvelApp> {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

    }
}