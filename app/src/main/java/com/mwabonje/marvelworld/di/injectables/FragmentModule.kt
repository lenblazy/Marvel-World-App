package com.mwabonje.marvelworld.di.injectables

import com.mwabonje.marvelworld.view.fragments.detail.CharacterDetailFragment
import com.mwabonje.marvelworld.view.fragments.list.CharacterListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector()
    abstract fun contributeCharacterListFragment(): CharacterListFragment

    @ContributesAndroidInjector()
    abstract fun contributeCharacterDetailFragment(): CharacterDetailFragment

}