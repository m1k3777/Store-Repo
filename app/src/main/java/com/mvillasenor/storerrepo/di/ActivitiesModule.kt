package com.mvillasenor.storerrepo.di

import com.mvillasenor.storerrepo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeYourActivityInjector(): MainActivity
}