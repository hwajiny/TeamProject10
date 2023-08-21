package com.example.teamproject10

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class FloatingActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating)

        val playbtn =  findViewById<ImageButton>(R.id.playbtn)
        val pausebtn =  findViewById<ImageButton>(R.id.pausebtn)
        val stopbtn =  findViewById<ImageButton>(R.id.stopbtn)
        val playbtn2 =  findViewById<ImageButton>(R.id.playbtn2)
        val pausebtn2 =  findViewById<ImageButton>(R.id.pausebtn2)
        val stopbtn2 =  findViewById<ImageButton>(R.id.stopbtn2)
        val playbtn3 =  findViewById<ImageButton>(R.id.playbtn3)
        val pausebtn3 =  findViewById<ImageButton>(R.id.pausebtn3)
        val stopbtn3 =  findViewById<ImageButton>(R.id.stopbtn3)
        val playbtn4 =  findViewById<ImageButton>(R.id.playbtn4)
        val pausebtn4 =  findViewById<ImageButton>(R.id.pausebtn4)
        val stopbtn4 =  findViewById<ImageButton>(R.id.stopbtn4)

        playbtn.setOnClickListener{
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.song_ontheground)
            }
            mediaPlayer?.start()
        }

        pausebtn.setOnClickListener{
            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
        }
        stopbtn.setOnClickListener{
            mediaPlayer?.stop()
            mediaPlayer = null
        }

        //================================================
        playbtn2.setOnClickListener{
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.song_solo)
            }
            mediaPlayer?.start()
        }

        pausebtn2.setOnClickListener{
            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
        }
        stopbtn2.setOnClickListener{
            mediaPlayer?.stop()
            mediaPlayer = null
        }
        //=================================================
        playbtn3.setOnClickListener{
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.song_money)
            }
            mediaPlayer?.start()
        }

        pausebtn3.setOnClickListener{
            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
        }
        stopbtn3.setOnClickListener{
            mediaPlayer?.stop()
            mediaPlayer = null
        }

        //===================================================
        playbtn4.setOnClickListener{
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.song_flower)
            }
            mediaPlayer?.start()
        }

        pausebtn4.setOnClickListener{
            if (mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
        }
        stopbtn4.setOnClickListener{
            mediaPlayer?.stop()
            mediaPlayer = null
        }
    }
}