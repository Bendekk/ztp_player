package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;
import AppPackage.Song;

public class NextSongCommand implements AlternateCommand{
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        if( mp3Player.getFileCurrentlyPlaying() != null && !mp3Player.getActualPlaylist().getCollectionOfSongs().isEmpty() )
        {
            if ( mp3Player.getIter().hasNext() ){
                Song song = (Song) mp3Player.getIter().next();
                mp3Player.setFileCurrentlyPlaying( song.getFile() );
            }
        }
        if( mp3Player.getA() != null && mp3Player.getFileCurrentlyPlaying() != null )
            new PlayCommand().execute( frame, mp3Player );
    }
}
