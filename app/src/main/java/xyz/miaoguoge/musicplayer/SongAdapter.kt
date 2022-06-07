package xyz.miaoguoge.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> mSongList = new ArrayList<>();
    static class ViewHolder extends RecyclerView.ViewHolder{
        View SongView;
        ImageView SongBitmap;
        TextView SongTitle;
        TextView SongArtist;

        public ViewHolder(@NonNull View view) {
            super(view);
            SongView = view;
            SongBitmap = view.findViewById(R.id.song_bitmap);
            SongTitle = view.findViewById(R.id.song_title);
            SongArtist = view.findViewById(R.id.song_artist);

        }
    }

    public SongAdapter(List<Song> mSongList) {
        this.mSongList = mSongList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = mSongList.get(position);
        holder.SongBitmap.setImageBitmap(song.bitmap);
        holder.SongTitle.setText(song.Title);
        holder.SongArtist.setText(song.Artist);
    }
    @Override
    public int getItemCount(){
        return mSongList.size();
    }
}


