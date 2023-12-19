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
import android.view.View
import androidx.fragment.app.Fragment
import upv.dadm.ex15_viewpager2.R
import upv.dadm.ex15_viewpager2.databinding.FragmentMessageBinding

// Constant used to pass the Id as argument to the Fragment
const val ID = "upv.dadm.ex15_viewpager2.ui.fragments.ID"

/**
 * Displays a message.
 */
class MessageFragment : Fragment(R.layout.fragment_message) {

    // Backing property to resource binding
    private var _binding: FragmentMessageBinding? = null

    // Property valid between onCreateView() and onDestroyView()
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        _binding = FragmentMessageBinding.bind(view)
        // Set the message for the current Fragment
        binding.tvMessage.text = getString(R.string.message, requireArguments().getLong(ID))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear resources to make them eligible for garbage collection
        _binding = null
    }
}