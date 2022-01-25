package AppPackage.sort;

import AppPackage.Playlist;
import AppPackage.Song;

import java.util.Collections;
import java.util.Comparator;

public class YearSort implements SongSortStrategy{

    @Override
    public void sort(Playlist playlist) {
        Collections.sort(playlist.getCollectionOfSongs(), new YearComparator());
    }

    private class YearComparator implements Comparator<Song> {
        public int compare(Song song1, Song song2) {
            return (int) (song1.getYear().getTime() - song2.getYear().getTime());
        }
    }
}
