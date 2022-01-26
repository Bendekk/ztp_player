package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;
import AppPackage.facade.ReadPlaylistFacade;

import javax.swing.*;
import java.io.File;

public class BrowsePlaylistCommand implements AlternateCommand{
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        frame.setIsFileBrowsed( false );
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            mp3Player.setPlaylistPhysicalFile( new File( fileChooser.getSelectedFile().getAbsolutePath() ) );
            frame.getjTextFieldPlayingFile().setText("Selected playlist: " + fileChooser.getSelectedFile().getName() );
            mp3Player.getReadPlaylistFacade().read(mp3Player, mp3Player.getCheckForDuplicatesManager(), mp3Player.getActualPlaylist() );
            frame.DrawPlaylist();
            if( mp3Player.getFilePlaylist() != null && !mp3Player.getFilePlaylist().isEmpty() )
                if( mp3Player.getFilePlaylist().get(0) != null )
                    mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get( 0 ) );
            frame.setFirstBrowse( false );
        } else {
            if ( frame.getIsFirstBrowse() )
                System.exit(69);
        }
    }
}
