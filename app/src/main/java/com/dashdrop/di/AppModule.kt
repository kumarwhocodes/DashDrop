package com.dashdrop.di

import com.dashdrop.data.repo.GetCategoryFireRepo
import com.dashdrop.data.repo.GetItemFireRepo
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireRepoCategoryRow()= GetCategoryFireRepo(query1 = FirebaseFirestore.getInstance()
        .collection("Category")
    )

    @Singleton
    @Provides
    fun provideFireRepoItems()= GetItemFireRepo(query2 = FirebaseFirestore.getInstance()
        .collection("products")
    )



}