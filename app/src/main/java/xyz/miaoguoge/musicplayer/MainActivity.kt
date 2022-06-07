package xyz.miaoguoge.musicplayer

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import xyz.miaoguoge.musicplayer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val assetManager = assets
        val fd = assetManager.openFd("snow_halation.mp3")
        Config.mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        Config.mediaPlayer.prepare()

        Config.mmr.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)

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
        binding.rlPlayBar.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }
        //btnLocal跳转到LocalMusicActivity
        binding.btnLocal.setOnClickListener {
            val intent = Intent(this, LocalMusicActivity::class.java)
            startActivity(intent)
        }
        //btnRecent跳转到RecentPlayActivity
        binding.btnRecent.setOnClickListener {
            val intent = Intent(this, RecentPlayActivity::class.java)
            startActivity(intent)
        }
        //btnFavor跳转到MyCollectionActivity
        binding.btnFavor.setOnClickListener {
            val intent = Intent(this, MyCollectionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        if (Config.isLoaded) {
            updateInfo()
        }
        if (Config.mediaPlayer.isPlaying) {
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
        } else {
            binding.btnPlay.visibility = Button.VISIBLE
            binding.btnPause.visibility = Button.INVISIBLE
        }
    }

    private fun updateInfo() {
        binding.songTitle.text = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        binding.songArtist.text = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val cover = Config.mmr.embeddedPicture
        if (cover != null) {
            val bitmap = BitmapFactory.decodeByteArray(cover, 0, cover.size)
            binding.imgCover.setImageBitmap(bitmap)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Config.mediaPlayer.stop()
        Config.mediaPlayer.release()
    }
}
