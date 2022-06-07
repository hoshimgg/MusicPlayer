package xyz.miaoguoge.musicplayer

import android.app.Application
import xyz.miaoguoge.musicplayer.Song
import java.util.ArrayList

object Global : Application() {
    var Favor: ArrayList<Song> = ArrayList()
    var Recent: ArrayList<Song> = ArrayList()
    var All: ArrayList<Song> = ArrayList()

    fun getSongByFilename(filename: String): Song? {
        for (song in All) {
            if (song.Filename == filename) {
                return song
            }
        }
        return null
    }
}
