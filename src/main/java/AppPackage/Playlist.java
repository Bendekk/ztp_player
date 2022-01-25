package AppPackage;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist implements Iterable<Song> {
    private ArrayList<Song> collectionOfSongs = new ArrayList<>();
    int actualPosition = 0;

    public Iterator<Song> iterator(){
        return new Iterator<>() {
            public boolean hasNext(){return true;}
            public Song next(){
                if(actualPosition >= collectionOfSongs.size() - 1)
                    actualPosition = 0;
                else
                    ++actualPosition;

                Song song = collectionOfSongs.get(actualPosition);
                return(song);
            }
        };
    }
    public Iterator<Song> iteratorToPrevious(){
        return new Iterator<>() {
            public boolean hasNext(){ return true; }
            public Song next(){
                if( actualPosition == 0 )
                    actualPosition = collectionOfSongs.size()-1;
                else
                    --actualPosition;

                Song song = collectionOfSongs.get(actualPosition);
                return(song);
            }
        };
    }

    public void clear(){
        collectionOfSongs.clear();
    }

    public ArrayList<Song> getCollectionOfSongs() {
        return collectionOfSongs;
    }

    public void setCollectionOfSongs(ArrayList<Song> collectionOfSongs) {
        this.collectionOfSongs = collectionOfSongs;
    }

    public void addToPlaylist(Song song){
        collectionOfSongs.add(song);
    }
    public void removeAllSongs(){
        collectionOfSongs.clear();
    }
}
