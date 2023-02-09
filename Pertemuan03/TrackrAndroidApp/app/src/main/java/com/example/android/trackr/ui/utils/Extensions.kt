

package com.example.android.trackr.ui.utils

import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Convenience for the pattern of launching coroutines when the View lifecycle is in a certain
 * minimum state and canceling those coroutines when outside of that minimum state. Example:
 *
 * ```
 * repeatWithViewLifecycle {
 *   launch {
 *     viewModel.flowA.collect { ... }
 *   }
 *   launch {
 *     viewModel.flowB.collect { ... }
 *   }
 * }
 * ```
 *
 * @param minState The minimum lifecycle state. Defaults to [Lifecycle.State.STARTED].
 * @param block The code block to run.
 */
inline fun Fragment.repeatWithViewLifecycle(
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    if (minState == Lifecycle.State.INITIALIZED || minState == Lifecycle.State.DESTROYED) {
        throw IllegalArgumentException("minState must be between INITIALIZED and DESTROYED")
    }
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minState) {
            block()
        }
    }
}

fun Fragment.requireFragment(id: Int): Fragment {
    return childFragmentManager.findFragmentById(id) ?: throw IllegalArgumentException()
}

inline val View.isRtl: Boolean
    get() = ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL
