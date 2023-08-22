package com.jamesm10101.astraeus.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsRoverSpecs(
    val detailedImage: String,
    val siteUrl: String,
    val instruments: List<MarsRoverInstrument>
) : Parcelable

val _perseverance = MarsRoverSpecs(
    detailedImage = "https://mars.nasa.gov/system/resources/detail_files/25045_Perseverance_Mars_Rover_Instrument_Labels-web.jpg",
    siteUrl = "https://mars.nasa.gov/mars2020/spacecraft/instruments/",
    instruments = listOf(
        MarsRoverInstrument(
            "Mastcam-Z",
            "An advanced camera system with panoramic and stereoscopic imaging capability with the ability to zoom. The instrument also will determine mineralogy of the Martian surface and assist with rover operations.",
            "https://mars.nasa.gov/layout/mars2020/images/mastcam-web.jpg"
        ),
        MarsRoverInstrument(
            "MEDA (Mars Environmental Dynamics Analyzer)",
            "A set of sensors that will provide measurements of temperature, wind speed and direction, pressure, relative humidity and dust size and shape.",
            "https://mars.nasa.gov/layout/mars2020/images/meda-500x300.jpg"
        ),
        MarsRoverInstrument(
            "MOXIE (Mars Oxygen ISRU Experiment)",
            "An exploration technology investigation that will produce oxygen from Martian atmospheric carbon dioxide.",
            "https://mars.nasa.gov/layout/mars2020/images/moxie-500x300.jpg"
        ),
        MarsRoverInstrument(
            "PIXL (Planetary Instrument for X-ray Lithochemistry)",
            "An X-ray fluorescence spectrometer that will also contain an imager with high resolution to determine the fine scale elemental composition of Martian surface materials. PIXL will provide capabilities that permit more detailed detection and analysis of chemical elements than ever before.",
            "https://mars.nasa.gov/layout/mars2020/images/pixl-500x300.jpg"
        ),
        MarsRoverInstrument(
            "RIMFAX (Radar Imager for Mars' Subsurface Experiment)",
            "A ground-penetrating radar that will provide centimeter-scale resolution of the geologic structure of the subsurface.",
            "https://mars.nasa.gov/layout/mars2020/images/rimfax-500x300.jpg"
        ),
        MarsRoverInstrument(
            "SHERLOC (Scanning Habitable Environments with Raman & Luminescence for Organics and Chemicals)",
            "A spectrometer that will provide fine-scale imaging and uses an ultraviolet (UV) laser to determine fine-scale mineralogy and detect organic compounds. SHERLOC will be the first UV Raman spectrometer to fly to the surface of Mars and will provide complementary measurements with other instruments in the payload.",
            "https://mars.nasa.gov/layout/mars2020/images/sherloc-500x300.jpg"
        ),
        MarsRoverInstrument(
            "SuperCam",
            "An instrument that takes images, analyzes chemical composition, studies minerals, and records audio with a microphone. The instrument can detect the presence of chemicals in rocks and regolith -- loose rock and dust on top of a rock -- from a distance. This instrument was built and is operated as a partnership between Los Alamos National Laboratory (Los Alamos, New Mexico, USA) and the Centre National d'Etudes Spatiales, France.",
            "https://mars.nasa.gov/layout/mars2020/images/supercam-500x300.jpg"
        ),
    )
)

