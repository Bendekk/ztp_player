package AppPackage;

import AppPackage.sort.DurationSort;
import AppPackage.sort.NameSort;
import AppPackage.sort.SongSortStrategy;
import AppPackage.sort.ArtistSort;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ProjectForm extends JFrame implements KeyListener{
//    private javax.swing.JButton jButtonAddToPlaylist;
//    private javax.swing.JButton jButtonBrowse;
//    private javax.swing.JButton jButtonClearPlaylist;
//    private javax.swing.JButton jButtonDisplayPlaylist;
//    private javax.swing.JButton jButtonNextSong;
//    private javax.swing.JButton jButtonPause;
//    private javax.swing.JButton jButtonPlaFy;
//    private javax.swing.JButton jButtonPreviousSong;
//    private javax.swing.JButton jButtonPrintPlaylist;
//    private javax.swing.JButton jButtonRepeatMode;
//    private javax.swing.JButton jButtonShuffle;
//    private javax.swing.JButton jButtonStop;
//    private javax.swing.JDesktopPane jDesktopPane1;
//    private javax.swing.JDialog jDialog1;
//    private javax.swing.JDialog jDialog2;
//    private javax.swing.JDialog jDialog3;
//    private JFileChooser jFileChooser1;
//    private javax.swing.JFrame jFrame1;
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JList<String> jList1;
//    private javax.swing.JList<String> jList2;
//    private javax.swing.JList<String> jListPlaylist;
//    private javax.swing.JPopupMenu jPopupMenu1;
//    private javax.swing.JScrollPane jScrollPane1;
//    private javax.swing.JScrollPane jScrollPane2;
//    private javax.swing.JScrollPane jScrollPane3;
//    private javax.swing.JTextField jTextFieldPlayingFile;
//    private JPanel panel1;
//    private JButton jButtonClearPlaylist;
//    private JButton jButtonPlay;
//    private JButton jButtonPreviousSong;
//    private JButton jButtonBrowse;
//    private JButton jButtonDisplayPlaylist;
//    private JButton jButtonPause;
//    private JButton jButtonNextSong;
//    private JButton jButtonPrintPlaylist;
//    private JButton jButtonRepeatMode;
//    private JButton jButtonShuffle;
//    private JButton jButtonStop;
//    private JButton jButtonAddToPlaylist;
//    private JList jListPlaylist;
//    private JTextField jTextFieldPlayingFile;
//    private JButton jButtonColorMode;
//    private JButton jButtonBrowsePlaylist;
    public JPanel panel1;
    public JButton jButtonClearPlaylist;
    public JButton jButtonPlay;
    public JButton jButtonPreviousSong;
    public JButton jButtonBrowse;
    public JButton jButtonDisplayPlaylist;
    public JButton jButtonPause;
    public JButton jButtonNextSong;
    public JButton jButtonPrintPlaylist;
    public JButton jButtonRepeatMode;
    public JButton jButtonShuffle;
    public JButton jButtonStop;
    public JButton jButtonAddToPlaylist;
    public JList jListPlaylist;
    public JTextField jTextFieldPlayingFile;
    public JButton jButtonColorMode;
    private JButton jButtonBrowsePlaylist;
    private JScrollPane scrollPane;
    private JButton sortByName;
    private JButton sortByYear;
    private JButton sortByDuration;
    boolean isLightModeOn = false;
    boolean isPlaylistBrowsed = false;
    boolean isFileBrowsed = false;
    private boolean hidden = true;
    PauseCommand pauseCommand = new PauseCommand();
    PlayCommand playCommand = new PlayCommand();

    ThemedFrame lightThemeFrame = new LightThemeFrame();
    ThemedFrame darkThemeFrame = new DarkThemeFrame();
    private int displayHeight = 525;
    private SongSortStrategy songSortStrategy;

    //    private JButton jButtonAddToPlaylist;
//    private JButton jButtonBrowse;
//    private JButton jButtonClearPlaylist;
//    private JButton jButtonDisplayPlaylist;
//    private JButton jButtonNextSong;
//    private JButton jButtonPause;
//    private JButton jButtonPlay;
//    private JButton jButtonPreviousSong;
//    private JButton jButtonPrintPlaylist;
//    private JButton jButtonRepeatMode;
//    private JButton jButtonShuffle;
//    private JButton jButtonStop;
    public class MyKeyListener implements KeyEventDispatcher {
        MP3Player k;
        ProjectForm f;
        PlayerHoldingState playerHoldingState;
        public MyKeyListener(ProjectForm f, MP3Player k, PlayerHoldingState playerHoldingState){
            this.k = k;
            this.f = f;
            this.playerHoldingState = playerHoldingState;
        }
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch( e.getKeyChar() ){
                    case 'x':
                        pauseCommand.execute(f, k.a, k, playerHoldingState);
                        break;
                    case 'c':
                        playCommand.execute(f, k.a, k, playerHoldingState);
                        break;
                }
//            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
//                System.out.println("2test2");
//            } else if (e.getID() == KeyEvent.KEY_TYPED) {
//                System.out.println("3test3");
            }
            return false;
        }
    }
