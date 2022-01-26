package AppPackage;

import AppPackage.commands.*;
import AppPackage.facade.ReadPlaylistFacade;
import AppPackage.factoryMethodSingleton.DarkThemeFrame;
import AppPackage.factoryMethodSingleton.LightThemeFrame;
import AppPackage.factoryMethodSingleton.ThemedFrame;
import AppPackage.iterator.Playlist;
import AppPackage.observer.CheckForDuplicatesManager;
import AppPackage.proxy.LabelChangingProxy;
import AppPackage.proxy.PrintingPlaylistOnJlist;
import AppPackage.proxy.ProxyInterface;
import AppPackage.sortStrategy.DurationSort;
import AppPackage.sortStrategy.NameSort;
import AppPackage.sortStrategy.SongSortStrategy;
import AppPackage.sortStrategy.ArtistSort;
import AppPackage.state.PlayerHoldingState;
import AppPackage.state.PlayerPauseState;
import AppPackage.state.PlayerPlayState;

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

    private boolean isFileBrowsed = false;

    private boolean hidden = true;
    private boolean firstBrowse = true;

    private ReadPlaylistFacade readPlaylistFacade;
    private Iterator iter;
    private Iterator iterPrevious;
    private ProxyInterface colorSelectedProxy;

    private Playlist actualPlaylist;

    private CheckForDuplicatesManager checkForDuplicatesManager;

    private PlayerHoldingState playerHoldingState;

    private ThemedFrame themedFrame;

    private int displayHeight = 525;
    private SongSortStrategy songSortStrategy;
    private MP3Player mp3Player;
    private ProjectForm thisFrame;

    public class MyKeyListener implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch( e.getKeyChar() ){
                    case 'x':
                        executeCommand( new PauseCommand() );
                        break;
                    case 'c':
                        executeCommand( new PlayCommand() );
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
        colorSelectedProxy = new LabelChangingProxy( new PrintingPlaylistOnJlist() );
        themedFrame = DarkThemeFrame.getDarkThemeFrame();
        themedFrame.changeTheme(thisFrame);
        sortlabel.setVisible(false);

        checkForDuplicatesManager = new CheckForDuplicatesManager();
        actualPlaylist = new Playlist();
        iter = actualPlaylist.iterator();
        iterPrevious = actualPlaylist.iteratorToPrevious();
        playerHoldingState = new PlayerHoldingState( new PlayerPauseState() );

        this.setContentPane(panel1);
        KeyEventDispatcher listener = new MyKeyListener();
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
        readPlaylistFacade.read(mp3Player, checkForDuplicatesManager, actualPlaylist);
        if( mp3Player.getFilePlaylist() != null && !mp3Player.getFilePlaylist().isEmpty() )
            if( mp3Player.getFilePlaylist().get(0) != null)
                mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get(0) );

        this.DrawPlaylist();
        addAllActionListeners();
