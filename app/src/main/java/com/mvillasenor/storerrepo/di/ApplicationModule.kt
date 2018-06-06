package com.mvillasenor.storerrepo.di

import com.mvillasenor.storerrepo.Application
import com.mvillasenor.storerrepo.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
@Module
class ApplicationModule(private var app: Application) {

    @Provides
    @Singleton
    fun application(): Application {
        return app;
    }


}