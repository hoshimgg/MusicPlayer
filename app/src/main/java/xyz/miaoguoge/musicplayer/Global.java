package xyz.miaoguoge.musicplayer;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Global extends Application {
    public static List<Song> Favor = new ArrayList<>();
    public static List<Song> Recent= new ArrayList<>();
    public static List<Song> All= new ArrayList<>();
}