//      first read a playlist
        executeCommand( new BrowsePlaylistCommand() );

    }

    private void jButtonAddToPlaylistActionPerformed(CheckForDuplicatesManager checkForDuplicatesManager, Playlist actualPlaylist) {//GEN-FIRST:event_jButtonAddToPlaylistActionPerformed
        boolean GoFurther = true;

        if( isFileBrowsed) {
            if (mp3Player.getFileCurrentlyPlaying() != null) {
                if (mp3Player.getFilePlaylist() == null) {
                    mp3Player.setFilePlaylist( new ArrayList() );
                }
                if (!mp3Player.getFilePlaylist().isEmpty()) {
                    GoFurther = checkForDuplicatesManager.notifySubscribers(mp3Player.getFileCurrentlyPlaying().getName());
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
        readPlaylistFacade.read(mp3Player, checkForDuplicatesManager, actualPlaylist);
    }
    public void WritePlaylistFile() {
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
    public void DrawPlaylist() {
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

        fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            mp3Player.setFileCurrentlyPlaying( fileChooser.getSelectedFile() );
            this.jTextFieldPlayingFile.setText("Selected: " + mp3Player.getFileCurrentlyPlaying().getName());
        }
    }


    private void jButtonShuffleActionPerformed(CheckForDuplicatesManager checkForDuplicatesManager, PlayerHoldingState playerHoldingState) {
        if(mp3Player.getFilePlaylist() != null){
            Random rnd = new Random();
            if( mp3Player.getFilePlaylist().size() > 1 ) {
                int choice = rnd.nextInt(mp3Player.getFilePlaylist().size());
                mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get( choice ) );
                executeCommand( new PlayCommand() );
            }
            else{
                System.out.printf("You cannot shuffle a playlist with less than 2 songs.\n");
            }
        }
    }
    private void jButtonStopActionPerformed(CheckForDuplicatesManager checkForDuplicatesManager, PlayerHoldingState playerHoldingState) {
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

    public JButton getjButtonPause() {
        return jButtonPause;
    }

    public JTextField getjTextFieldPlayingFile() {
        return jTextFieldPlayingFile;
    }

    public Iterator<Song> getIter() { return iter; }
    public Iterator<Song> getIterPrevious() { return iterPrevious; }

    public Playlist getActualPlaylist() {return actualPlaylist; }
    private void executeCommand( AlternateCommand command ){ command.execute( thisFrame, mp3Player ); }
    public boolean isFileBrowsed() { return isFileBrowsed; }
    public void setFileBrowsed(boolean fileBrowsed) { isFileBrowsed = fileBrowsed; }
    public PlayerHoldingState getPlayerHoldingState() { return playerHoldingState; }
    public boolean getIsFirstBrowse() { return firstBrowse;}
    public void setFirstBrowse(boolean firstBrowse) { this.firstBrowse = firstBrowse; }
    public ReadPlaylistFacade getReadPlaylistFacade() { return readPlaylistFacade; }
    public CheckForDuplicatesManager getCheckForDuplicatesManager() { return checkForDuplicatesManager; }

    public boolean getIsHidden() { return hidden; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    public int getDisplayHeight() { return displayHeight; }
    public JButton getjButtonDisplayPlaylist() { return jButtonDisplayPlaylist; }

    public JScrollPane getScrollPane() { return scrollPane; }
    public JLabel getSortlabel() { return sortlabel; }
    public void addAllActionListeners(){
        jButtonPrintPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new PrintPlaylistCommand() );
            }
        });
        jButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseActionPerformed();
            }
        });
        jButtonClearPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new ClearPlaylistCommand() );
            }
        });
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new PlayCommand() );
            }
        });
        jButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new PauseCommand());
            }
        });
        jButtonPreviousSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new PreviousSongCommand() );
            }
        });
        jButtonNextSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new NextSongCommand() );
            }
        });
        jButtonDisplayPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new DisplayCommand() );
            }
        });

        jButtonAddToPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddToPlaylistActionPerformed(checkForDuplicatesManager, actualPlaylist);
            }
        });

        jButtonShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShuffleActionPerformed(checkForDuplicatesManager, playerHoldingState);
            }
        });
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(checkForDuplicatesManager, playerHoldingState);
            }
        });
        jButtonColorMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetColorMode();
            }
        });
        jButtonBrowsePlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeCommand( new BrowsePlaylistCommand() );
            }
        });
        sortByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new NameSort();
                songSortStrategy.sort(actualPlaylist);
                colorSelectedProxy.changeJlist( actualPlaylist, jListPlaylist, "Sort by name", sortlabel);
            }
        });
        sortByDuration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new DurationSort();
                songSortStrategy.sort(actualPlaylist);
                colorSelectedProxy.changeJlist( actualPlaylist, jListPlaylist, "Sort by duration", sortlabel );
            }
        });
        sortByArtist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songSortStrategy = new ArtistSort();
                songSortStrategy.sort(actualPlaylist);
                colorSelectedProxy.changeJlist( actualPlaylist, jListPlaylist, "Sort by artist", sortlabel );
            }
        });
    }
}
