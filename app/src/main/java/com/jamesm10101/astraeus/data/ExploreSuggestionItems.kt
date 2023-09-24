package com.jamesm10101.astraeus.data

/**
 * Class representing a collection of ExploreSuggestionItem instances.
 */
class ExploreSuggestionItems {

    companion object {

        private val exploreItems: List<ExploreSuggestionItem> = listOf(
            ExploreSuggestionItem(
                "Andromeda",
                "https://www.nasa.gov/sites/default/files/thumbnails/image/m31-mosaic-with-groundbased-image.jpg",
                ExploreSuggestionEnums.SEARCH
            ),
            ExploreSuggestionItem(
                "Perseverance",
                "https://mars.nasa.gov/layout/mars2020/images/PIA23764-RoverNamePlateonMars-web.jpg",
                ExploreSuggestionEnums.ROVER
            ),
            ExploreSuggestionItem(
                "APOD",
                "https://www.nasa.gov/sites/default/files/thumbnails/image/apod_logo.png",
                ExploreSuggestionEnums.APOD
            ),
            ExploreSuggestionItem(
                "Juno",
                "https://www.nasa.gov/sites/default/files/styles/full_width/public/images/492704main_junoartist200904-full_full.jpg",
                ExploreSuggestionEnums.SEARCH
            ),
            ExploreSuggestionItem(
                "Opportunity",
                "https://upload.wikimedia.org/wikipedia/commons/d/d8/NASA_Mars_Rover.jpg",
                ExploreSuggestionEnums.ROVER
            ),
            ExploreSuggestionItem(
                "Black Holes",
                "https://www.nasa.gov/sites/default/files/styles/ubernode_alt_horiz/public/thumbnails/image/simulated_bh.jpg",
                ExploreSuggestionEnums.SEARCH
            ),
            ExploreSuggestionItem(
                "Milky Way",
                "https://exoplanets.nasa.gov/internal_resources/698",
                ExploreSuggestionEnums.SEARCH
            ),
            ExploreSuggestionItem(
                "Curiosity",
                "https://mars.nasa.gov/system/feature_items/images/6037_msl_banner.jpg",
                ExploreSuggestionEnums.ROVER
            ),
            ExploreSuggestionItem(
                "Spirit",
                "https://mars.nasa.gov/layout/mars2020/images/PIA23764-RoverNamePlateonMars-web.jpg",
                ExploreSuggestionEnums.ROVER
            )
        )

        /**
         * Get the list of ExploreSuggestionItem instances.
         *
         * @return A list of predefined ExploreSuggestionItem objects.
         */
        fun getItems(): List<ExploreSuggestionItem> {
            return exploreItems
        }
    }
}