package xyz.miaoguoge.musicplayer

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import xyz.miaoguoge.musicplayer.Config.mediaPlayer
import xyz.miaoguoge.musicplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val assetManager = assets
        val fd = assetManager.openFd("snow_halation.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        val cover = mmr.embeddedPicture

        binding.btnPlay.setOnClickListener {
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
            mediaPlayer.start()
            binding.songTitle.text = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            binding.songArtist.text = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            if (cover != null) {
                val bitmap = BitmapFactory.decodeByteArray(cover, 0, cover.size)
                binding.imgCover.setImageBitmap(bitmap)
            }
        }
        binding.btnPause.setOnClickListener {
            binding.btnPause.visibility = Button.INVISIBLE
            binding.btnPlay.visibility = Button.VISIBLE
            mediaPlayer.pause()
        }
        binding.rlPlayBar.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
