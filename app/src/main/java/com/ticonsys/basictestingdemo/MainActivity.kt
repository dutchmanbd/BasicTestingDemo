package com.ticonsys.basictestingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val pAdapter by lazy {
        ParticipantAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        addParticipants()

        var hasGridLayout = true
        fabChange.setOnClickListener {
            hasGridLayout = !hasGridLayout
            updateLayoutManager(hasGridLayout)
        }
    }

    private fun updateLayoutManager(hasGridLayout: Boolean) {
        if(hasGridLayout){
            val height = resources.getDimension(R.dimen.local_grid_size).toInt()
            val lp = RelativeLayout.LayoutParams(MATCH_PARENT, height)
            flLocal.layoutParams = lp
            lp.addRule(RelativeLayout.BELOW, flLocal.id)
//            val marginLayoutParams =
//                ViewGroup.MarginLayoutParams(rvParticipant.layoutParams)
//            marginLayoutParams.setMargins(0, height, 0, 0)
//            rvParticipant.layoutParams = marginLayoutParams
            rvParticipant.apply {
                layoutManager = GridLayoutManager(applicationContext, 2)
            }
        } else{
            val lp = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            lp.removeRule(RelativeLayout.BELOW)
            flLocal.layoutParams = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            rvParticipant.apply {
                layoutManager = LinearLayoutManager(applicationContext).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            }
        }
    }

    private fun addParticipants() {
        pAdapter.differ.submitList(
            listOf(
                RemoteParticipant(1, "Jewel"),
                RemoteParticipant(2, "Jewel1"),
                RemoteParticipant(3, "Jewel2"),
                RemoteParticipant(4, "Jewel3"),
                RemoteParticipant(5, "Jewel4"),
            )
        )
    }

    private fun setupRecyclerView() {
        rvParticipant.apply {
            adapter = pAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
        }
    }


}