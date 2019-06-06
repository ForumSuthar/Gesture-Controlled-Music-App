package com.example.musicapp

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.Manifest
import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.SearchEvent
import android.widget.*
import android.widget.SeekBar
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var songimg : ImageView? = null
    //private var songList:Array<String>? = null
    internal lateinit var songList:MutableList<String>
    private var currentSong : Int = 0
    internal lateinit var songtitle:TextView
    private var mediaPlayer:MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playbutton = findViewById<ImageButton>(R.id.imageButton4)
        val pausebutton = findViewById<ImageButton>(R.id.imageButton3)
        val nextbutton = findViewById<ImageButton>(R.id.imageButton)
        val previousbutton = findViewById<ImageButton>(R.id.imageButton2)
        songtitle = findViewById<TextView>(R.id.textView)

        val volumeSeekBar = findViewById<SeekBar>(R.id.seekBar)
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //volumeSeekBar.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        //volumeSeekBar.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        //volumeSeekBar.setOnSeekBarChangeListener()


        Log.i("Oncreate", "InOnCreate")
        playbutton.setOnClickListener{
            playSong()
        }

        pausebutton.setOnClickListener{
            pauseSong()
        }

        nextbutton.setOnClickListener{
            nextSong()
        }

        previousbutton.setOnClickListener{
            previousSong()
        }
        Log.i("AfterListner", "InOnCreate")
        //var songList = ArrayList<String>()
        //var fields = resources.assets.list("/Users/ashikarohit/Downloads/musicapp/app/src/main/assets")
        //songList = assets.list("songs")
        songList = ArrayList()
        val fields = R.raw::class.java!!.fields

        for ( i in fields.indices) {
            songList.add (fields[i].name)
            Log.i("fields", fields[i].name)

        }
        songtitle.text = songList.get(currentSong)
    }

    private fun playSong()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer?.release()
        }
        var resID = resources.getIdentifier(songList.get(currentSong),"raw",packageName)
        mediaPlayer = MediaPlayer.create(this,resID)
        mediaPlayer!!.start()
        songtitle.text = songList.get(currentSong)
    }

    private fun pauseSong()
    {
        mediaPlayer?.pause()
    }

    private fun nextSong()
    {
        currentSong = (currentSong + 1)%3
        playSong()
    }

    private fun previousSong()
    {
        if(currentSong == 0)
        {
            currentSong = 2
        }
        else
        {
            currentSong = currentSong - 1
        }
        playSong()
    }


}
