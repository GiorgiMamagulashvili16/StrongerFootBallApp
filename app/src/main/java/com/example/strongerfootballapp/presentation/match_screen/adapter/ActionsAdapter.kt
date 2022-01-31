package com.example.strongerfootballapp.presentation.match_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.strongerfootballapp.databinding.ActionsRecyclerItemBinding
import com.example.strongerfootballapp.domain.mappers.TeamActionUiModelMapper
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.utils.ItemDiffUtil
import com.example.strongerfootballapp.presentation.match_screen.adapter.helper.ActionAdapterHelper

class ActionsAdapter(
    private val helper: ActionAdapterHelper,
) : ListAdapter<Summary, ActionsAdapter.ViewHolder>(ItemDiffUtil<Summary>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ActionsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.onBind(currentList, helper,)


    class ViewHolder(private val binding: ActionsRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            summaries: MutableList<Summary>,
            helper: ActionAdapterHelper,
        ) {
            val current = summaries[adapterPosition]
            with(binding) {
                actionsContainer.submitTeamActions(
                    current.actionTime,
                    helper.mapTeamAction(current.team1Action)
                )
                actionsContainer.submitTeamActions(
                    current.actionTime,
                    helper.mapTeamAction(current.team2Action)
                )
                helper.getHalfScoreView(itemView.context, current.actionTime, summaries)?.let {
                    binding.root.addView(it, 0)
                }
            }
        }

    }
}