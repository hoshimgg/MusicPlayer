package xyz.miaoguoge.musicplayer

import android.media.MediaPlayer
import xyz.miaoguoge.musicplayer.Song
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import xyz.miaoguoge.musicplayer.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.ArrayList

class SongAdapter(mSongList: List<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    private var mSongList: List<Song> = ArrayList()

    class ViewHolder(var SongView: View) : RecyclerView.ViewHolder(
        SongView
    ) {
        var SongBitmap: ImageView
        var SongTitle: TextView
        var SongArtist: TextView

        init {
            SongBitmap = SongView.findViewById(R.id.song_bitmap)
            SongTitle = SongView.findViewById(R.id.song_title)
            SongArtist = SongView.findViewById(R.id.song_artist)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.SongView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val song = mSongList[position]
            Toast.makeText(it.context, "你点击了" + song.Title, Toast.LENGTH_SHORT).show()
            Config.mediaPlayer.stop()
            Config.mediaPlayer.release()
            val assetManager = it.context.assets
            //从Config.musicList中获取歌曲下标
            val musicIndex = Config.musicList.indexOf(song.Filename)
            Config.currentMusic = musicIndex
            val fd = assetManager.openFd(song.Filename)
            Config.mediaPlayer = MediaPlayer()
            Config.mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            Config.mediaPlayer.prepare()
            Config.StartPlay()
            Config.mmr.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            Config.isLoaded = true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = mSongList[position]
        holder.SongBitmap.setImageBitmap(song.bitmap)
        holder.SongTitle.text = song.Title
        holder.SongArtist.text = song.Artist
    }

    override fun getItemCount(): Int {
        return mSongList.size
    }

    init {
        this.mSongList = mSongList
    }
}