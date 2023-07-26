package com.jamesm10101.astraeus.views

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.jamesm10101.astraeus.viewModels.MainViewModel

open class MainBaseFragment : Fragment() {

    /**
     * Interacts with the viewModel to hide an active TouchImageFull or pop the backstack
     *
     * @param viewModel the MainViewModel to interact with
     */
    fun handleBackPress(viewModel: MainViewModel) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (viewModel.showTouchImage.value == true) {
                viewModel.toggleShowTouchImage()
            } else {
                parentFragmentManager.popBackStack()
            }
        }
    }
}