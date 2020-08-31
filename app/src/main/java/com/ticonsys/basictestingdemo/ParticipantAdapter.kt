package com.ticonsys.basictestingdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ParticipantAdapter(

) : RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<RemoteParticipant>() {
        override fun areItemsTheSame(
            oldItem: RemoteParticipant,
            newItem: RemoteParticipant
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RemoteParticipant,
            newItem: RemoteParticipant
        ) = (oldItem == newItem)
    }

    val differ = AsyncListDiffer(this, differCallback)


    class ParticipantViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_participant_item, parent, false)
        return ParticipantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = differ.currentList[position]
    }

    override fun getItemCount() = differ.currentList.size
}