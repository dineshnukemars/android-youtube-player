package com.sky.youtubeplayertest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {
    private val youtubeListener = YoutubeListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val playerView: YouTubePlayerView = findViewById(R.id.custom_player_view)
//
//        playerView.enableAutomaticInitialization = false
//        lifecycle.addObserver(playerView)
//
//        initPlayer(playerView)
//
        findViewById<Button>(R.id.secondActivityBtn).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }


    private fun initPlayer(
        playerView: YouTubePlayerView
    ) {
        if (youtubeListener.isReady) return

        val url = "https://www.youtube.com/watch?v=HVjJQPnyfjE"

        val videoId = getVideoIdFromYoutubeUrl(url)

        youtubeListener.onReadyListener = { player ->
            player.cueVideo(videoId, 0f)
            playerView.setCustomUi(
                playFrom = 0f,
                player = player,
                onFullScreenClick = { currentTime ->
                    Log.d("MainActivity", "onFullScreenClick")
                }
            )
        }
        playerView.initialize(youTubePlayerListener = youtubeListener)
    }
}

fun getVideoIdFromYoutubeUrl(url: String): String {
    val uri: Uri = Uri.parse(url)
    val videoId = uri.getQueryParameter("v") ?: ""
    Log.d("MainActivity", "Youtube video Id $videoId")
    return videoId
}