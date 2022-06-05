package xyz.miaoguoge.musicplayer

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import xyz.miaoguoge.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        btnPlay.setOnClickListener {
            btnPlay.visibility = Button.INVISIBLE
            btnPause.visibility = Button.VISIBLE
        }
        btnPause.setOnClickListener {
            btnPause.visibility = Button.INVISIBLE
            btnPlay.visibility = Button.VISIBLE
        }
    }
}
