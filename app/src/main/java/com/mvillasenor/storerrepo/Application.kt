package com.mvillasenor.storerrepo

import android.app.Activity
import android.app.Application
import com.mvillasenor.storerrepo.di.ApplicationModule
import com.mvillasenor.storerrepo.di.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class Application : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
                .inject(this)
    }

    override fun activityInjector() = activityInjector
}