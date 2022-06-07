package xyz.miaoguoge.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyCollectionActivity : AppCompatActivity() {
    private var songList : ArrayList<Song> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_play)
        initSong()
        var recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = SongAdapter(songList)
        recyclerView.adapter = adapter

    }
    private fun initSong() {
        songList = Global.Recent as ArrayList<Song>
    }
}