/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex15_viewpager2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Holds the list of fragments Id.
 */
class MessagesViewModel : ViewModel() {

    // Backing property for the list of Id (empty by default)
    private val _messagesList = MutableLiveData(listOf<Long>())

    // List of Ids
    val messagesList: LiveData<List<Long>>
        get() = _messagesList

    /**
     * Adds a new Id to the list.
     * This Id will be obtained by increasing by one the last ID of the list.
     */
    fun addMessage() {
        // If the list is empty then add the first Id (0)
        if (_messagesList.value?.isEmpty() != false) {
            _messagesList.value = listOf(0)
        } else {
            // Add a new Id by increasing the last Id in one
            _messagesList.value =
                _messagesList.value?.plus(_messagesList.value!![_messagesList.value!!.lastIndex] + 1)
        }
    }

    /**
     * Removes the Id located at the given position.
     */
    fun removeMessage(position: Int) {
        _messagesList.value = _messagesList.value!!.minus(_messagesList.value!![position])
    }

}