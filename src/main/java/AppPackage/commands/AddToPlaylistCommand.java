package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

import java.util.ArrayList;

public class AddToPlaylistCommand implements AlternateCommand {
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        boolean GoFurther = true;

        if( frame.getIsFileBrowsed() ) {
            if (mp3Player.getFileCurrentlyPlaying() != null) {
                if (mp3Player.getFilePlaylist() == null) {
                    mp3Player.setFilePlaylist( new ArrayList() );
                }
                if (!mp3Player.getFilePlaylist().isEmpty()) {
                    GoFurther = frame.getCheckForDuplicatesManager().notifySubscribers(mp3Player.getFileCurrentlyPlaying().getName());
                }
                if (GoFurther) {
                    mp3Player.getFilePlaylist().add( mp3Player.getFileCurrentlyPlaying() );
                    frame.WritePlaylistFile();
                    frame.DrawPlaylist();
                }
            } else {
                System.out.printf("Please, select a file first!\n");
            }
        }
        else
            System.out.printf("Can't add playlist to playlist, you dumb dumb!\n");
        frame.getReadPlaylistFacade().read(mp3Player, frame.getCheckForDuplicatesManager(), frame.getActualPlaylist());
    }
}