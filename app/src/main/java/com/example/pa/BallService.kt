package com.example.pa
import android.app.Service
import android.widget.Toast
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
class BallService : Service() {
    lateinit var myPlayer: MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        myPlayer = MediaPlayer.create(this, R.raw.threebigballs)
        myPlayer?.start()
        myPlayer.setOnCompletionListener { _ ->
            onDestroy()
        }
        return START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        myPlayer.stop()
    }
}
