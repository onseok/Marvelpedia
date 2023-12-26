/*
 * Copyright 2023 onseok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.onseok.marvelpedia.database.impl.di

import android.content.Context
import androidx.room.Room
import com.onseok.marvelpedia.database.LocalDataSource
import com.onseok.marvelpedia.database.impl.LocalDataSourceImpl
import com.onseok.marvelpedia.database.impl.MarvelDatabase
import com.onseok.marvelpedia.database.impl.dao.MarvelHeroDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    @Binds
    @Singleton
    fun provideLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl,
    ): LocalDataSource

    companion object {

        @Provides
        @Singleton
        fun provideMarvelHeroDao(
            marvelDatabase: MarvelDatabase,
        ): MarvelHeroDao {
            return marvelDatabase.marvelHeroDao()
        }

        @Provides
        @Singleton
        fun provideMarvelDatabase(
            @ApplicationContext context: Context,
        ): MarvelDatabase {
            return Room
                .databaseBuilder(context, MarvelDatabase::class.java, "marvelpeida.db")
                .build()
        }
    }
}
