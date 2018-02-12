package com.alesterlewis.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSingle: Button = findViewById(R.id.btnPlaySingle)
        val btnStandalone: Button = findViewById(R.id.btnStandAlone)
        btnSingle.setOnClickListener(this)
        btnStandalone.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        var intent: Intent? = null

        when(view!!.id)
        {
            R.id.btnPlaySingle -> intent = Intent(this, YoutubeActivity().javaClass)

            R.id.btnStandAlone -> intent = Intent(this, StandaloneActivity().javaClass)

            else -> {}
        }

        if(intent!=null)
        {
            startActivity(intent)
        }
    }
}
