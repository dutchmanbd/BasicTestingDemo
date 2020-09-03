package com.ticonsys.basictestingdemo.di

import android.content.Context
import androidx.room.Room
import com.ticonsys.basictestingdemo.data.local.ShoppingDao
import com.ticonsys.basictestingdemo.data.local.ShoppingDatabase
import com.ticonsys.basictestingdemo.data.remote.retrofit.PixabyApiService
import com.ticonsys.basictestingdemo.data.repositories.DefaultShoppingRepository
import com.ticonsys.basictestingdemo.data.repositories.ShoppingRepository
import com.ticonsys.basictestingdemo.internal.Constant.BASE_URL
import com.ticonsys.basictestingdemo.internal.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingDatabase::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideShoppingDao(shoppingDatabase: ShoppingDatabase) = shoppingDatabase.shoppingDao()

    @Singleton
    @Provides
    fun providePixabyApiService() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(PixabyApiService::class.java)

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        pixabyApiService: PixabyApiService
    ) = DefaultShoppingRepository(dao, pixabyApiService) as ShoppingRepository




}