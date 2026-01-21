/*
 * Copyright (c) 2022-2026 Universitat Politècnica de València
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
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import upv.dadm.ex15_viewpager2.ui.fragments.ID
import upv.dadm.ex15_viewpager2.ui.fragments.MessageFragment

/**
 * Custom adapter to show a variable number of Fragments
 * (it must override getItemId() and containsItem()).
 * An AsyncListDiffer computes the changes between the old and new list of Ids to automatically
 * update the Fragments displayed.
 */
class VariableSizeFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Computes the difference between the old and new list of the Ids of
     * the currently displayed Fragments.
     */
    private val diff = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Long>() {
        override fun areItemsTheSame(oldItem: Long, newItem: Long): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Long, newItem: Long): Boolean = oldItem == newItem
    })

    /**
     * Returns the number of Fragments currently displayed.
     */
    override fun getItemCount(): Int = diff.currentList.size

    /**
     * Creates a new Fragment for the given position.
     * The position is used to get the Fragment Id (passed as argument).
     */
    override fun createFragment(position: Int): Fragment {
        val fragment = MessageFragment()
        fragment.arguments = bundleOf(ID to diff.currentList[position])
        return fragment
    }

    /**
     * Returns the Id of the Fragment at a given position.
     */
    override fun getItemId(position: Int): Long = diff.currentList[position]

    /**
     * Determines whether a Fragment with a given Id is currently being displayed.
     */
    override fun containsItem(itemId: Long): Boolean = diff.currentList.contains(itemId)

    /**
     * Provides the new list of Ids for the Fragments to be displayed.
     */
    fun submitList(newList: List<Long>) = diff.submitList(newList)

}