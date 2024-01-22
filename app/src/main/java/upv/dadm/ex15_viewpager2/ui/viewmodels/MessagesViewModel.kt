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

package upv.dadm.ex15_viewpager2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Holds the list of fragments Id.
 */
class MessagesViewModel : ViewModel() {

    // Backing property for the list of Id (empty by default)
    private val _messagesList = MutableStateFlow(listOf<Long>())

    // List of Ids
    val messagesList = _messagesList.asStateFlow()

    /**
     * Adds a new Id to the list.
     * This Id will be obtained by increasing by one the last ID of the list.
     */
    fun addMessage() {
        // If the list is empty then add the first Id (0)
        if (_messagesList.value.isEmpty()) {
            _messagesList.update {
                listOf(0)
            }
        } else {
            // Add a new Id by increasing the last Id in one
            _messagesList.update { list ->
                list.plus(list[list.lastIndex] + 1)
            }
        }
    }

    /**
     * Removes the Id located at the given position.
     */
    fun removeMessage(position: Int) {
        _messagesList.update { list ->
            list.minus(list[position])
        }
    }

}