/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex15_viewpager2.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import upv.dadm.ex15_viewpager2.R
import upv.dadm.ex15_viewpager2.databinding.FragmentViewPagerBinding
import upv.dadm.ex15_viewpager2.ui.adapters.FixedSizeFragmentStateAdapter

/**
 * Displays a fixed number of Fragments.
 * A TabLayout displays a Tab for each Fragment.
 */
class FixedSizeViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    // Backing property to resource binding
    private var _binding: FragmentViewPagerBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = FragmentViewPagerBinding.bind(view)

        // Associated the adapter to the ViewPager2
        binding.viewPager.adapter = FixedSizeFragmentStateAdapter(this)

        // Link the TabLayout to the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(R.string.tab, position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }
}