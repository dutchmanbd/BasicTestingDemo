package com.ticonsys.basictestingdemo.internal

import android.content.Context

class ResourceComparer {

    fun isEqual(
        context: Context, resId: Int, text: String
    ) = context.getString(resId) == text
}