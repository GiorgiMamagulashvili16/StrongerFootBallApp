package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.ActionTypeUIModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.ActionTypes

class ActionTypeUIModelMapper: Mapper<ActionTypes, ActionTypeUIModel> {

    override fun mapModel(model: ActionTypes): ActionTypeUIModel {
        return  ActionTypeUIModel(model.actionImageRes, model.actionTextRes, model.actionId)
    }

}