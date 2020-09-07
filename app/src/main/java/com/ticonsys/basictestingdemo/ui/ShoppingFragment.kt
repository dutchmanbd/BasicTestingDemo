package com.ticonsys.basictestingdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ticonsys.basictestingdemo.R
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment: Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

        fabAddShoppingItem.setOnClickListener {
            val addAction = ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            findNavController().navigate(addAction)
        }

    }
}