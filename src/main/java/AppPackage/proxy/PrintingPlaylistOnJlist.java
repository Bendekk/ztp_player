package AppPackage.proxy;

import AppPackage.iterator.Playlist;
import AppPackage.Song;

import javax.swing.*;
import java.util.ArrayList;

public class PrintingPlaylistOnJlist implements ProxyInterface{
    public void changeJlist(Playlist playlist, JList jListPlaylist, String text, JLabel labelText){
        ArrayList<String> allSongs = new ArrayList<>();
        for (Song s : playlist.getCollectionOfSongs()) {
            allSongs.add(s.getName());
        }

        jListPlaylist.setListData(allSongs.toArray());
        jListPlaylist.revalidate();
        jListPlaylist.repaint();
    }
}
