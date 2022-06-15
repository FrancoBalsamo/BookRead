package com.frabasoft.bookread.clases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AsyncTaskViewModel : ViewModel() {
    fun <R> execute(
        onPreExecute: () -> Unit,
        doInBackground: () -> R,
        onPostExecute: (R) -> Unit
    ) = viewModelScope.launch {
        onPreExecute()
        val result = withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
            doInBackground()
        }
        onPostExecute(result)
    }
}