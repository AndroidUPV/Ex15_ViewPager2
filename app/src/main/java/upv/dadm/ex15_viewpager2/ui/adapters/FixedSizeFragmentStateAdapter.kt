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

package upv.dadm.ex15_viewpager2.ui.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import upv.dadm.ex15_viewpager2.ui.fragments.ID
import upv.dadm.ex15_viewpager2.ui.fragments.MessageFragment

/**
 * Custom adapter to show a fixed number of Fragments.
 */
class FixedSizeFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Returns the fixed number of Fragments to display (4).
     */
    override fun getItemCount(): Int = 4

    /**
     * Creates a new Fragment for the given position.
     * The position is used as the Id for the Fragment (passed as argument).
     */
    override fun createFragment(position: Int): Fragment {
        val fragment = MessageFragment()
        fragment.arguments = bundleOf(ID to position.toLong())
        return fragment
    }

}