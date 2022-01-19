package com.example.strongerfootballapp.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

fun Fragment.launchLifeCycleScope(action: suspend () -> Unit){
    viewLifecycleOwner.lifecycleScope.launch {
        action()
    }
}

fun Fragment.makeToast(message: String?){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
