package xyz.miaoguoge.musicplayer

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMediaPlayer()
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        btnPlay.setOnClickListener {
            btnPlay.visibility = Button.INVISIBLE
            btnPause.visibility = Button.VISIBLE
            mediaPlayer.start()
        }
        btnPause.setOnClickListener {
            btnPause.visibility = Button.INVISIBLE
            btnPlay.visibility = Button.VISIBLE
            mediaPlayer.pause()
        }

        val rlPlayBar: RelativeLayout = findViewById(R.id.rlPlayBar)
        rlPlayBar.setOnClickListener {
            startActivity(Intent(this, PlayerActivity::class.java))
        }
    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("snow_halation.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
