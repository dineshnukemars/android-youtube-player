package com.sky.youtubeplayertest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    private val youtubeListener = YoutubeListener()
    private lateinit var playerView: YouTubePlayerView

    val url = "https://www.youtube.com/watch?v=HVjJQPnyfjE"
    val videoId = getVideoIdFromYoutubeUrl(url)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playerView = createYouTubePlayerView(this)

        setContent {
            PlayerContainer(playerView)
        }
    }

    private fun initPlayer(
        playerView: YouTubePlayerView
    ) {

        youtubeListener.onReadyListener = { player ->

            Log.d("SecondActivity", "onReadyListener ")

            player.cueVideo(videoId, 0f)
            playerView.setCustomUi(
                playFrom = 0f,
                player = player,
                onFullScreenClick = { currentTime ->
                    Log.d("SecondActivity", "onFullScreenClick ")
                }
            )
        }


        try {
            Log.d("SecondActivity", "set Init and youtubeListener ")
            playerView.initialize(
                youTubePlayerListener = youtubeListener,
                playerOptions = IFramePlayerOptions.Builder().controls(0).build()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @Composable
    fun PlayerContainer(playerView: YouTubePlayerView) {
        Column(modifier = Modifier.fillMaxSize()) {

            Log.d("SecondActivity", "Compose recompose 1 ")

            var isUpdateKey by remember {
                mutableStateOf(-1)
            }

            if (isUpdateKey != -1) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth(),
                    factory = {
                        Log.d("SecondActivity", "Compose player factory ")
                        playerView
                    },
                    update = {
                        Log.d("SecondActivity", "Compose player update ")

                    }
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    isUpdateKey = Random.nextInt()

//                    val url2 = getVideoIdFromYoutubeUrl("https://www.youtube.com/watch?v=Fxz7dMw3vZA")
//                    youtubeListener.currentPlayer?.loadVideo(url2,0f)
//                    Log.d("SecondActivity", "youtubeListener.currentPlayer ${youtubeListener.currentPlayer} ")
                }
            ) {
                Text(text = "Int player")
            }
        }
    }


    private fun createYouTubePlayerView(it: Context): YouTubePlayerView {

        val playerView = YouTubePlayerView(it)
        playerView.enableAutomaticInitialization = false

        lifecycle.addObserver(playerView)

        Log.d("SecondActivity", "createYouTubePlayerView ")
        initPlayer(playerView)
        return playerView
    }
}
