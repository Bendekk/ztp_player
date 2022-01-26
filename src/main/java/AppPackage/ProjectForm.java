package AppPackage;

import AppPackage.sort.DurationSort;
import AppPackage.sort.NameSort;
import AppPackage.sort.SongSortStrategy;
import AppPackage.sort.ArtistSort;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ProjectForm extends JFrame implements KeyListener{
    private JPanel panel1;
    private JButton jButtonClearPlaylist;
    private JButton jButtonPlay;
    private JButton jButtonPreviousSong;
    private JButton jButtonBrowse;
    private JButton jButtonDisplayPlaylist;
    private JButton jButtonPause;
    private JButton jButtonNextSong;
    private JButton jButtonPrintPlaylist;
    private JButton jButtonRepeatMode;
    private JButton jButtonShuffle;
    private JButton jButtonStop;
    private JButton jButtonAddToPlaylist;
    private JList jListPlaylist;
    private JTextField jTextFieldPlayingFile;
    private JButton jButtonColorMode;
    private JButton jButtonBrowsePlaylist;
    private JScrollPane scrollPane;
    private JButton sortByName;
    private JButton sortByArtist;
    private JButton sortByDuration;
    private JPanel backgroundPanel;
    private JLabel sortlabel;
    private boolean isLightModeOn = false;
    private boolean isPlaylistBrowsed = false;
    private boolean isFileBrowsed = false;
    private boolean hidden = true;
    private boolean firstBrowse = true;
    private ReadPlaylistFacade readPlaylistFacade;
    private Iterator iter;
    private Iterator iterPrevious;
    private ProxyInterface colorSelectedProxy;
    private PauseCommand pauseCommand = new PauseCommand();
    private PlayCommand playCommand = new PlayCommand();

    private ThemedFrame themedFrame;
    private int displayHeight = 525;
    private SongSortStrategy songSortStrategy;
    private MP3Player mp3Player;
    private ProjectForm thisFrame;

    public class MyKeyListener implements KeyEventDispatcher {
        MP3Player k;
        ProjectForm f;
        PlayerHoldingState playerHoldingState;
        public MyKeyListener(ProjectForm f, MP3Player k, PlayerHoldingState playerHoldingState){
            this.f = f;
            this.playerHoldingState = playerHoldingState;
        }
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch( e.getKeyChar() ){
                    case 'x':
                        pauseCommand.execute(f, mp3Player, playerHoldingState);
                        break;
                    case 'c':
                        playCommand.execute(f, mp3Player, playerHoldingState);
                        break;
                }
            }
            return false;
        }
    }
    public ProjectForm(MP3Player k){
        mp3Player = k;
        thisFrame = this;
        readPlaylistFacade = new ReadPlaylistFacade();
        colorSelectedProxy = new ColorSelectedProxy( new PrintingPlaylistOnJlist() );
        themedFrame = DarkThemeFrame.getDarkThemeFrame();
        themedFrame.changeTheme(thisFrame);
        sortlabel.setVisible(false);

        StopPlayManager stopPlayManager = new StopPlayManager();
        Playlist actualPlaylist = new Playlist();
        iter = actualPlaylist.iterator();
        iterPrevious = actualPlaylist.iteratorToPrevious();
        PlayerHoldingState playerHoldingState = new PlayerHoldingState( new PlayerPauseState() );

        this.setContentPane(panel1);
        KeyEventDispatcher listener = new MyKeyListener(this, k, playerHoldingState);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(listener);
        setFocusable(false);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.revalidate();
        this.repaint();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mp3Player.setPlaylistPhysicalFile( new File( "playlist.txt" ) );
        readPlaylistFacade.read(mp3Player, stopPlayManager, actualPlaylist);
        if( mp3Player.getFilePlaylist() != null && !mp3Player.getFilePlaylist().isEmpty() )
            if( mp3Player.getFilePlaylist().get(0) != null)
                mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get(0) );

        this.DrawPlaylist();

        jButtonPrintPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintPlaylistActionPerformed(stopPlayManager, actualPlaylist);
                jButtonPrintPlaylist.setFocusPainted(false);
            }
        });
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed();
                jButtonBrowse.setSelected(false);
            }
        });
        jButtonClearPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearPlaylistActionPerformed(stopPlayManager, actualPlaylist);
                jButtonClearPlaylist.setSelected(false);
            }
        });
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButtonPlayActionPerformed(evt, k, stopPlayManager, playerHoldingState);
                playCommand.execute(thisFrame, mp3Player, playerHoldingState);
                jButtonPlay.setSelected(false);
            }
        });
        jButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPauseActionPerformed(playerHoldingState);
                jButtonPause.setSelected(false);
            }
        });
        jButtonPreviousSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousSongActionPerformed(stopPlayManager, actualPlaylist, playerHoldingState);
            }
        });
        jButtonNextSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextSongActionPerformed(stopPlayManager, actualPlaylist, playerHoldingState);
                jButtonNextSong.setSelected(false);
            }
        });
        jButtonDisplayPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayPlaylistActionPerformed();
                jButtonDisplayPlaylist.setSelected(false);
            }
        });

        jButtonAddToPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddToPlaylistActionPerformed(stopPlayManager, actualPlaylist);
                jButtonAddToPlaylist.setSelected(false);
            }
        });
        jButtonRepeatMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRepeatModeActionPerformed();
                jButtonRepeatMode.setSelected(false);
            }
        });
        jButtonShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShuffleActionPerformed(stopPlayManager, playerHoldingState);
                jButtonShuffle.setSelected(false);
            }
        });
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(stopPlayManager, playerHoldingState);
                jButtonStop.setSelected(false);
            }
        });
        jButtonColorMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetColorMode();
                jButtonColorMode.setSelected(false);
            }
        });
        jButtonBrowsePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowsePlaylists(stopPlayManager, actualPlaylist);
                jButtonBrowsePlaylist.setSelected(false);
            }
        });
        sortByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new NameSort();
                songSortStrategy.sort(actualPlaylist);
                colorSelectedProxy.color( actualPlaylist, jListPlaylist, "Sort by name", sortlabel);
            }
        });
        sortByDuration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new DurationSort();
                songSortStrategy.sort(actualPlaylist);
                colorSelectedProxy.color( actualPlaylist, jListPlaylist, "Sort by duration", sortlabel );
            }
        });
        sortByArtist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new ArtistSort();
                songSortStrategy.sort(actualPlaylist);
                colorSelectedProxy.color( actualPlaylist, jListPlaylist, "Sort by artist", sortlabel );
            }
        });
        jButtonBrowsePlaylists(stopPlayManager, actualPlaylist);
    }

    private void jButtonAddToPlaylistActionPerformed(StopPlayManager stopPlayManager, Playlist actualPlaylist) {//GEN-FIRST:event_jButtonAddToPlaylistActionPerformed
        boolean GoFurther = true;

        if( isFileBrowsed) {
            if (mp3Player.getFileCurrentlyPlaying() != null) {
                if (mp3Player.getFilePlaylist() == null) {
                    mp3Player.setFilePlaylist( new ArrayList() );
                }
                if (!mp3Player.getFilePlaylist().isEmpty()) {
                    GoFurther = stopPlayManager.notifySubscribers(mp3Player.getFileCurrentlyPlaying().getName());
                }
                if (GoFurther) {
                    mp3Player.getFilePlaylist().add( mp3Player.getFileCurrentlyPlaying() );
                    this.WritePlaylistFile();
                    this.DrawPlaylist();
                }
            } else {
                System.out.printf("Please, select a file first!\n");
            }
        }
        else
            System.out.printf("Can't add playlist to playlist, you dumb dumb!\n");
        readPlaylistFacade.read(mp3Player, stopPlayManager, actualPlaylist);
    }
    private void WritePlaylistFile() {
        if(mp3Player.getFilePlaylist() != null) {
            try{
                FileOutputStream fs = new FileOutputStream( mp3Player.getPlaylistPhysicalFile() );
                ObjectOutputStream os = new ObjectOutputStream( fs );
                os.writeObject( mp3Player.getFilePlaylist() );
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
    private void DrawPlaylist() {
        String[] myString = new String[1000];
        int i=0;
        if( mp3Player.getFilePlaylist() != null ) {
            for(File s : mp3Player.getFilePlaylist() ){
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
    private void jButtonBrowseActionPerformed() {
        JFileChooser fileChooser = new JFileChooser();
        isFileBrowsed = true;
        isPlaylistBrowsed = false;

        fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            mp3Player.setFileCurrentlyPlaying( fileChooser.getSelectedFile() );
            this.jTextFieldPlayingFile.setText("Selected: " + mp3Player.getFileCurrentlyPlaying().getName());
//            mp3Player.Browsed = true;
        }
    }

    private void jButtonClearPlaylistActionPerformed(StopPlayManager stopPlayManager, Playlist actualPlaylist) {
        if(mp3Player.getFilePlaylist() != null)
        {
            mp3Player.getFilePlaylist().clear();
            actualPlaylist.clear();
            this.WritePlaylistFile();
            this.DrawPlaylist();
        }
    }
    private void jButtonDisplayPlaylistActionPerformed() {
        if( hidden ) {
            this.setSize(695, displayHeight);
            scrollPane.setVisible(true);
            sortlabel.setVisible(true);
            this.revalidate();
            this.repaint();
            this.jButtonDisplayPlaylist.setText("Hide Playlist");
        }
        else{
            this.setSize(578, displayHeight);
            scrollPane.setVisible(false);
            sortlabel.setVisible(false);
            this.revalidate();
            this.repaint();
            this.jButtonDisplayPlaylist.setText("Display Playlist");
        }
        hidden = !hidden;
    }
    private void PlaySongFile(PlayerHoldingState playerHoldingState)
    {
        playCommand.execute(this, mp3Player, playerHoldingState);
    }
    private void jButtonPauseActionPerformed(PlayerHoldingState playerHoldingState) {pauseCommand.execute(this, mp3Player, playerHoldingState);}

    private void jButtonPreviousSongActionPerformed(StopPlayManager stopPlayManager, Playlist actualPlaylist, PlayerHoldingState playerHoldingState) {
        if(mp3Player.getFileCurrentlyPlaying() != null && !actualPlaylist.getCollectionOfSongs().isEmpty())
        {

            if( iterPrevious.hasNext() ){
                Song song = (Song) iterPrevious.next();
                mp3Player.setFileCurrentlyPlaying( song.getFile() );
            }
        }
        if( mp3Player.getA() != null && mp3Player.getFileCurrentlyPlaying() != null )
        {
            this.PlaySongFile(playerHoldingState);
        }
    }
    private void jButtonNextSongActionPerformed(StopPlayManager stopPlayManager, Playlist actualPlaylist, PlayerHoldingState playerHoldingState) {//GEN-FIRST:event_jButtonNextSongActionPerformed
        if( mp3Player.getFileCurrentlyPlaying() != null && !actualPlaylist.getCollectionOfSongs().isEmpty() )
        {
            if ( iter.hasNext() ){
                Song song = (Song) iter.next();
                mp3Player.setFileCurrentlyPlaying( song.getFile() );
            }
        }
        if( mp3Player.getA() != null && mp3Player.getFileCurrentlyPlaying() != null )
            this.PlaySongFile(playerHoldingState);
    }
    private void jButtonPrintPlaylistActionPerformed(StopPlayManager stopPlayManager, Playlist actualPlaylist) {
        readPlaylistFacade.read(mp3Player, stopPlayManager, actualPlaylist);
        if(mp3Player.getFilePlaylist() != null && !mp3Player.getFilePlaylist().isEmpty() ){
            System.out.printf("Playlist Content: \n\n");
            for( File s: mp3Player.getFilePlaylist() ){
                if(s!=null){
                    System.out.printf("%s\n", s.getName());
                }
            }
            System.out.printf("\nThere are %d songs in the playlist.\n", mp3Player.getFilePlaylist().size());
        }
        else{
            System.out.printf("Playlist is empty!\n");
        }
    }
    private void jButtonRepeatModeActionPerformed() {
        if( this.jButtonRepeatMode.getText().compareTo( "Repeat Mode is ON!" ) == 0 ){
            this.jButtonRepeatMode.setText( "Repeat Mode is OFF!" );
//            mp3Player.RepeatMode = false;
        }
        else{
            this.jButtonRepeatMode.setText( "Repeat Mode is ON!" );
//            mp3Player.RepeatMode = true;
        }
    }
    private void jButtonShuffleActionPerformed(StopPlayManager stopPlayManager, PlayerHoldingState playerHoldingState) {
        if(mp3Player.getFilePlaylist() != null){
            Random rnd = new Random();
            if( mp3Player.getFilePlaylist().size() > 2 ) {
                int choice = rnd.nextInt(mp3Player.getFilePlaylist().size());
                mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get( choice ) );
                this.PlaySongFile(playerHoldingState);
            }
            else
            {
                System.out.printf("You cannot shuffle a playlist with less than 3 songs.\n");
            }
        }
    }
    private void jButtonStopActionPerformed(StopPlayManager stopPlayManager, PlayerHoldingState playerHoldingState) {
        if( mp3Player.getA() != null ){
            playerHoldingState.setState(new PlayerPlayState() );
            playerHoldingState.getState().doAction( playerHoldingState, this, mp3Player);
            mp3Player.getA().suspend();
            jTextFieldPlayingFile.setText("Stopped on: " + mp3Player.getFileCurrentlyPlaying().getName());
        }
    }
    private void jButtonSetColorMode() {
        if(isLightModeOn)
            themedFrame = DarkThemeFrame.getDarkThemeFrame();
        else
            themedFrame = LightThemeFrame.getLightThemeFrame();

        themedFrame.changeTheme(this);
        isLightModeOn = !isLightModeOn;
    }
    public LinkedList<JComponent> returnAllThemeComponents(){
        LinkedList<JComponent> components = new LinkedList<>();
        components.push(panel1);
        components.push(backgroundPanel);
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
        components.push(jButtonBrowsePlaylist);
        components.push(scrollPane);
        components.push(sortByName);
        components.push(sortByArtist);
        components.push(sortByDuration);
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
    public void jButtonBrowsePlaylists(StopPlayManager stopPlayManager, Playlist actualPlaylist){
        isFileBrowsed = false;
        isPlaylistBrowsed = true;
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            mp3Player.setPlaylistPhysicalFile( new File( fileChooser.getSelectedFile().getAbsolutePath() ) );
            this.jTextFieldPlayingFile.setText("Selected playlist: " + fileChooser.getSelectedFile().getName() );
            readPlaylistFacade.read(mp3Player, stopPlayManager, actualPlaylist);
            this.DrawPlaylist();
            if( mp3Player.getFilePlaylist() != null && !mp3Player.getFilePlaylist().isEmpty() )
                if( mp3Player.getFilePlaylist().get(0) != null )
                    mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get( 0 ) );
            firstBrowse = false;
        } else {
            if (firstBrowse)
                System.exit(69);
        }
    }

    public JButton getjButtonPause() {
        return jButtonPause;
    }

    public JTextField getjTextFieldPlayingFile() {
        return jTextFieldPlayingFile;
    }
}
