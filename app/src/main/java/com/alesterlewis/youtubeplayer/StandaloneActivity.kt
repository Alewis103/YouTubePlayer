package com.alesterlewis.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeStandalonePlayer

/**
 * Created by Alestor Lewis on 2/11/2018.
 */

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        val btnPlayVideo: Button = findViewById(R.id.btnPlayVideo)
        val btnPlayList: Button = findViewById(R.id.btnPlayList)

        btnPlayVideo.setOnClickListener(this)
        btnPlayList.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        var intent: Intent? = null
        val googID: String = YoutubeActivity().GOOGLE_API_KEY
        val vidID: String = YoutubeActivity().YOUTUBE_VIDEO_ID
        val playListID: String = YoutubeActivity().YOUTUBE_PLAYLIST
        when (view!!.id) {
            R.id.btnPlayVideo -> intent = YouTubeStandalonePlayer.createVideoIntent(this, googID, vidID)

            R.id.btnPlayList -> intent = YouTubeStandalonePlayer.createVideoIntent(this, googID, playListID)

            else -> {
            }
        }

        if (intent != null) {
            startActivity(intent)
        }
    }
}
