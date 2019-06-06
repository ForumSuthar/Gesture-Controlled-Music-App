package com.example.musicapp

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import java.util.ArrayList



class MusicPlayer : AppCompatActivity() {

    var songimg : ImageView? = null
    private var currentSong : Int = 0
    internal lateinit var songtitle: TextView
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

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
        var position:Int = intent.getIntExtra("position", 0)
        currentSong = position
        songtitle.text = songList.get(position)
        playSong()

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


    override fun onBackPressed() {
        pauseSong()
        finish()
    }

}

