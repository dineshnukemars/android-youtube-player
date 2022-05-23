package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.Utils

internal class NetworkListener : BroadcastReceiver() {

    var onNetworkUnavailable = { }
    var onNetworkAvailable = { }

    override fun onReceive(context: Context, intent: Intent) {
        if (Utils.isOnline(context))
            onNetworkAvailable()
        else
            onNetworkUnavailable()
    }
}
