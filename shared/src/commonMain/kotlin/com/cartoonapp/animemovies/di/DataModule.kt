package com.cartoonapp.animemovies.di

import com.cartoonapp.animemovies.repository.DefaultKodikRepository
import com.cartoonapp.common.core_network.api.KodikApi
import com.cartoonapp.common.core_network.api.kodik.DefaultKodikApi
import com.cartoonapp.common.core_network.provider.EndpointProvider
import com.cartoonapp.common.core_network.provider.HttpClientEngineProvider
import com.cartoonapp.common.core_network.provider.HttpClientProvider
import com.cartoonapp.common.core_network.provider.JsonProvider
import com.cartoonapp.common.core_network.provider.KodikHttpClientProvider
import com.cartoonapp.common.core_network.provider.KodikHttpClientQualifier
import com.cartoonapp.common.domain.repository.KodikRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::HttpClientEngineProvider)
    singleOf(HttpClientEngineProvider::get)

    factoryOf(::HttpClientProvider)
    singleOf(HttpClientProvider::get)

    factoryOf(::KodikHttpClientProvider)
    single(qualifier<KodikHttpClientQualifier>()) {
        get<KodikHttpClientProvider>().get()
    }

    factoryOf(::JsonProvider)
    singleOf(JsonProvider::get)

    singleOf(::EndpointProvider)

    factory<KodikApi> {
        DefaultKodikApi(httpClient = get(qualifier = qualifier<KodikHttpClientQualifier>()))
    }

    singleOf(::DefaultKodikRepository) bind KodikRepository::class
}