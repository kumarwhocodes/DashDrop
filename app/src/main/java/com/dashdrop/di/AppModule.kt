package com.dashdrop.di

import com.dashdrop.data.repo.address.GetAddressFireRepo
import com.dashdrop.data.repo.cart.GetCartFireRepo
import com.dashdrop.data.repo.category.GetCategoryFireRepo
import com.dashdrop.data.repo.favourite.GetFavFireRepo
import com.dashdrop.data.repo.item.GetItemFireRepo
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
        .collection("products"),
        query6 = FirebaseFirestore.getInstance().collection("popular")
    )

    @Singleton
    @Provides
    fun provideFireRepoCart()= GetCartFireRepo(
        query3 = FirebaseFirestore.getInstance()
        .collection("cart").document(user!!.uid),
        query4 = FirebaseFirestore.getInstance().collection("products")
    )

    @Singleton
    @Provides
    fun provideFireRepoFav()= GetFavFireRepo(
        query3 = FirebaseFirestore.getInstance()
            .collection("favourite").document(user!!.uid),
        query4 = FirebaseFirestore.getInstance().collection("products")
    )

    @Singleton
    @Provides
    fun provideFireRepoAddress()= GetAddressFireRepo(
        query5 = FirebaseFirestore.getInstance()
            .collection("address").document(user!!.uid)
    )

}