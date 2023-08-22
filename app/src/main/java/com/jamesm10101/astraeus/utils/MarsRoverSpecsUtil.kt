package com.jamesm10101.astraeus.utils

import com.jamesm10101.astraeus.data.MarsRoverName
import com.jamesm10101.astraeus.data.MarsRoverSpecs
import com.jamesm10101.astraeus.data._curiosity
import com.jamesm10101.astraeus.data._opportunity
import com.jamesm10101.astraeus.data._perseverance
import com.jamesm10101.astraeus.data._spirit

/**
 * Gets the MarsRoverSpecs based on [rover] passed in.
 *
 * @param rover the MarsRoveName to get the specs of
 * @return the mars rover specs for the requested rover
 */
fun getRoverSpecs(rover: MarsRoverName): MarsRoverSpecs {
    return when (rover) {
        MarsRoverName.PERSEVERANCE -> _perseverance
        MarsRoverName.CURIOSITY -> _curiosity
        MarsRoverName.SPIRIT -> _spirit
        MarsRoverName.OPPORTUNITY -> _opportunity
    }
}