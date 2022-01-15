package com.example.strongerfootballapp.ui.match_screen

import com.example.strongerfootballapp.databinding.MatchFragmentBinding
import com.example.strongerfootballapp.ui.base.BaseFragment
import com.example.strongerfootballapp.ui.base.Inflate
import kotlin.reflect.KClass

class MatchFragment : BaseFragment<MatchFragmentBinding,MatchViewModel>() {
    override val viewModelClass: KClass<MatchViewModel>
        get() = MatchViewModel::class

    override fun inflateFragment(): Inflate<MatchFragmentBinding> {
       return MatchFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: MatchViewModel) {

    }
}