//        @Override
//        public void keyTyped(KeyEvent e) {
//            System.out.println("keyTyped="+KeyEvent.getKeyText(e.getKeyCode()));
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
//            if( KeyEvent.getKeyText(e.getKeyCode()).compareTo("X") == 0 )
//                if( k.a!=null)
//                    pauseCommand.execute(f, k.a, k);
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
//        }
//    }

    public ProjectForm(MP3Player k){

        darkThemeFrame.changeTheme(this);

        StopPlayManager stopPlayManager = new StopPlayManager();
        Playlist actualPlaylist = new Playlist();
        PlayerHoldingState playerHoldingState = new PlayerHoldingState( new PlayerPauseState() );
        ProjectForm thisForm = this;

        this.setContentPane(panel1);
        KeyEventDispatcher listener = new MyKeyListener(this, k, playerHoldingState);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(listener);
        setFocusable(false);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.ReadPlaylistFile(k, stopPlayManager, actualPlaylist);
        this.DrawPlaylist(k);

        jButtonPrintPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintPlaylistActionPerformed(evt, k, stopPlayManager, actualPlaylist);
                jButtonPrintPlaylist.setFocusPainted(false);
            }
        });
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed(evt, k);
                jButtonBrowse.setSelected(false);
            }
        });
        jButtonClearPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearPlaylistActionPerformed(evt, k, stopPlayManager, actualPlaylist);
                jButtonClearPlaylist.setSelected(false);
            }
        });
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonPlayActionPerformed(evt, k, stopPlayManager, playerHoldingState);
                playCommand.execute(thisForm, k.a, k, playerHoldingState);
                jButtonPlay.setSelected(false);
            }
        });
        jButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPauseActionPerformed(evt,k, playerHoldingState);
                jButtonPause.setSelected(false);
            }
        });
        jButtonPreviousSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousSongActionPerformed(evt,k, stopPlayManager, actualPlaylist, playerHoldingState);
                jButtonPreviousSong.setSelected(false);
            }
        });
        jButtonNextSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextSongActionPerformed(evt, k, stopPlayManager, actualPlaylist, playerHoldingState);
                jButtonNextSong.setSelected(false);
            }
        });
        jButtonDisplayPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayPlaylistActionPerformed(evt);
                jButtonDisplayPlaylist.setSelected(false);
            }
        });

        jButtonAddToPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddToPlaylistActionPerformed(evt, k, stopPlayManager, actualPlaylist);
                jButtonAddToPlaylist.setSelected(false);
            }
        });
        jButtonRepeatMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRepeatModeActionPerformed(evt, k);
                jButtonRepeatMode.setSelected(false);
            }
        });
        jButtonShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShuffleActionPerformed(evt, k, stopPlayManager, playerHoldingState);
                jButtonShuffle.setSelected(false);
            }
        });
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt, k, stopPlayManager, playerHoldingState);
                jButtonStop.setSelected(false);
            }
        });
        jButtonColorMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetColorMode(evt, k);
                jButtonColorMode.setSelected(false);
            }
        });
        jButtonBrowsePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowsePlaylists(evt, k, stopPlayManager, actualPlaylist);
                jButtonBrowsePlaylist.setSelected(false);
            }
        });
        sortByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new NameSort();
                songSortStrategy.sort(actualPlaylist);
                assignToJList(actualPlaylist);
            }
        });
        sortByDuration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new DurationSort();
                songSortStrategy.sort(actualPlaylist);
                assignToJList(actualPlaylist);
            }
        });
        sortByYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new ArtistSort();
                songSortStrategy.sort(actualPlaylist);
                assignToJList(actualPlaylist);
            }
        });
    }

    private void assignToJList(Playlist playlist) {
        ArrayList<String> allSongs = new ArrayList<>();
        for (Song s : playlist.getCollectionOfSongs()) {
            System.out.println(s.getName());
            System.out.println("----------------");
            allSongs.add(s.getName());
        }

        jListPlaylist.setListData(allSongs.toArray());
        jListPlaylist.revalidate();
        jListPlaylist.repaint();
    }

    private void ReadPlaylistFile(MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist)
    {
        if(k.filePlaylist!=null) {
            k.filePlaylist.clear();
            stopPlayManager.unsubscribeAll();
            try {
                FileInputStream fi = new FileInputStream(k.playlistPhysicalFile);
                ObjectInputStream oi = new ObjectInputStream(fi);
                while (true) //this loop will terminate when the catch block will terminate, and the catch block will
                //terminate then EOFException will be thrown.
                {
                    k.filePlaylist = (ArrayList) oi.readObject();
                    //this will read until the file ends.
                    //When it cannot read any more object from the file it will throw an EOFException that needs
                    //to be catched, and handled, otherwise the program will terminate.

                }
            } catch (EOFException b) {
                System.out.println("EOF exception");
            } catch (IOException e) {
                System.out.println("IO exception");
            } catch (ClassNotFoundException e) {
                System.out.println("CNF exception");
            }
            if (k.filePlaylist != null) {
                boolean first = true;
                actualPlaylist.clear();
                for (File s : k.filePlaylist) {
                    System.out.println(s.getName());
//                    if( first )
//                        k.fileCurrentlyPlaying = s;
                    first = false;
                    Mp3File mp3file = null;
                    int duration = 0;
                    String artist = "Unknown";
                    try {
                        mp3file = new Mp3File(s.getAbsolutePath());
                        System.out.println("duration:" + mp3file.getLengthInSeconds());
                        if (mp3file != null &&  mp3file.hasId3v2Tag()) {
                            duration = (int) mp3file.getLengthInSeconds();
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
                    stopPlayManager.subscribe(newSong);
                    actualPlaylist.addToPlaylist(newSong);
                }
            }
        }
    }
    private void jButtonAddToPlaylistActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist) {//GEN-FIRST:event_jButtonAddToPlaylistActionPerformed
        boolean GoFurther = true;
        this.ReadPlaylistFile(k, stopPlayManager, actualPlaylist); //refresh the playlist.
        if( isFileBrowsed) {
            if (k.fileCurrentlyPlaying != null) {
                if (k.filePlaylist == null) {
                    k.filePlaylist = new ArrayList();
                }
                if (!k.filePlaylist.isEmpty()) {
                    GoFurther = stopPlayManager.notifySubscribers(k.fileCurrentlyPlaying.getName());
                    //                for(File s : k.filePlaylist)
                    //                {

                    //                    if(s.compareTo(k.fileCurrentlyPlaying)==0)
                    //                    {
                    //                        GoFurther = false;
                    //                        break;
                    //                    }
                    //                }
                }
                if (GoFurther) {
                    k.filePlaylist.add(k.fileCurrentlyPlaying);
                    this.WritePlaylistFile(k);
                    this.DrawPlaylist(k);
                }
                //            else
                //            {
                //                System.out.printf("File: %s is already in the playlist.\n", k.fileCurrentlyPlaying.getName());
                //            }
            } else {
                System.out.printf("Please, select a file first!\n");
            }
        }
        else
            System.out.printf("Can't add playlist to playlist, you dumb dumb!\n");
    }
    private void WritePlaylistFile(MP3Player k) {
        if(k.filePlaylist!=null) {
            try{
                FileOutputStream fs = new FileOutputStream(k.playlistPhysicalFile);
                ObjectOutputStream os = new ObjectOutputStream(fs);
                os.writeObject(k.filePlaylist);
                os.close();
                fs.close();
            }
            catch(FileNotFoundException e) {
                System.out.printf("FileNotFoundException: %s\n", e);
            }
            catch(IOException e){
                System.out.printf("IOException: %s\n", e);
            }
        }
    }
    private void DrawPlaylist(MP3Player k) {
        String[] myString = new String[1000];
        int i=0;
        if( k.filePlaylist!=null) {
            for(File s : k.filePlaylist){
                if( s != null ) {
                    myString[i] = s.getName();
                    ++i;
                }
            }
        }
        jListPlaylist.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return myString.length; }
            public String getElementAt(int i) { return myString[i]; }
        });
    }
    private void jButtonBrowseActionPerformed(java.awt.event.ActionEvent evt, MP3Player k) {//GEN-FIRST:event_jButtonBrowseActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        isFileBrowsed = true;
        isPlaylistBrowsed = false;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            k.fileCurrentlyPlaying = fileChooser.getSelectedFile();
            this.jTextFieldPlayingFile.setText("Selected: " + k.fileCurrentlyPlaying.getName());
            k.Browsed = true;
            // load from file
        }
    }

    private void jButtonClearPlaylistActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist) {//GEN-FIRST:event_jButtonClearPlaylistActionPerformed
        if(k.filePlaylist!=null)
        {
//          design pattern
//            stopPlayManager.notifySubscribers(k.a);

            k.filePlaylist.clear();
            actualPlaylist.clear();
            this.WritePlaylistFile(k);
            this.DrawPlaylist(k);
        }
    }
    private void jButtonDisplayPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplayPlaylistActionPerformed
        if( hidden ) {
//            this.setSize(695, 288);
            System.out.println( this.getSize() );
            this.setSize(695, displayHeight);
            System.out.println( this.getSize() );
            scrollPane.setVisible(true);
            this.revalidate();
            this.repaint();
            this.jButtonDisplayPlaylist.setText("Hide Playlist");
        }
        else{
//            this.setSize(377, 288);
            System.out.println( this.getSize() );
            this.setSize(578, displayHeight);
            System.out.println( this.getSize() );
            scrollPane.setVisible(false);
            this.revalidate();
            this.repaint();
            this.jButtonDisplayPlaylist.setText("Display Playlist");
        }
        hidden = !hidden;
    }
    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonPlayActionPerformed
        // TODO add your handling code here:
