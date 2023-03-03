package com.joblogic.technicaltestandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joblogic.technicaltestandroid.R
import com.joblogic.technicaltestandroid.databinding.FragmentHomeBinding
import com.joblogic.technicaltestandroid.ui.buy.BuyFragment
import com.joblogic.technicaltestandroid.ui.call.CallFragment
import com.joblogic.technicaltestandroid.ui.sell.SellFragment

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCall.setOnClickListener { replaceFragment(CallFragment()) }
        binding.btnBuy.setOnClickListener {replaceFragment(BuyFragment()) }
        binding.btnSell.setOnClickListener { replaceFragment(SellFragment()) }

    }

    private fun replaceFragment (fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frmContainer, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}