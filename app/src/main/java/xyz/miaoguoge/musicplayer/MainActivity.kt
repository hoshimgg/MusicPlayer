package xyz.miaoguoge.musicplayer

import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val mediaPlayer = MediaPlayer()
    private val mmr = MediaMetadataRetriever()

    private lateinit var assetManager: AssetManager
    private lateinit var fd: AssetFileDescriptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        assetManager = assets
        fd = assetManager.openFd("snow_halation.mp3")
        initMediaPlayer()
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        val imgCover: ImageView = findViewById(R.id.imgCover)
        btnPlay.setOnClickListener {
            btnPlay.visibility = Button.INVISIBLE
            btnPause.visibility = Button.VISIBLE
            mediaPlayer.start()
            // 获取封面
            mmr.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            val cover = mmr.embeddedPicture
            if (cover != null) {
                val bitmap = BitmapFactory.decodeByteArray(cover, 0, cover.size)
                imgCover.setImageBitmap(bitmap)
            }
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
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
