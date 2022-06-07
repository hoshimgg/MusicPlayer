package xyz.miaoguoge.musicplayer

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer

//class MyMediaPlayer : MediaPlayer() {
//    override fun setOnCompletionListener(listener: OnCompletionListener?) {
//        super.setOnCompletionListener(listener)
//        stop()
//        release()
//        Config.setNextIndex()
//        val localFd = assetManager.openFd(Config.musicList[Config.currentMusic])
//        Config.mediaPlayer = MediaPlayer()
//        Config.mediaPlayer.setDataSource(localFd.fileDescriptor, localFd.startOffset, localFd.length)
//        Config.mediaPlayer.prepare()
//        Config.mediaPlayer.start()
//        Config.mmr.setDataSource(localFd.fileDescriptor, localFd.startOffset, localFd.length)
//        updateInfo()
//        Config.inAutoNext = true
//    }
//}

object Config {
    var mediaPlayer = MediaPlayer()
    val mmr = MediaMetadataRetriever()
    var isLoaded = false
    val musicList = listOf(
        "snow_halation.mp3",
        "and_I_m_home.mp3",
        "connect.mp3",
        "dangodaikazoku.mp3",
        "only_my_railgun.mp3",
        "secret_base.mp3",
        "senbonsakura.mp3"
    )
    var currentMusic = 0
    var playMode = "all"

    fun StartPlay() {
        val CurSong = Global.getSongByFilename(musicList[currentMusic])
        if(CurSong !in Global.Recent){
            if (CurSong != null) {
                Global.Recent.add(CurSong)
            }
        }
        mediaPlayer.start()
    }
    var inAutoNext = false

    fun setNextIndex() {
        when (playMode) {
            "all" -> {
                if (currentMusic < musicList.size - 1) {
                    currentMusic++
                } else {
                    currentMusic = 0
                }
            }
            "shuffle" -> {
                val indexList = (0 until currentMusic) + (currentMusic + 1 until musicList.size)
                currentMusic = indexList.random()
            }
        }
    }
}
