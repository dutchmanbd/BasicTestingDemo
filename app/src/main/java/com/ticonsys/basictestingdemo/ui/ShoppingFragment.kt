package com.ticonsys.basictestingdemo.ui

import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.ticonsys.basictestingdemo.R
import kotlinx.android.synthetic.main.fragment_shopping.*

class ShoppingFragment: Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModel

    private var isFormatting = false
    private var deletingHyphen = false
    private var hyphenStart = 0
    private var deletingBackward = false

    private val phoneNumberWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            s ?: return
            if (isFormatting)
                return

            // Make sure user is deleting one char, without a selection
            val selStart = Selection.getSelectionStart(s)
            val selEnd = Selection.getSelectionEnd(s)
            if (s.length > 1 // Can delete another character
                && count == 1 // Deleting only one character
                && after == 0 // Deleting
                && s[start] == '-' // a hyphen
                && selStart == selEnd) { // no selection
                deletingHyphen = true;
                hyphenStart = start;
                // Check if the user is deleting forward or backward
                deletingBackward = selStart == start + 1
            } else {
                deletingHyphen = false
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(text: Editable?) {
            text ?: return

            if (isFormatting)
                return
            isFormatting = true
            // If deleting hyphen, also delete character before or after it
            if (deletingHyphen && hyphenStart > 0) {
                if (deletingBackward) {
                    if (hyphenStart - 1 < text.length) {
                        text.delete(hyphenStart - 1, hyphenStart)
                    }
                } else if (hyphenStart < text.length) {
                    text.delete(hyphenStart, hyphenStart + 1)
                }
            }
            if (text.length == 3 || text.length == 8) {
                text.append('-')
            }
            isFormatting = false
        }
    }

    private var keyDel = 0
    private val phoneWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s ?: return
            etPhoneNumber.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL) keyDel = 1
                false
            }


            if (keyDel == 0) {
                val len: Int = etPhoneNumber.text!!.length
//                if (len == 3 || len == 8) {
//                    etPhoneNumber.setText("${etPhoneNumber.text.toString()}-")
//                    etPhoneNumber.setSelection(etPhoneNumber.text!!.length)
//                }

                if (len == 6) {
                    etPhoneNumber.setText("${etPhoneNumber.text.toString()}-")
                    etPhoneNumber.setSelection(etPhoneNumber.text!!.length)
                }
            } else {
                keyDel = 0
            }
        }

        override fun afterTextChanged(text: Editable?) {
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

//        etPhoneNumber.addTextChangedListener(phoneNumberWatcher)
        //etPhoneNumber.addTextChangedListener(phoneWatcher)

//        val listener = MaskedTextChangedListener("[000]-[0000]-[0000]", etPhoneNumber)
        val listener = MaskedTextChangedListener("[000000]-[0000000]", etPhoneNumber)
        etPhoneNumber.addTextChangedListener(listener)
        etPhoneNumber.onFocusChangeListener = listener
        fabAddShoppingItem.setOnClickListener {
            val addAction = ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            findNavController().navigate(addAction)
        }

    }
}