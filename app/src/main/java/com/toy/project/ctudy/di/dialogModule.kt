package com.toy.project.ctudy.di

import com.toy.project.ctudy.repository.etc.CommonDialogImpl
import com.toy.project.ctudy.repository.etc.CommonDialogManager
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Dialog Module
 */
val dialogModule = module {

    factory<CommonDialogManager> {
        CommonDialogImpl(androidApplication())
    }
}