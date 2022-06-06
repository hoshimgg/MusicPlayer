package xyz.miaoguoge.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        btnPlay.setOnClickListener {
            btnPlay.visibility = Button.INVISIBLE
            btnPause.visibility = Button.VISIBLE
        }
        btnPause.setOnClickListener {
            btnPause.visibility = Button.INVISIBLE
            btnPlay.visibility = Button.VISIBLE
        }
    }
}
