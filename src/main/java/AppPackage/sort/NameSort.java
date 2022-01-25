package AppPackage.sort;

import AppPackage.Playlist;
import AppPackage.Song;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class NameSort implements SongSortStrategy{
    @Override
    public void sort(Playlist playlist) {
        Collections.sort(playlist.getCollectionOfSongs(), new NameComparator());

    }

    private class NameComparator implements Comparator<Song> {

        @Override
        public int compare(Song o1, Song o2) {
            return o1.getName().toLowerCase(Locale.ROOT).compareTo(o2.getName().toLowerCase(Locale.ROOT));
        }
    }
}
