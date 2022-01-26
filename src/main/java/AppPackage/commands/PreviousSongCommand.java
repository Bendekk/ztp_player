package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.Song;
import AppPackage.iterator.Playlist;
import AppPackage.observer.CheckForDuplicatesManager;
import AppPackage.state.PlayerHoldingState;
import AppPackage.ProjectForm;

import java.util.Iterator;

public class PreviousSongCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, MP3Player mp3Player) {
            if( mp3Player.getFileCurrentlyPlaying() != null && !frame.getActualPlaylist().getCollectionOfSongs().isEmpty() ) {
                if( frame.getIterPrevious().hasNext() ){
                    Song song = frame.getIterPrevious().next();
                    mp3Player.setFileCurrentlyPlaying( song.getFile() );
                }
            }
            if( mp3Player.getA() != null && mp3Player.getFileCurrentlyPlaying() != null )
                new PlayCommand().execute(frame, mp3Player);
    }
}
