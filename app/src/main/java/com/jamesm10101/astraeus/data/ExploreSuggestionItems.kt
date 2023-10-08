package com.jamesm10101.astraeus.data

/**
 * Class representing a collection of ExploreSuggestionItem instances.
 */
class ExploreSuggestionItems {

    companion object {

        private val exploreItems: List<ExploreSuggestionItem> = listOf(
            ExploreSuggestionItem(
                "Andromeda",
                "https://smd-cms.nasa.gov/wp-content/uploads/2023/04/m31-mosaic-with-groundbased-image-jpg.webp?resize=1024,1024",
                ExploreSuggestionEnums.SEARCH
            ),
            ExploreSuggestionItem(
                "Perseverance",
                "https://mars.nasa.gov/layout/mars2020/images/PIA23764-RoverNamePlateonMars-web.jpg",
                ExploreSuggestionEnums.ROVER
            ),
            ExploreSuggestionItem(
                "APOD",
                "https://www.nasa.gov/wp-content/uploads/2023/03/apod-logo.png",
                ExploreSuggestionEnums.APOD
            ),
            ExploreSuggestionItem(
                "Juno",
                "https://smd-cms.nasa.gov/wp-content/uploads/2023/03/jpegpia21771.width-1600-jpg.webp?resize=1546,2000",
                ExploreSuggestionEnums.SEARCH
            ),
            ExploreSuggestionItem(
                "Opportunity",
                "https://upload.wikimedia.org/wikipedia/commons/d/d8/NASA_Mars_Rover.jpg",
                ExploreSuggestionEnums.ROVER
            ),
            ExploreSuggestionItem(
                "Black Holes",
                "https://smd-cms.nasa.gov/wp-content/uploads/2023/06/blackhole-binary-mainsequence-jpg.webp?resize=2048,2048",
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