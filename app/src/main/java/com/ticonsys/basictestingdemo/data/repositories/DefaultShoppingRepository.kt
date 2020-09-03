package com.ticonsys.basictestingdemo.data.repositories

import com.ticonsys.basictestingdemo.data.local.ShoppingDao
import com.ticonsys.basictestingdemo.data.local.ShoppingItem
import com.ticonsys.basictestingdemo.data.remote.responses.ImageResponse
import com.ticonsys.basictestingdemo.data.remote.retrofit.PixabyApiService
import com.ticonsys.basictestingdemo.internal.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabyApiService: PixabyApiService
): ShoppingRepository {
    override suspend fun insertShoppingItem(
        shoppingItem: ShoppingItem
    ) = shoppingDao.insertShoppingItem(shoppingItem)

    override suspend fun deleteShoppingItem(
        shoppingItem: ShoppingItem
    ) = shoppingDao.deleteShoppingItem(shoppingItem)

    override fun observeAllShoppingItems() = shoppingDao.observeAllShoppingItems()

    override fun observeTotalPrice() = shoppingDao.observeTotalPrice()

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabyApiService.searchForImage(imageQuery)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            }else{
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception){
            Resource.error(e.message ?: "Check your internet connection", null)
        }
    }
}