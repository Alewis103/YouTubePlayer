package com.alesterlewis.youtubeplayer

import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    val GOOGLE_API_KEY: String = "AIzaSyBhBW0aXrwr66YruzsVSx7oPm0kVxmXpl4";
    val YOUTUBE_VIDEO_ID: String = "GnCoT7sg9KY";
    val YOUTUBE_PLAYLIST: String = "PLlu92f1dI_nZeMGzMxQcPviDppMmylh2y";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        /*
        example of creating buttons dynamically

        var button1:Button = Button(this)
         button1.layoutParams = ConstraintLayout.LayoutParams(300,80)
         button1.setText("Button added")
         constraintLayout.addView(button1)
         */

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        constraintLayout.addView(playerView)

        playerView.initialize(GOOGLE_API_KEY, this);
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${p0.toString()}")
        Toast.makeText(this@YoutubeActivity, "Success!", Toast.LENGTH_LONG).show()
        p1!!.setPlaybackEventListener(playbackEventListener)
        p1!!.setPlayerStateChangeListener(playerStateChangeListener)

        if (!p2) {
            p1!!.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }


    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        val REQUEST_CODE: Int = 1;
        if (p1!!.isUserRecoverableError()) {
            p1.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage: String = String.format("there was an error initializing the YouTubePlayer(%1s)", p1.toString())

            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private val playbackEventListener: YouTubePlayer.PlaybackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
            Toast.makeText(this@YoutubeActivity, "Video buffering", Toast.LENGTH_LONG).show()
        }

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Good video is playing okay!", Toast.LENGTH_LONG).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity, "Video is stopped", Toast.LENGTH_LONG).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video is paused", Toast.LENGTH_LONG).show()
        }
    }

    private val playerStateChangeListener: YouTubePlayer.PlayerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Click ad now", Toast.LENGTH_LONG).show()

        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video Started", Toast.LENGTH_LONG).show()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Wasn't that great ?!?!", Toast.LENGTH_LONG).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }

}
