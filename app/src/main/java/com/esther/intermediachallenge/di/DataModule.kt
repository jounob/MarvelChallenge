package com.esther.intermediachallenge.di

import android.app.Service
import com.esther.intermediachallenge.data.dataSource.CharacterDataSource
import com.esther.intermediachallenge.data.repositories.CharactersRepository
import com.esther.intermediachallenge.data.services.CharacterService
import com.esther.intermediachallenge.utils.ApiConfiguration.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @ApiMarvel
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    }

    @Provides
    fun provideCharacterService(@ApiMarvel retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Provides
    fun provideCharacterDataSource(characterService: CharacterService): CharacterDataSource {
        return CharacterDataSource(characterService)
    }

    @Provides
    fun provideCharacterRepository(characterDataSource: CharacterDataSource): CharactersRepository {
        return CharactersRepository(characterDataSource)
    }
}