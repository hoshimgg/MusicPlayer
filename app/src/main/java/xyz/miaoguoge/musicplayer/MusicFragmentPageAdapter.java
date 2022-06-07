package xyz.miaoguoge.musicplayer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MusicFragmentPageAdapter extends FragmentStateAdapter {
    public MusicFragmentPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MusicFragmentPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SingleSongFragment();
            case 2:
                return new SingerFragment();
            case 3:
                return new FolderFragment();
            default:
                return new AlbumFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
