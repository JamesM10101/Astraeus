package com.jamesm10101.astraeus.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.jamesm10101.astraeus.R
import com.jamesm10101.astraeus.data.APOD
import com.jamesm10101.astraeus.databinding.FragmentApodBinding
import com.jamesm10101.astraeus.viewModels.ApodViewModel
import com.jamesm10101.astraeus.viewModels.MainViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class ApodFragment : MainBaseFragment() {

    private val viewModel: ApodViewModel by viewModels()
    private lateinit var mainViewModel: MainViewModel

    private var apod: APOD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleBackPress(mainViewModel)

        @Suppress("DEPRECATION")
        apod = arguments?.getParcelable("apod")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentApodBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.viewModel = viewModel

        binding.apodResult = when (apod != null) {
            true -> MutableLiveData(apod)
            false -> mainViewModel.apodResult
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apod = when (apod != null) {
            true -> apod
            false -> mainViewModel.apodResult.value
        }

        // load video into the player
        val youTubePlayerView: YouTubePlayerView = view.findViewById(R.id.ytp_apod)
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                try {
                    val videoId = apod?.mediaSrcUrl!!.substring(30, 41)
                    youTubePlayer.loadVideo(videoId, 0f)
                } catch (e: Exception) {
                    Log.d("apodVideoLoad", e.message.toString())
                }
            }
        })

    }

}