//        this.PlaySongFile(k, playerHoldingState);

    }
    private void PlaySongFile(MP3Player k, PlayerHoldingState playerHoldingState)
    {
        playCommand.execute(this, k.a, k, playerHoldingState);
//        if(k.a!=null)
//        {
//            if(k.a.isAlive())
//            {
//                k.a.stop();
//            }
//        }
//        k.a = new Thread ()
//        {
//            public void run()
//            {
//                try
//                {
//                    if(k.fileCurrentlyPlaying!=null)
//                    {
//                        FileInputStream buff = new FileInputStream(k.fileCurrentlyPlaying);
//                        k.myplayer = new Player(buff);
//                        if (k.myplayer != null)
//                        {
//                            jTextFieldPlayingFile.setText("Playing: " + k.fileCurrentlyPlaying.getName());
//                            k.myplayer.play();
//                            k.Browsed = false;
//                        }
//                    }
//                }
//                catch(Exception e)
//                {
//                    System.out.printf("Error Playing File: %s\n", e);
//                }
//            }
//
//        };
//        k.a.start();
    }
    private void jButtonPauseActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonPauseActionPerformed
//        if( k.a!=null)
//        {
        pauseCommand.execute(this, k.a, k, playerHoldingState);
//            if(this.jButtonPause.getText().compareTo("Pause")==0)
//            {
//                this.jButtonPause.setText("Resume");
//                k.a.suspend();
//                if(k.fileCurrentlyPlaying!=null)
//                {
//                    this.jTextFieldPlayingFile.setText("Paused on: " + k.fileCurrentlyPlaying.getName());
//                }
//            }
//            else
//            {
//                this.jButtonPause.setText("Pause");
//                this.jTextFieldPlayingFile.setText("Playing: " + k.fileCurrentlyPlaying.getName());
//                k.a.resume();
//            }
//        }
    }
    private void jButtonPreviousSongActionPerformed(ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonPreviousSongActionPerformed

        if(k.fileCurrentlyPlaying!=null && !k.filePlaylist.isEmpty())
        {
            boolean selectionDone = false;
            Iterator iteratorToPrevious = actualPlaylist.iteratorToPrevious();
            while( iteratorToPrevious.hasNext() ){
                Song song = (Song) iteratorToPrevious.next();
                File file = song.getFile();
//            for(File s: k.filePlaylist)
//            {
                if(file.compareTo(k.fileCurrentlyPlaying)==0)
                {
                    int futureIndex = k.filePlaylist.indexOf(file)-1;
                    if(futureIndex<0)
                    {
                        int index = k.filePlaylist.size()-1;
                        k.fileCurrentlyPlaying = k.filePlaylist.get(index);
                    }
                    else if(futureIndex<k.filePlaylist.size()-1)
                    {
                        int index = futureIndex;
                        k.fileCurrentlyPlaying = k.filePlaylist.get(futureIndex);
                    }
                    selectionDone = true;
                    break;
                }
            }
            if(!selectionDone)
            {
                k.fileCurrentlyPlaying = k.filePlaylist.get(0);
            }
        }
        if(k.a!=null && k.fileCurrentlyPlaying!=null)
        {
            this.PlaySongFile(k, playerHoldingState);
        }
    }
    private void jButtonNextSongActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonNextSongActionPerformed
        System.out.println("hihawa przed");
        if( k.fileCurrentlyPlaying != null && !k.filePlaylist.isEmpty() )
        {
            System.out.println("hihaWA PO");
            boolean selectionDone = false;
            Iterator iter = actualPlaylist.iterator();
            while( iter.hasNext() ){
                Song song = (Song) iter.next();
                File file = song.getFile();
//                k.fileCurrentlyPlaying = file;
//                selectionDone = true;
                if( file.compareTo(k.fileCurrentlyPlaying) == 0 ){
//                {
                    int futureIndex = k.filePlaylist.indexOf( file );
                    if(futureIndex<k.filePlaylist.size()-1)
                        k.fileCurrentlyPlaying = k.filePlaylist.get(futureIndex+1);
                    else
                        k.fileCurrentlyPlaying = k.filePlaylist.get(0);
                    selectionDone = true;
                    break;
                }
            }
//            Iterator<File> iteratorOfFilePlaylist = k.filePlaylist.iterator();

//            while( iteratorOfFilePlaylist.hasNext() ){
//                File file = iteratorOfFilePlaylist.next();
//                if( file.compareTo(k.fileCurrentlyPlaying) == 0 )
//                {
//                    int futureIndex = k.filePlaylist.indexOf( file );
//                    if(futureIndex<k.filePlaylist.size()-1)
//                        k.fileCurrentlyPlaying = k.filePlaylist.get(futureIndex+1);
//                    else
//                        k.fileCurrentlyPlaying = k.filePlaylist.get(0);
//                    selectionDone = true;
//                    break;
//                }
//            }
//            for(File s: k.filePlaylist)
//            {
//                if(s.compareTo(k.fileCurrentlyPlaying)==0)
//                {
//                    int futureIndex = k.filePlaylist.indexOf(s);
//                    if(futureIndex<k.filePlaylist.size()-1)
//                    {
//                        k.fileCurrentlyPlaying = k.filePlaylist.get(futureIndex+1);
//                    }
//                    else
//                    {
//                        k.fileCurrentlyPlaying = k.filePlaylist.get(0);
//                    }
//                    selectionDone = true;
//                    break;
//                }
//            }
            if(!selectionDone)
            {
//                k.fileCurrentlyPlaying = k.filePlaylist.get(0);
            }
        }
        if(k.a!=null && k.fileCurrentlyPlaying!=null)
        {
            this.PlaySongFile(k, playerHoldingState);
        }
    }
    private void jButtonPrintPlaylistActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist) {//GEN-FIRST:event_jButtonPrintPlaylistActionPerformed
        this.ReadPlaylistFile(k, stopPlayManager, actualPlaylist);

        if(k.filePlaylist!=null && !k.filePlaylist.isEmpty()){
            System.out.printf("Playlist Content: \n\n");
            for(File s: k.filePlaylist){
                if(s!=null){
                    System.out.printf("%s\n", s.getName());
                }
            }
            System.out.printf("\nThere are %d songs in the playlist.\n", k.filePlaylist.size());
        }
        else{
            System.out.printf("Playlist is empty!\n");
        }
    }
    private void jButtonRepeatModeActionPerformed(java.awt.event.ActionEvent evt, MP3Player k) {//GEN-FIRST:event_jButtonRepeatModeActionPerformed
        if( this.jButtonRepeatMode.getText().compareTo( "Repeat Mode is ON!" ) == 0 ){
            this.jButtonRepeatMode.setText( "Repeat Mode is OFF!" );
            k.RepeatMode = false;
        }
        else{
            this.jButtonRepeatMode.setText( "Repeat Mode is ON!" );
            k.RepeatMode = true;
        }
    }
    private void jButtonShuffleActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonShuffleActionPerformed
        if(k.filePlaylist!=null)
        {
            Random rnd = new Random();
            if(k.filePlaylist.size()>2)
            {
                int choice = rnd.nextInt(k.filePlaylist.size());
                k.fileCurrentlyPlaying = k.filePlaylist.get(choice);
                this.PlaySongFile(k, playerHoldingState);
            }
            else
            {
                System.out.printf("You cannot shuffle a playlist with less than 3 songs.\n");
            }
        }
    }
    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonStopActionPerformed
        if(k.a!=null)
        {
            playerHoldingState.setState(new PlayerPlayState() );
            playerHoldingState.getState().doAction( playerHoldingState, this, k.a, k);
//            stopPlayManager.notifySubscribers(k.a);
            k.a.suspend();
            jTextFieldPlayingFile.setText("Stopped on: " + k.fileCurrentlyPlaying.getName());

        }
    }
    private void jButtonSetColorMode(java.awt.event.ActionEvent evt, MP3Player k) {//GEN-FIRST:event_jButtonStopActionPerformed
        if(isLightModeOn) {
            darkThemeFrame.changeTheme(this);
            isLightModeOn = false;
        }
        else {
            lightThemeFrame.changeTheme(this);
            isLightModeOn = true;
        }
    }
    public LinkedList<JComponent> returnAllThemeComponents(){
        LinkedList<JComponent> components = new LinkedList<>();
        components.push(panel1);
        components.push(jButtonClearPlaylist);
        components.push(jButtonPlay);
        components.push(jButtonPreviousSong);
        components.push(jButtonBrowse);
        components.push(jButtonDisplayPlaylist);
        components.push(jButtonPause);
        components.push(jButtonNextSong);
        components.push(jButtonPrintPlaylist);
        components.push(jButtonRepeatMode);
        components.push(jButtonShuffle);
        components.push(jButtonStop);
        components.push(jButtonAddToPlaylist);
        components.push(jListPlaylist);
        components.push(jTextFieldPlayingFile);
        components.push(jButtonColorMode);
        return components;
    }
    public void keyTyped(KeyEvent e) {
        System.out.println(e + "KEY TYPED: ");
    }
    public void keyPressed(KeyEvent e) {
        System.out.println(e + "KEY PRESSED: ");
        System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
    }
    public void keyReleased(KeyEvent e) {
        System.out.println(e + "KEY RELEASED: ");
    }
    public void jButtonBrowsePlaylists(java.awt.event.ActionEvent evt, MP3Player k, StopPlayManager stopPlayManager, Playlist actualPlaylist){
        isFileBrowsed = false;
        isPlaylistBrowsed = true;
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            stopPlayManager.unsubscribeAll();

            k.playlistPhysicalFile = new File( fileChooser.getSelectedFile().getName() );
            this.jTextFieldPlayingFile.setText("Selected playlist: " + fileChooser.getSelectedFile().getName() );
            // load from file
            this.ReadPlaylistFile(k, stopPlayManager, actualPlaylist);
            this.DrawPlaylist(k);
        }
    }
