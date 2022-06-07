package xyz.miaoguoge.musicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LocalMusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_music)

        // 得到ViewPager对象
        //创建viewpager和tablayout

        // 得到ViewPager对象
        //创建viewpager和tablayout
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val musicTab = findViewById<TabLayout>(R.id.musicTab)
        val musicFragmentPagerAdapter = MusicFragmentPageAdapter(this)
        viewPager.adapter = musicFragmentPagerAdapter
        viewPager.currentItem = 0
        //创建tablayout和viewpaper的连接器
        //创建tablayout和viewpaper的连接器
        val tabLayoutMediator = TabLayoutMediator(
            musicTab, viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.text = "单曲"
                1 -> tab.text = "专辑"
                2 -> tab.text = "歌手"
                3 -> tab.text = "文件夹"
            }
        }
        tabLayoutMediator.attach()
    }
}