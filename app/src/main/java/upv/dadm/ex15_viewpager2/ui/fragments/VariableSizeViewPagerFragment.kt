/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex15_viewpager2.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import upv.dadm.ex15_viewpager2.R
import upv.dadm.ex15_viewpager2.databinding.FragmentViewPagerBinding
import upv.dadm.ex15_viewpager2.ui.adapters.VariableSizeFragmentStateAdapter
import upv.dadm.ex15_viewpager2.ui.viewmodels.MessagesViewModel

/**
 * Displays a variable number of Fragments.
 * A scrollable TabLayout displays a Tab for each Fragment.
 * Action elements can add a new Fragment or remove the current Fragment from the ViewPager2.
 */
class VariableSizeViewPagerFragment : Fragment(R.layout.fragment_view_pager), MenuProvider {

    // Reference to the ViewModel that holds the list of Ids
    private val viewModel: MessagesViewModel by viewModels()

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
        val adapter = VariableSizeFragmentStateAdapter(this)
        binding.viewPager.adapter = adapter

        // Set the TabLayout scrollable mode
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        // Link the TabLayout to the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(R.string.tab, viewModel.messagesList.value?.get(position))
        }.attach()

        // Add this Fragment as MenuProvider to its Activity
        requireActivity().addMenuProvider(this@VariableSizeViewPagerFragment, viewLifecycleOwner)

        // Update the Fragments displayed by the ViewPager2 when the list of Ids changes
        viewModel.messagesList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }

    // Populates the ActionBar with action elements
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_variable_size, menu)
    }

    // Reacts to the selection of action elements
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        // Determine the action to take place according to its Id
        return when (menuItem.itemId) {

            // Add a new Fragment
            R.id.mAddFragment -> {
                viewModel.addMessage()
                true
            }

            // Remove the current Fragment
            R.id.mRemoveFragment -> {
                viewModel.removeMessage(binding.viewPager.currentItem)
                true
            }

            // If none of the custom action elements was selected, let the system deal with it
            else -> false
        }
    }
}