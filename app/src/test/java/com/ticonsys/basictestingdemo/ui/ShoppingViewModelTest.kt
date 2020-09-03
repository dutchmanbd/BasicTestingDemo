package com.ticonsys.basictestingdemo.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ticonsys.basictestingdemo.MainCoroutineRule
import com.ticonsys.basictestingdemo.data.repositories.FakeShoppingRepository
import com.ticonsys.basictestingdemo.getOrAwaitValueTest
import com.ticonsys.basictestingdemo.internal.Constant
import com.ticonsys.basictestingdemo.internal.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ShoppingViewModel

    @Before
    fun setup(){
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun insertShoppingItemWithEmptyFieldReturnsError(){
        viewModel.insertShoppingItem("name", "", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun insertShoppingItemWithTooLongNameReturnsError(){

        val name = buildString {
            for(i in 1..Constant.MAX_NAME_LENGTH + 1){
                append(1)
            }
        }

        viewModel.insertShoppingItem(name, "5", "3.0")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun insertShoppingItemWithTooLongPriceReturnsError(){

        val price = buildString {
            for(i in 1..Constant.MAX_PRICE_LENGTH + 1){
                append(1)
            }
        }

        viewModel.insertShoppingItem("name", "5", price)

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun insertShoppingItemWithTooHighAmountReturnsError(){
        viewModel.insertShoppingItem("name", "99999999999999999999", "4.4")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun insertShoppingItemWithValidInputReturnsSuccess(){
        viewModel.insertShoppingItem("name", "5", "4.4")

        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)

    }

    @Test
    fun setCurImageUrlIsEmptyWhenInsertShoppingItemReturnTrue(){
        viewModel.insertShoppingItem("name", "5", "4.4")
        val value = viewModel.curImageUrl.getOrAwaitValueTest()
        assertThat(value).isEmpty()
    }

    @Test
    fun setCurImageUrlReturnsTrue(){
        val url = Constant.BASE_URL
        viewModel.setCurImageUrl(url)
        val value = viewModel.curImageUrl.getOrAwaitValueTest()
        assertThat(value).isEqualTo(url)
    }

}