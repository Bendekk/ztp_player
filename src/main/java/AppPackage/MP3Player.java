package AppPackage;

import com.mpatric.mp3agic.*;
import java.io.File;
import java.io.*;
import java.util.*;
import javazoom.jl.player.Player;

public class MP3Player extends javax.swing.JFrame {
    public Player myplayer;
    public File fileCurrentlyPlaying;
    public ArrayList<File> filePlaylist = new ArrayList();
    public File playlistPhysicalFile = new File("playlist.txt");
    public static Thread a;
    public static boolean RepeatMode;
    public boolean Browsed;
    private Thread b;

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


}