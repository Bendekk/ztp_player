package AppPackage;

import AppPackage.observer.CheckForDuplicatesManager;
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

    public MP3Player() throws InvalidDataException, UnsupportedTagException, IOException {
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

}