package com.sky.youtubeplayertest

import android.util.Log
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

class YoutubeListener : AbstractYouTubePlayerListener() {

    var isReady = false
        private set
    var currentPlayer: YouTubePlayer? = null
        private set
    var currentSec = 0f
        private set

    var onReadyListener: (youTubePlayer: YouTubePlayer) -> Unit = {
        Log.d("YoutubeViewHelper", "no onReady listener set!!")
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        Log.d("YoutubeViewHelper", "onReady")
        isReady = true
        currentPlayer = youTubePlayer
        onReadyListener(youTubePlayer)
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        Log.d("YoutubeViewHelper", "onStateChange")

    }

    override fun onPlaybackQualityChange(
        youTubePlayer: YouTubePlayer,
        playbackQuality: PlayerConstants.PlaybackQuality
    ) {
        Log.d("YoutubeViewHelper", "onPlaybackQualityChange")

    }

    override fun onPlaybackRateChange(
        youTubePlayer: YouTubePlayer,
        playbackRate: PlayerConstants.PlaybackRate
    ) {
        Log.d("YoutubeViewHelper", "onPlaybackRateChange")

    }

    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
        Log.d("YoutubeViewHelper", "onError $error")

    }

    override fun onApiChange(youTubePlayer: YouTubePlayer) {
        Log.d("YoutubeViewHelper", "onApiChange")

    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        currentSec = second
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
        Log.d("YoutubeViewHelper", "onVideoDuration $duration")

    }

    override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {

    }

    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
        Log.d("YoutubeViewHelper", "onVideoId $videoId")

    }
}

fun YouTubePlayerView.setCustomUi(
    playFrom: Float,
    player: YouTubePlayer,
    onFullScreenClick: (playFrom: Float) -> Unit
) {
    val uiController = DefaultPlayerUiController(this, player)
    this.setCustomPlayerUi(uiController.rootView)

    uiController.setFullScreenButtonClickListener {
        onFullScreenClick(playFrom)
    }
}