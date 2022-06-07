package xyz.miaoguoge.musicplayer

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import xyz.miaoguoge.musicplayer.databinding.ActivityPlayerBinding
import java.util.*

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding

    val updateSeekbar = 1

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateSeekbar -> {
                    val duration = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toInt()
                    val currentPosition = Config.mediaPlayer.currentPosition
                    binding.seekBar.progress = 100 * currentPosition / duration
                    val currentTime = String.format("%d:%02d", currentPosition / 1000 / 60, currentPosition / 1000 % 60)
                    binding.tvCurrentTime.text = currentTime
                }
            }
        }
    }
    private val timerTask: TimerTask = object : TimerTask() {
        override fun run() {
            if (Config.mediaPlayer.isPlaying && !binding.seekBar.isPressed) {
                handler.sendEmptyMessage(updateSeekbar) // 发送消息
            }
        }
    }
    private val timer = Timer()


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
            timer.schedule(timerTask, 0, 1000)
        }

        binding.btnPlay.setOnClickListener {
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
            Config.mediaPlayer.start()
            updateInfo()
            if (!Config.isLoaded) {
                timer.schedule(timerTask, 0, 1000)
            }
            Config.isLoaded = true
        }
        binding.btnPause.setOnClickListener {
            binding.btnPause.visibility = Button.INVISIBLE
            binding.btnPlay.visibility = Button.VISIBLE
            Config.mediaPlayer.pause()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d("PlayerActivity", "seek to $progress")
                    val duration = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toInt()
                    val msec = progress * duration / 100
                    Config.mediaPlayer.seekTo(msec)
                    val currentTime = String.format("%d:%02d", msec / 1000 / 60, msec / 1000 % 60)
                    binding.tvCurrentTime.text = currentTime
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        binding.btnNext.setOnClickListener {
            Config.mediaPlayer.stop()
            Config.mediaPlayer.release()
            val assetManager = assets
            if (Config.currentMusic < Config.musicList.size - 1) {
                Config.currentMusic++
            } else {
                Config.currentMusic = 0
            }
            val fd = assetManager.openFd(Config.musicList[Config.currentMusic])
            Config.mediaPlayer = MediaPlayer()
            Config.mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            Config.mediaPlayer.prepare()
            Config.mediaPlayer.start()
            Config.mmr.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            updateInfo()
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
        }
        binding.btnPrevious.setOnClickListener {
            Config.mediaPlayer.stop()
            Config.mediaPlayer.release()
            val assetManager = assets
            if (Config.currentMusic > 0) {
                Config.currentMusic--
            } else {
                Config.currentMusic = Config.musicList.size - 1
            }
            val fd = assetManager.openFd(Config.musicList[Config.currentMusic])
            Config.mediaPlayer = MediaPlayer()
            Config.mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            Config.mediaPlayer.prepare()
            Config.mediaPlayer.start()
            Config.mmr.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            updateInfo()
            binding.btnPlay.visibility = Button.INVISIBLE
            binding.btnPause.visibility = Button.VISIBLE
        }
    }

    private fun updateInfo() {
        binding.tvSongTitle.text = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        binding.tvSongArtist.text = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val duration = Config.mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toInt()
        val endTime = String.format("%d:%02d", duration / 1000 / 60, duration / 1000 % 60)
        binding.tvEndTime.text = endTime
        val cover = Config.mmr.embeddedPicture
        if (cover != null) {
            val bitmap = BitmapFactory.decodeByteArray(cover, 0, cover.size)
            binding.ivAlbumCover.setImageBitmap(bitmap)
        }
    }
}
