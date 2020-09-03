package com.ticonsys.basictestingdemo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ticonsys.basictestingdemo.data.local.ShoppingItem
import com.ticonsys.basictestingdemo.data.remote.responses.ImageResponse
import com.ticonsys.basictestingdemo.data.repositories.ShoppingRepository
import com.ticonsys.basictestingdemo.internal.Constant
import com.ticonsys.basictestingdemo.internal.Event
import com.ticonsys.basictestingdemo.internal.Resource
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository
): ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val image: LiveData<Event<Resource<ImageResponse>>>
        get() = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String>
        get() = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>>
        get() = _insertShoppingItemStatus

    fun setCurImageUrl(url: String){
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }


    fun insertShoppingItem(name: String, amountString: String, priceString: String){
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()){
            _insertShoppingItemStatus.postValue(Event(Resource.error("The field must not be empty", null)))
            return
        }

        if(name.length > Constant.MAX_NAME_LENGTH){
            _insertShoppingItemStatus.postValue(Event(
                Resource.error("The name of the item must not exceed ${Constant.MAX_NAME_LENGTH} characters", null)
            ))
            return
        }

        if(priceString.length > Constant.MAX_PRICE_LENGTH){
            _insertShoppingItemStatus.postValue(Event(
                Resource.error("The price of the item must not exceed ${Constant.MAX_PRICE_LENGTH} characters", null)
            ))
            return
        }

        val amount = try {
            amountString.toInt()
        } catch (e: Exception){
            _insertShoppingItemStatus.postValue(Event(
                Resource.error("Please enter valid amount", null)
            ))
            return
        }

        val shoppingItem = ShoppingItem(name, amount, priceString.toFloat(), _curImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurImageUrl("")
        _insertShoppingItemStatus.postValue(Event(
            Resource.success(shoppingItem)
        ))

    }

    fun searchForImage(imageQuery: String){
        if(imageQuery.isEmpty()){
            return
        }
        _images.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            _images.value = Event(response)
        }
    }
}