package com.ticonsys.basictestingdemo.ui

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ticonsys.basictestingdemo.ParticipantAdapter
import com.ticonsys.basictestingdemo.R
import com.ticonsys.basictestingdemo.RemoteParticipant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private val pAdapter by lazy {
//        ParticipantAdapter(width, height)
//    }
//    private val height by lazy {
//        resources.displayMetrics.let { displayMetrics ->
//            return@let displayMetrics.heightPixels
//        }
//    }
//
//    private val width by lazy {
//        resources.displayMetrics.let { displayMetrics ->
//            return@let displayMetrics.widthPixels
//        }
//    }
//
//    fun Activity.displayMetrics(): DisplayMetrics {
//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        return displayMetrics
//    }
//
//    fun dpToPx(dp: Int): Int {
//        return (dp * Resources.getSystem().displayMetrics.density).toInt()
//    }
//
//    private fun pxToDp(px: Int): Int {
//        return (px / Resources.getSystem().displayMetrics.density).toInt()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setupRecyclerView()
//        addParticipants()
//
//
//
//        var hasGridLayout = true
//        fabChange.setOnClickListener {
//            hasGridLayout = !hasGridLayout
//            updateLayoutManager(hasGridLayout)
//        }
    }

//    private fun updateLayoutManager(hasGridLayout: Boolean) {
//        if(hasGridLayout){
//            val height = resources.getDimension(R.dimen.local_grid_size).toInt()
//            val lp = RelativeLayout.LayoutParams(MATCH_PARENT, height)
//            flLocal.layoutParams = lp
//            lp.addRule(RelativeLayout.BELOW, flLocal.id)
////            val marginLayoutParams =
////                ViewGroup.MarginLayoutParams(rvParticipant.layoutParams)
////            marginLayoutParams.setMargins(0, height, 0, 0)
////            rvParticipant.layoutParams = marginLayoutParams
//            rvParticipant.apply {
//                layoutManager = GridLayoutManager(applicationContext, 2)
//            }
//        } else{
//            val lp = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//            lp.removeRule(RelativeLayout.BELOW)
//            flLocal.layoutParams = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//            rvParticipant.apply {
//                layoutManager = LinearLayoutManager(applicationContext).apply {
//                    orientation = LinearLayoutManager.HORIZONTAL
//                }
//            }
//        }
//    }
//
//    private fun addParticipants() {
//        pAdapter.differ.submitList(
//            listOf(
//                RemoteParticipant(1, "Jewel"),
//                RemoteParticipant(2, "Jewel1"),
//                RemoteParticipant(3, "Jewel2"),
//                RemoteParticipant(4, "Jewel3"),
//                RemoteParticipant(5, "Jewel4"),
//            )
//        )
//    }
//
//    private fun setupRecyclerView() {
//        rvParticipant.apply {
//            adapter = pAdapter
//            layoutManager = GridLayoutManager(applicationContext, 2)
//        }
//    }


}