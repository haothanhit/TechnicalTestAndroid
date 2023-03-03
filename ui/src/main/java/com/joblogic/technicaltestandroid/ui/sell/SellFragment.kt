package com.joblogic.technicaltestandroid.ui.sell

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joblogic.technicaltestandroid.R
import com.joblogic.technicaltestandroid.base.BaseFragment
import com.joblogic.technicaltestandroid.databinding.FragmentDetailBinding
import com.joblogic.technicaltestandroid.presentation.viewmodel.SellUIState
import com.joblogic.technicaltestandroid.presentation.viewmodel.SellViewModel
import com.joblogic.technicaltestandroid.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SellFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>() {


    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override val viewModel: SellViewModel by viewModels()

    @Inject
    lateinit var sellAdapter: SellAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sellSuccess
            .mapNotNull { it }
            .distinctUntilChanged()
            .onEach(this::onViewStateChange)
            .launchIn(lifecycleScope)
        setupToolbar()
        initRecyclerView()

    }


    private fun setupToolbar() {
        binding.ivBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvDetail.text = activity?.getString(R.string.sell_list)
    }


    private fun initRecyclerView() {

        binding.rcvList.apply {
            adapter = sellAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

    private fun onViewStateChange(event: SellUIState) {
        when (event) {
            is SellUIState.Error -> handleErrorMessage(event.error)
            is SellUIState.Loading -> handleLoading(true)
            is SellUIState.Success -> {
                handleLoading(false)
                event.data.let {
                    sellAdapter.list = it
                }
            }
        }
    }
}