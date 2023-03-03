package com.joblogic.technicaltestandroid.ui.call

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joblogic.technicaltestandroid.R
import com.joblogic.technicaltestandroid.base.BaseFragment
import com.joblogic.technicaltestandroid.databinding.FragmentDetailBinding
import com.joblogic.technicaltestandroid.presentation.viewmodel.CallUIState
import com.joblogic.technicaltestandroid.presentation.viewmodel.CallViewModel
import com.joblogic.technicaltestandroid.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class CallFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>() {


    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override val viewModel: CallViewModel by viewModels()

    @Inject
    lateinit var callAdapter: CallAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.callSuccess
            .mapNotNull { it }
            .distinctUntilChanged()
            .onEach(this::onViewStateChange)
            .launchIn(lifecycleScope)
        setupToolbar()
        initRecyclerView()

    }


    private fun setupToolbar() {
        binding.ivBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvDetail.text = activity?.getString(R.string.call_list)

    }


    private fun initRecyclerView() {

        binding.rcvList.apply {
            adapter = callAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }

    private fun onViewStateChange(event: CallUIState) {
        when (event) {
            is CallUIState.Error -> handleErrorMessage(event.error)
            is CallUIState.Loading -> handleLoading(true)
            is CallUIState.Success -> {
                handleLoading(false)
                event.data.let {
                    callAdapter.list = it
                }
            }
        }
    }
}