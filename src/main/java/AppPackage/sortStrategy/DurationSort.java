package AppPackage.sortStrategy;

import AppPackage.iterator.Playlist;
import AppPackage.Song;

import java.util.Collections;
import java.util.Comparator;

public class DurationSort implements SongSortStrategy {
    @Override
    public void sort(Playlist playlist) {
        Collections.sort(playlist.getCollectionOfSongs(), new DurationComparator());
    }

    private class DurationComparator implements Comparator<Song> {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getDuration() - o2.getDuration();
        }
    }
}
