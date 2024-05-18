package com.dashdrop.di

import com.dashdrop.data.repo.GetCartFireRepo
import com.dashdrop.data.repo.GetCategoryFireRepo
import com.dashdrop.data.repo.GetItemFireRepo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val user = com.google.firebase.ktx.Firebase.auth.currentUser

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

    @Singleton
    @Provides
    fun provideFireRepoCart()= GetCartFireRepo(
        query3 = FirebaseFirestore.getInstance()
        .collection("cart").document(user!!.uid),
        query4 = FirebaseFirestore.getInstance().collection("products")
    )

}