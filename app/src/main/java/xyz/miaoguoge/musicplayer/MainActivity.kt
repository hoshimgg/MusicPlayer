package xyz.miaoguoge.musicplayer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val rlPlayBar: RelativeLayout = findViewById(R.id.rlPlayBar)
        rlPlayBar.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }
}
