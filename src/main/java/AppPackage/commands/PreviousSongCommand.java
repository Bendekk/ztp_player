package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.Song;
import AppPackage.ProjectForm;


public class PreviousSongCommand implements AlternateCommand {
    @Override
    public void execute(ProjectForm frame, MP3Player mp3Player) {
            if( mp3Player.getFileCurrentlyPlaying() != null && !mp3Player.getActualPlaylist().getCollectionOfSongs().isEmpty() ) {
                if( mp3Player.getIterPrevious().hasNext() ){
                    Song song = mp3Player.getIterPrevious().next();
                    mp3Player.setFileCurrentlyPlaying( song.getFile() );
                }
            }
            if( mp3Player.getA() != null && mp3Player.getFileCurrentlyPlaying() != null )
                new PlayCommand().execute(frame, mp3Player);
    }
}
