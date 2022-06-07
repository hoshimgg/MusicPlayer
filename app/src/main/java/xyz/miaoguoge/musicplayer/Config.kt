package xyz.miaoguoge.musicplayer

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer

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
}
