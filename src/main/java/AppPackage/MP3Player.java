package AppPackage;

import AppPackage.facade.ReadPlaylistFacade;
import AppPackage.iterator.Playlist;
import AppPackage.observer.CheckForDuplicatesManager;
import AppPackage.proxy.LabelChangingProxy;
import AppPackage.proxy.PrintingPlaylistOnJlist;
import AppPackage.proxy.ProxyInterface;
import AppPackage.state.PlayerHoldingState;
import AppPackage.state.PlayerPauseState;
import com.mpatric.mp3agic.*;
import java.io.File;
import java.io.*;
import java.util.*;
import javazoom.jl.player.Player;

public class MP3Player extends javax.swing.JFrame {

    private Player myplayer;
    private File fileCurrentlyPlaying;
    private ArrayList<File> filePlaylist = new ArrayList();
    private File playlistPhysicalFile;
    private static Thread a;
    private ReadPlaylistFacade readPlaylistFacade;
    private Iterator iter;
    private Iterator iterPrevious;
    private ProxyInterface colorSelectedProxy;
    private Playlist actualPlaylist;
    private CheckForDuplicatesManager checkForDuplicatesManager;
    private PlayerHoldingState playerHoldingState;

    public MP3Player() throws InvalidDataException, UnsupportedTagException, IOException {
        checkForDuplicatesManager = new CheckForDuplicatesManager();
        actualPlaylist = new Playlist();
        iter = actualPlaylist.iterator();
        iterPrevious = actualPlaylist.iteratorToPrevious();
        playerHoldingState = new PlayerHoldingState( new PlayerPauseState() );
        readPlaylistFacade = new ReadPlaylistFacade();
        colorSelectedProxy = new LabelChangingProxy( new PrintingPlaylistOnJlist() );
        setPlaylistPhysicalFile( new File( "playlist.txt" ) );
        readPlaylistFacade.read(this, checkForDuplicatesManager, actualPlaylist);
        if( this.getFilePlaylist() != null && !this.getFilePlaylist().isEmpty() )
            if( this.getFilePlaylist().get(0) != null)
                this.setFileCurrentlyPlaying( this.getFilePlaylist().get(0) );

        ProjectForm p = new ProjectForm(this);

    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MP3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MP3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MP3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MP3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MP3Player();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void setA(Thread a) {
        MP3Player.a = a;
    }
    public static Thread getA() {
        return a;
    }

    public File getPlaylistPhysicalFile() {
        return playlistPhysicalFile;
    }
    public void setPlaylistPhysicalFile(File playlistPhysicalFile) {
        this.playlistPhysicalFile = playlistPhysicalFile;
    }

    public Player getMyplayer() { return myplayer; }
    public void setMyplayer(Player myplayer) {this.myplayer = myplayer;}

    public File getFileCurrentlyPlaying() {return fileCurrentlyPlaying;}
    public void setFileCurrentlyPlaying(File fileCurrentlyPlaying) {this.fileCurrentlyPlaying = fileCurrentlyPlaying;}

    public ArrayList<File> getFilePlaylist() {return filePlaylist;}
    public void setFilePlaylist(ArrayList<File> filePlaylist) {this.filePlaylist = filePlaylist;}

    public Iterator<Song> getIter() { return iter; }
    public Iterator<Song> getIterPrevious() { return iterPrevious; }
    public Playlist getActualPlaylist() {return actualPlaylist; }
    public PlayerHoldingState getPlayerHoldingState() { return playerHoldingState; }
    public ReadPlaylistFacade getReadPlaylistFacade() { return readPlaylistFacade; }
    public CheckForDuplicatesManager getCheckForDuplicatesManager() { return checkForDuplicatesManager; }
    public ProxyInterface getColorSelectedProxy() { return colorSelectedProxy; }
}