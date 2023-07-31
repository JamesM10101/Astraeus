package com.jamesm10101.astraeus.viewModels

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamesm10101.astraeus.R
import java.lang.Exception

class MarsRoverPhotoViewModel : ViewModel() {

    lateinit var resources: Resources

    private val _showRoverDetails = MutableLiveData<Boolean>()

    // accessors
    val showRoverDetails: LiveData<Boolean> = _showRoverDetails

    /**
     * Creates an onClickListener that exposes the rovers details
     *
     * @return the click listener for the view
     */
    fun toggleShowRoverDetails(): View.OnClickListener {

        return View.OnClickListener {
            try {
                val txtV = it as TextView

                // update the arrow drawable
                val drawable: Drawable? = ResourcesCompat.getDrawable(
                    resources, when (_showRoverDetails.value == true) {
                        true -> R.drawable.ic_arrow_head_down
                        false -> R.drawable.ic_arrow_head_up
                    }, null
                )

                txtV.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)

                // toggle the details visibility
                _showRoverDetails.value = _showRoverDetails.value != true
            } catch (e: Exception) {
                Log.e("showRoverDetails", e.message.toString())
            }
        }
    }

}

