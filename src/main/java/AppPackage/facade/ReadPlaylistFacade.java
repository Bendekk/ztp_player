package AppPackage.facade;

import AppPackage.observer.CheckForDuplicatesManager;
import AppPackage.MP3Player;
import AppPackage.iterator.Playlist;
import AppPackage.Song;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.*;
import java.util.ArrayList;

public class ReadPlaylistFacade {
    public void read(MP3Player mp3Player, CheckForDuplicatesManager checkForDuplicatesManager, Playlist actualPlaylist)
    {
        if(mp3Player.getFilePlaylist() == null){
            mp3Player.setFilePlaylist( new ArrayList<>() );
        }
        if(mp3Player.getFilePlaylist() != null) {
            mp3Player.getFilePlaylist().clear();
            checkForDuplicatesManager.unsubscribeAll();
            try {
                FileInputStream fi = new FileInputStream( mp3Player.getPlaylistPhysicalFile() );
                ObjectInputStream oi = new ObjectInputStream(fi);
                while (true){
                    mp3Player.setFilePlaylist( (ArrayList) oi.readObject() );
                }
            } catch (EOFException b) {
                System.out.println("EOF exception");
            } catch (IOException e) {
                System.out.println("IO exception");
            } catch (ClassNotFoundException e) {
                System.out.println("CNF exception");
            }
            if (!mp3Player.getFilePlaylist().isEmpty()) {
                actualPlaylist.clear();
                for ( File s : mp3Player.getFilePlaylist() ) {
                    Mp3File mp3file = null;
                    int duration = 0;
                    String artist = "Unknown";
                    try {
                        mp3file = new Mp3File(s.getAbsolutePath());
                        System.out.println("duration:" + mp3file.getLengthInSeconds());
                        if (mp3file != null &&  mp3file.hasId3v2Tag()) {
                            duration = (int) mp3file.getLengthInSeconds();
                            if (mp3file.getId3v2Tag().getArtist() != null)
                                artist = mp3file.getId3v2Tag().getArtist();
                        }
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } ;
                    Song newSong = new Song(s.getName(), duration, artist, s);
                    checkForDuplicatesManager.subscribe(newSong);
                    actualPlaylist.addToPlaylist(newSong);
                }
            }
        }
    }
}