//    public class ProjectForm(){
//        jFileChooser1 = new javax.swing.JFileChooser();
//        jScrollPane1 = new javax.swing.JScrollPane();
//        jList1 = new javax.swing.JList<>();
//        jPopupMenu1 = new javax.swing.JPopupMenu();
//        jDialog1 = new javax.swing.JDialog();
//        jDialog2 = new javax.swing.JDialog();
//        jDialog3 = new javax.swing.JDialog();
//        jFrame1 = new javax.swing.JFrame();
//        jDesktopPane1 = new javax.swing.JDesktopPane();
//        jScrollPane3 = new javax.swing.JScrollPane();
//        jList2 = new javax.swing.JList<>();
//        jButtonPause = new javax.swing.JButton();
//        jButtonPlay = new javax.swing.JButton();
//        jButtonBrowse = new javax.swing.JButton();
//        jButtonAddToPlaylist = new javax.swing.JButton();
//        jButtonStop = new javax.swing.JButton();
//        jButtonNextSong = new javax.swing.JButton();
//        jButtonPrintPlaylist = new javax.swing.JButton();
//        jTextFieldPlayingFile = new javax.swing.JTextField();
//        jButtonPreviousSong = new javax.swing.JButton();
//        jButtonRepeatMode = new javax.swing.JButton();
//        jButtonShuffle = new javax.swing.JButton();
//        jScrollPane2 = new javax.swing.JScrollPane();
//        jListPlaylist = new javax.swing.JList<>();
//        jLabel1 = new javax.swing.JLabel();
//        jButtonDisplayPlaylist = new javax.swing.JButton();
//        jButtonClearPlaylist = new javax.swing.JButton();
//
//            jList1.setModel(new javax.swing.AbstractListModel<String>() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public String getElementAt(int i) { return strings[i]; }
//        });
//            jScrollPane1.setViewportView(jList1);
//
//        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
//            jDialog1.getContentPane().setLayout(jDialog1Layout);
//            jDialog1Layout.setHorizontalGroup(
//                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 400, Short.MAX_VALUE)
//            );
//            jDialog1Layout.setVerticalGroup(
//                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 300, Short.MAX_VALUE)
//            );
//
//        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
//            jDialog2.getContentPane().setLayout(jDialog2Layout);
//            jDialog2Layout.setHorizontalGroup(
//                    jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 400, Short.MAX_VALUE)
//            );
//            jDialog2Layout.setVerticalGroup(
//                    jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 300, Short.MAX_VALUE)
//            );
//
//        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
//            jDialog3.getContentPane().setLayout(jDialog3Layout);
//            jDialog3Layout.setHorizontalGroup(
//                    jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 400, Short.MAX_VALUE)
//            );
//            jDialog3Layout.setVerticalGroup(
//                    jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 300, Short.MAX_VALUE)
//            );
//
//        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
//            jFrame1.getContentPane().setLayout(jFrame1Layout);
//            jFrame1Layout.setHorizontalGroup(
//                    jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 400, Short.MAX_VALUE)
//            );
//            jFrame1Layout.setVerticalGroup(
//                    jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 300, Short.MAX_VALUE)
//            );
//
//        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
//            jDesktopPane1.setLayout(jDesktopPane1Layout);
//            jDesktopPane1Layout.setHorizontalGroup(
//                    jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 100, Short.MAX_VALUE)
//            );
//            jDesktopPane1Layout.setVerticalGroup(
//                    jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGap(0, 100, Short.MAX_VALUE)
//            );
//
//            jList2.setModel(new javax.swing.AbstractListModel<String>() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public String getElementAt(int i) { return strings[i]; }
//        });
//            jScrollPane3.setViewportView(jList2);
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setTitle("Java MP3 Player");
//        setResizable(false);
//
//            jButtonPause.setText("Pause");
//            jButtonPause.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonPauseActionPerformed(evt);
//            }
//        });
//
//            jButtonPlay.setText("Play");
//            jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonPlayActionPerformed(evt);
//            }
//        });
//
//            jButtonBrowse.setText("Browse");
//            jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonBrowseActionPerformed(evt);
//            }
//        });
//
//            jButtonAddToPlaylist.setText("Add To Playlist");
//            jButtonAddToPlaylist.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonAddToPlaylistActionPerformed(evt);
//            }
//        });
//
//            jButtonStop.setText("Stop");
//            jButtonStop.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonStopActionPerformed(evt);
//            }
//        });
//
//            jButtonNextSong.setText("Next Song");
//            jButtonNextSong.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonNextSongActionPerformed(evt);
//            }
//        });
//
//            jButtonPrintPlaylist.setText("Print Playlist");
//            jButtonPrintPlaylist.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonPrintPlaylistActionPerformed(evt);
//            }
//        });
//
//            jTextFieldPlayingFile.setEditable(false);
//            jTextFieldPlayingFile.setBackground(new java.awt.Color(0, 0, 0));
//            jTextFieldPlayingFile.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
//            jTextFieldPlayingFile.setHorizontalAlignment(javax.swing.JTextField.CENTER);
//            jTextFieldPlayingFile.setToolTipText("");
//            jTextFieldPlayingFile.setAutoscrolls(false);
//            jTextFieldPlayingFile.setEnabled(false);
//            jTextFieldPlayingFile.setName(""); // NOI18N
//            jTextFieldPlayingFile.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextFieldPlayingFileActionPerformed(evt);
//            }
//        });
//
//            jButtonPreviousSong.setText("Previous Song");
//            jButtonPreviousSong.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonPreviousSongActionPerformed(evt);
//            }
//        });
//
//            jButtonRepeatMode.setText("Repeat Mode is ON!");
//            jButtonRepeatMode.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonRepeatModeActionPerformed(evt);
//            }
//        });
//
//            jButtonShuffle.setText("Shuffle!");
//            jButtonShuffle.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonShuffleActionPerformed(evt);
//            }
//        });
//
//            jListPlaylist.setFocusable(false);
//            jListPlaylist.setOpaque(false);
//            jListPlaylist.setVerifyInputWhenFocusTarget(false);
//            jScrollPane2.setViewportView(jListPlaylist);
//            jListPlaylist.getAccessibleContext().setAccessibleName("");
//
//            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            jLabel1.setText("File Playing:");
//            jLabel1.setEnabled(false);
//            jLabel1.setFocusable(false);
//
//            jButtonDisplayPlaylist.setText("Display Playlist");
//            jButtonDisplayPlaylist.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonDisplayPlaylistActionPerformed(evt);
//            }
//        });
//
//            jButtonClearPlaylist.setText("Clear Playlist");
//            jButtonClearPlaylist.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonClearPlaylistActionPerformed(evt);
//            }
//        });
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//            layout.setHorizontalGroup(
//                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(layout.createSequentialGroup()
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                            .addGroup(layout.createSequentialGroup()
//                                                    .addGap(34, 34, 34)
//                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                                            .addGroup(layout.createSequentialGroup()
//                                                                    .addComponent(jButtonPreviousSong)
//                                                                    .addGap(18, 18, 18)
//                                                                    .addComponent(jButtonRepeatMode))
//                .addGroup(layout.createSequentialGroup()
//                                                                    .addComponent(jButtonPrintPlaylist)
//                                                                    .addGap(18, 18, 18)
//                                                                    .addComponent(jButtonBrowse)
//                                                                    .addGap(18, 18, 18)
//                                                                    .addComponent(jButtonNextSong))))
//                .addGroup(layout.createSequentialGroup()
//                                                    .addContainerGap()
//                                                    .addComponent(jTextFieldPlayingFile, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGroup(layout.createSequentialGroup()
//                                                    .addGap(146, 146, 146)
//                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
//                .addGroup(layout.createSequentialGroup()
//                                                    .addContainerGap()
//                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
//                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
//                                                                    .addComponent(jButtonClearPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                                                    .addComponent(jButtonDisplayPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                                                    .addComponent(jButtonShuffle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
//                                                                    .addComponent(jButtonPlay)
//                                                                    .addGap(24, 24, 24)
//                                                                    .addComponent(jButtonPause)
//                                                                    .addGap(27, 27, 27)
//                                                                    .addComponent(jButtonStop)
//                                                                    .addGap(26, 26, 26)
//                                                                    .addComponent(jButtonAddToPlaylist)))))
//                .addGap(18, 18, 18)
//                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
//                                    .addContainerGap())
//                );
//            layout.setVerticalGroup(
//                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                    .addComponent(jLabel1)
//                                    .addGap(18, 18, 18)
//                                    .addComponent(jTextFieldPlayingFile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                            .addComponent(jButtonShuffle)
//                                            .addComponent(jButtonDisplayPlaylist)
//                                            .addComponent(jButtonClearPlaylist))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                            .addComponent(jButtonPause)
//                                            .addComponent(jButtonAddToPlaylist)
//                                            .addComponent(jButtonPlay)
//                                            .addComponent(jButtonStop))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                            .addComponent(jButtonBrowse)
//                                            .addComponent(jButtonNextSong)
//                                            .addComponent(jButtonPrintPlaylist))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                                            .addComponent(jButtonPreviousSong)
//                                            .addComponent(jButtonRepeatMode))
//                .addGap(22, 22, 22))
//                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                    .addContainerGap()
//                                    .addComponent(jScrollPane2)
//                                    .addContainerGap())
//                );
//    }

}
