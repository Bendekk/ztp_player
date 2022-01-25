package AppPackage.sort;

import AppPackage.Playlist;
import AppPackage.Song;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class ArtistSort implements SongSortStrategy{

    @Override
    public void sort(Playlist playlist) {
        Collections.sort(playlist.getCollectionOfSongs(), new ArtistComparator());
    }

    private class ArtistComparator implements Comparator<Song> {
        public int compare(Song song1, Song song2) {
            return song1.getArtist().toLowerCase(Locale.ROOT).compareTo(song2.getArtist().toLowerCase(Locale.ROOT));
        }
    }
}
