package com.joblogic.technicaltestandroid.ui.buy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joblogic.technicaltestandroid.R
import com.joblogic.technicaltestandroid.base.BaseFragment
import com.joblogic.technicaltestandroid.databinding.FragmentDetailBinding
import com.joblogic.technicaltestandroid.presentation.viewmodel.BuyUIState
import com.joblogic.technicaltestandroid.presentation.viewmodel.BuyViewModel
import com.joblogic.technicaltestandroid.presentation.viewmodel.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BuyFragment : BaseFragment<FragmentDetailBinding, BaseViewModel>() {


    override fun getViewBinding(): FragmentDetailBinding =
        FragmentDetailBinding.inflate(layoutInflater)

    override val viewModel: BuyViewModel by viewModels()

    @Inject
    lateinit var buyAdapter: BuyAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.buySuccess
            .mapNotNull { it }
            .distinctUntilChanged()
            .onEach(this::onViewStateChange)
            .launchIn(lifecycleScope)
        setupToolbar()
        initRecyclerView()

    }



    private fun setupToolbar() {
        binding.ivBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvDetail.text = activity?.getString(R.string.buy_list)
    }



private fun initRecyclerView() {

    binding.rcvList.apply {
        adapter = buyAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }


}

private fun onViewStateChange(event: BuyUIState) {
    when (event) {
        is BuyUIState.Error -> handleErrorMessage(event.error)
        is BuyUIState.Loading -> handleLoading(true)
        is BuyUIState.Success -> {
            handleLoading(false)
            event.data.let {
                buyAdapter.list = it
            }
            event.data.map {
                viewModel.insertAllListToDatabase(it)
            }
        }
    }
}
}