package xyz.miaoguoge.musicplayer

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import xyz.miaoguoge.musicplayer.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Config.mediaPlayer.isPlaying) {
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
        }
        if (Config.isLoaded) {
            updateInfo()
        }

        binding.btnPlay.setOnClickListener {
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
            Config.mediaPlayer.start()
            Config.isLoaded = true
            updateInfo()
        }
        binding.btnPause.setOnClickListener {
            binding.btnPause.visibility = Button.INVISIBLE
            binding.btnPlay.visibility = Button.VISIBLE
            Config.mediaPlayer.pause()
        }
    }

    private fun updateInfo() {
        binding.tvSongTitle.text = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        binding.tvSongArtist.text = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val cover = Config.mmr.embeddedPicture
        if (cover != null) {
            val bitmap = BitmapFactory.decodeByteArray(cover, 0, cover.size)
            binding.ivAlbumCover.setImageBitmap(bitmap)
        }
    }
}
