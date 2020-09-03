package com.ticonsys.basictestingdemo.data.repositories

import androidx.lifecycle.LiveData
import com.ticonsys.basictestingdemo.data.local.ShoppingItem
import com.ticonsys.basictestingdemo.data.remote.responses.ImageResponse
import com.ticonsys.basictestingdemo.internal.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>


}