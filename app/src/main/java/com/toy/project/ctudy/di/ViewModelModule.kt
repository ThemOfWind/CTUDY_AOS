package com.toy.project.ctudy.di

import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModel Module
 *
 * Koin이 해당 viewmodel을 ViewModelFactory에 등록하고 현재 컴포넌트와 바인딩한다.
 * 주입받을 때도 해당하는 ViewModelFactory 객체를 불러온다.
 */
val ViewModelModule = module {
    viewModel { IntroViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignViewModel(get()) }
    viewModel { RoomAddViewModel(get()) }
}