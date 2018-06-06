package com.mvillasenor.storerrepo.di

import com.mvillasenor.storerrepo.Application
import com.mvillasenor.storerrepo.di.data.DataModule
import com.mvillasenor.storerrepo.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, ViewModelModule::class, ApplicationModule::class, DataModule::class, ActivitiesModule::class))
interface ApplicationComponent {
    fun inject(app: Application)
}