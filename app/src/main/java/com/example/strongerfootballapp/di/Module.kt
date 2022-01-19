package com.example.strongerfootballapp.di

import com.example.strongerfootballapp.data.network.interceptors.NetworkConnectionInterceptor
import com.example.strongerfootballapp.network.provideHttpClient
import com.example.strongerfootballapp.network.provideMatchApi
import com.example.strongerfootballapp.network.provideRetrofit
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.data.repository.FootballRepositoryImpl
import com.example.strongerfootballapp.presentation.match_screen.MatchFragment
import com.example.strongerfootballapp.presentation.match_screen.MatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { NetworkConnectionInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get()) }
}
val viewModelModule = module {
    scope<MatchFragment> {
        viewModel { MatchViewModel(get()) }
        factory { provideMatchApi(get()) }
        factory<FootballRepository> { FootballRepositoryImpl(get()) }
    }
}