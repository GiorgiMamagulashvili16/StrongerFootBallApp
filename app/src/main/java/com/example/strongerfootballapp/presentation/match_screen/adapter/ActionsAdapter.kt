package com.example.strongerfootballapp.ui.match_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.strongerfootballapp.databinding.TeamActionsContainerBinding
import com.example.strongerfootballapp.model.Summary
import com.example.strongerfootballapp.ui.match_screen.adapter.helper.ActionAdapterHelper
import com.example.strongerfootballapp.utils.ItemDiffUtil

class ActionsAdapter(private val helper: ActionAdapterHelper)
    : ListAdapter<Summary, ActionsAdapter.ViewHolder>(ItemDiffUtil<Summary>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TeamActionsContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(currentList, helper)


    class ViewHolder(private val binding: TeamActionsContainerBinding): RecyclerView.ViewHolder(binding.root){

        fun onBind(summaries: MutableList<Summary>, helper: ActionAdapterHelper){
            val current = summaries[adapterPosition]

            helper.createActionView(binding.root.context, current.actionTime, current.team1Action){
                binding.firstTeamLinearLayout.addView(it)
            }

            helper.createActionView(binding.root.context, current.actionTime, current.team2Action){
                binding.secondTeamLinearLayout.addView(it)
            }

            helper.showHalfScoreView(current.actionTime, binding.halfScoreView, summaries)
        }
    }
}