val _curiosity = MarsRoverSpecs(
    detailedImage = "https://mars.nasa.gov/internal_resources/629/",
    siteUrl = "https://mars.nasa.gov/msl/spacecraft/instruments/summary/",
    instruments = listOf(
        MarsRoverInstrument(
            "Mastcam (Mast Camera)",
            "The Mast Camera, or Mastcam for short, takes color images and color video footage of the Martian terrain. The images can be stitched together to create panoramas of the landscape around the rover.",
            "https://mars.nasa.gov/internal_resources/630"
        ),
        MarsRoverInstrument(
            "MAHLI (Mars Hand Lens Imager)",
            "The Mars Hand Lens Imager, called MAHLI, is the rover's version of the magnifying hand lens that geologists usually carry with them into the field. MAHLI's close-up images reveal the minerals and textures in rock surfaces.",
            "https://mars.nasa.gov/internal_resources/635"
        ),
        MarsRoverInstrument(
            "MARDI (Mars Descent Imager)",
            "The Mars Descent Imager, nicknamed \"MARDI,\" shot a color video of the terrain below as the rover descended to its landing site. The video helped mission planners select the best path for Curiosity when the rover started exploring Gale Crater.",
            "https://mars.nasa.gov/internal_resources/634"
        ),
        MarsRoverInstrument(
            "APXS (Alpha Particle X-Ray Spectrometer)",
            "The Alpha Particle X-Ray Spectrometer is called APXS for short. When it is placed right next to a rock or soil surface, it uses two kinds of radiation to measure the amounts and types of chemical elements that are present.",
            "https://mars.nasa.gov/internal_resources/636"
        ),
        MarsRoverInstrument(
            "ChemCam (Chemistry and Camera)",
            "The Chemistry and Camera tool is known as ChemCam. ChemCam's laser, camera and spectrograph work together to identify the chemical and mineral composition of rocks and soils.",
            "https://mars.nasa.gov/internal_resources/637"
        ),
        MarsRoverInstrument(
            "CheMin (Chemical and Mineralogy)",
            "The Chemical and Mineralogy instrument, or CheMin for short, performs chemical analysis of powdered rock samples to identify the types and amounts of different minerals that are present.The Chemical and Mineralogy instrument, or CheMin for short, performs chemical analysis of powdered rock samples to identify the types and amounts of different minerals that are present.",
            "https://mars.nasa.gov/internal_resources/638"
        ),

        MarsRoverInstrument(
            "SAM (Sample Analysis at Mars)",
            "The Sample Analysis at Mars tool is called SAM. SAM is made up of three different instruments that search for and measure organic chemicals and light elements that are important ingredients potentially associated with life.",
            "https://mars.nasa.gov/internal_resources/639"
        ),
        MarsRoverInstrument(
            "RAD (Radiation Assessment Detector)",
            "The Radiation Assessment Detector is also known as RAD, and is helping prepare for future human exploration of Mars. RAD measures the type and amount of harmful radiation that reaches the Martian surface from the sun and space sources.",
            "https://mars.nasa.gov/internal_resources/640"
        ),
        MarsRoverInstrument(
            "DAN (Dynamic Albedo Of Neutrons)",
            "The Dynamic Albedo of Neutrons tool, called DAN for short, looks for telltale changes in the way neutrons released from Martian soil that indicate liquid or frozen water exists underground.",
            "https://mars.nasa.gov/internal_resources/641"
        ),
        MarsRoverInstrument(
            "REMS (Rover Environmental Monitoring Station)",
            "The Rover Environmental Monitoring Station is nicknamed REMS, and it contains all the weather instruments needed to provide daily and seasonal reports on meteorological conditions around the rover.",
            "https://mars.nasa.gov/internal_resources/642"
        ),
        MarsRoverInstrument(
            "MEDLI (Mars Science Laboratory Entry Descent and Landing Instrument)",
            "The Mars Science Laboratory Entry Descent and Landing Instrument is called MEDLI. MEDLI measured the heating and atmospheric pressure changes that occurred during the descent to help determine the effects on different parts of the spacecraft.",
            "https://mars.nasa.gov/internal_resources/643"
        ),
    )
)

val _spirit = MarsRoverSpecs(
    detailedImage = "https://mars.nasa.gov/imgs/mer/instrument-main.jpg",
    siteUrl = "https://mars.nasa.gov/mer/mission/rover/",
    instruments = listOf(
        MarsRoverInstrument(
            "Panoramic Camera (Pancam)",
            "The Panoramic Camera is known by its nickname, Pancam. Two cameras work in combination to take detailed, multi-wavelength, 3-D panoramic pictures of the Martian landscape surrounding the rover.",
            "https://mars.nasa.gov/images/mer/instruments/pancam-PIA05187.jpg"
        ),
        MarsRoverInstrument(
            "Microscopic Imager (MI)",
            "The Microscopic Imager on the Spirit and Opportunity Rovers of the Mars Exploration Rovers is also known as MI. The high-resolution camera gives magnifying glass-views of Martian rocks and soils. It scouts promising targets for detailed study and analysis by other instruments on the rover.",
            "https://mars.nasa.gov/imgs/mer/instruments/mi1_500.jpg"
        ),
        MarsRoverInstrument(
            "Miniature Thermal Emission Spectrometer (Mini-TES)",
            "The Miniature Thermal Emission Spectrometer is called Mini-TES for short. Mini-TES measures the different spectrums of infrared light, or heat, emitted from different minerals in rocks and soils. Mini-TES is specially tuned to look for minerals formed in water.",
            "https://mars.nasa.gov/imgs/mer/instruments/minites1_400.jpg"
        ),
        MarsRoverInstrument(
            "Mössbauer Spectrometer (MB)",
            "The Mössbauer Spectrometer on the Mars Exploration Rovers, Spirit and Opportunity, is known as MB. The MB determines the makeup and quantities of iron-bearing minerals in geological samples studied by the rover. MB can be placed right up to rock and soil samples for close-up study, and it also examines magnetic dust samples collected by the Magnetic Array on the rover's deck.",
            "https://mars.nasa.gov/layout/mer/images/PIA05111-web.jpg"
        ),
        MarsRoverInstrument(
            "Alpha Particle X-Ray Spectrometer (APXS)",
            "The Alpha Particle X-Ray Spectrometer on the Mars Exploration Rovers, Spirit and Opportunity, is also called the APXS. The APXS reveals the elemental chemistry of rocks and soils by measuring the distinctive way difference materials respond to two kinds of radiation: X-rays and alpha particles.",
            "https://mars.nasa.gov/imgs/mer/instruments/apxs-full.jpg"
        ),
        MarsRoverInstrument(
            "Rock Abrasion Tool (RAT)",
            "The Rock Abrasion Tool on the Mars Exploration Rovers, Spirit and Opportunity, is known as the RAT. The RAT's rotating, grinding teeth gnaw into the surface of Martian rock to reveal fresh mineral surfaces for analysis by the rover's scientific tools.",
            "https://mars.nasa.gov/imgs/mer/instruments/rat-2.jpg"
        ),
        MarsRoverInstrument(
            "Magnet Array",
            "The Magnet Array is a scientific experiment that collects dust on the Mars Exploration Rovers, Spirit and Opportunity. Magnetic grains in Martian dust are tiny pieces of the Red Planet's past. The Magnetic Array collects the dust for analysis by scientific tools that identify the composition and present clues on the history of the dust particles.",
            "https://mars.nasa.gov/imgs/mer/instruments/PIA05729.jpg"
        ),
        MarsRoverInstrument(
            "Calibration Targets",
            "The instruments use calibration targets, including a sundial, to determine accurate colors, brightnesses, and other information collected by the instruments.",
            "https://mars.nasa.gov/imgs/mer/instruments/mer-calibration-target-sundial1_500.jpg"
        ),
    )
)

