package com.mvillasenor.storerrepo.di.viewmodel

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */

@MustBeDocumented
@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention()
@MapKey
internal annotation class ViewModelKey(
        val value: KClass<out ViewModel>)