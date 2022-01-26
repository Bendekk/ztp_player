package AppPackage.observer;

import AppPackage.Song;

import java.util.LinkedList;

public class CheckForDuplicatesManager {
    LinkedList<Song> list = new LinkedList<>();
    boolean trueNameTheSame = false;
    public CheckForDuplicatesManager(){
        LinkedList<String> l = new LinkedList<>();
    }
    public void subscribe(Song song) {
        list.push(song);
    }

    public void unsubscribeAll() {
        if( !list.isEmpty() )
            list.clear();
    }
    public boolean notifySubscribers(String k){
        for( Song l : list) {
            trueNameTheSame = l.update( k );
            if( !trueNameTheSame )
                break;
        }
        return trueNameTheSame;
    }

}
