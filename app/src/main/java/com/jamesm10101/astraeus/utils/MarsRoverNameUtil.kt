package com.jamesm10101.astraeus.utils

import com.jamesm10101.astraeus.data.MarsRoverName

/**
 * Returns the MarsRoverName enumerator corresponding to the [roverName],
 * and MarsRoverName.OPPORTUNITY when [roverName] is not recognized.
 *
 * @see[MarsRoverName]
 * @param roverName a string representation of the mars rover
 * @return the roverName's corresponding MarsRoverName enum
 */
fun getRoverNameEnum(roverName: String): MarsRoverName {
    return when (roverName.lowercase()) {
        "curiosity" -> MarsRoverName.CURIOSITY
        "perseverance" -> MarsRoverName.PERSEVERANCE
        "spirit" -> MarsRoverName.SPIRIT
        "opportunity" -> MarsRoverName.OPPORTUNITY
        else -> MarsRoverName.OPPORTUNITY
    }
}