val _opportunity = MarsRoverSpecs(
    detailedImage = "https://mars.nasa.gov/imgs/mer/instrument-main.jpg",
    siteUrl = "https://mars.nasa.gov/mer/mission/rover/",
    instruments = listOf(
        MarsRoverInstrument(
            "Panoramic Camera (Pancam)",
            "The Panoramic Camera is known by its nickname, Pancam. Two cameras work in combination to take detailed, multi-wavelength, 3-D panoramic pictures of the Martian landscape surrounding the rover.",
            "https://mars.nasa.gov/images/mer/instruments/pancam-PIA05187.jpg"
        ),
        MarsRoverInstrument(
            "Microscopic Imager (MI)",
            "The Microscopic Imager on the Spirit and Opportunity Rovers of the Mars Exploration Rovers is also known as MI. The high-resolution camera gives magnifying glass-views of Martian rocks and soils. It scouts promising targets for detailed study and analysis by other instruments on the rover.",
            "https://mars.nasa.gov/imgs/mer/instruments/mi1_500.jpg"
        ),
        MarsRoverInstrument(
            "Miniature Thermal Emission Spectrometer (Mini-TES)",
            "The Miniature Thermal Emission Spectrometer is called Mini-TES for short. Mini-TES measures the different spectrums of infrared light, or heat, emitted from different minerals in rocks and soils. Mini-TES is specially tuned to look for minerals formed in water.",
            "https://mars.nasa.gov/imgs/mer/instruments/minites1_400.jpg"
        ),
        MarsRoverInstrument(
            "Mössbauer Spectrometer (MB)",
            "The Mössbauer Spectrometer on the Mars Exploration Rovers, Spirit and Opportunity, is known as MB. The MB determines the makeup and quantities of iron-bearing minerals in geological samples studied by the rover. MB can be placed right up to rock and soil samples for close-up study, and it also examines magnetic dust samples collected by the Magnetic Array on the rover's deck.",
            "https://mars.nasa.gov/layout/mer/images/PIA05111-web.jpg"
        ),
        MarsRoverInstrument(
            "Alpha Particle X-Ray Spectrometer (APXS)",
            "The Alpha Particle X-Ray Spectrometer on the Mars Exploration Rovers, Spirit and Opportunity, is also called the APXS. The APXS reveals the elemental chemistry of rocks and soils by measuring the distinctive way difference materials respond to two kinds of radiation: X-rays and alpha particles.",
            "https://mars.nasa.gov/imgs/mer/instruments/apxs-full.jpg"
        ),
        MarsRoverInstrument(
            "Rock Abrasion Tool (RAT)",
            "The Rock Abrasion Tool on the Mars Exploration Rovers, Spirit and Opportunity, is known as the RAT. The RAT's rotating, grinding teeth gnaw into the surface of Martian rock to reveal fresh mineral surfaces for analysis by the rover's scientific tools.",
            "https://mars.nasa.gov/imgs/mer/instruments/rat-2.jpg"
        ),
        MarsRoverInstrument(
            "Magnet Array",
            "The Magnet Array is a scientific experiment that collects dust on the Mars Exploration Rovers, Spirit and Opportunity. Magnetic grains in Martian dust are tiny pieces of the Red Planet's past. The Magnetic Array collects the dust for analysis by scientific tools that identify the composition and present clues on the history of the dust particles.",
            "https://mars.nasa.gov/imgs/mer/instruments/PIA05729.jpg"
        ),
        MarsRoverInstrument(
            "Calibration Targets",
            "The instruments use calibration targets, including a sundial, to determine accurate colors, brightnesses, and other information collected by the instruments.",
            "https://mars.nasa.gov/imgs/mer/instruments/mer-calibration-target-sundial1_500.jpg"
        ),
    )
)