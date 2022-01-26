package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

public class ClearPlaylistCommand implements AlternateCommand {
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        if(mp3Player.getFilePlaylist() != null) {
            mp3Player.getFilePlaylist().clear();
            mp3Player.getActualPlaylist().clear();
            frame.WritePlaylistFile();
            frame.DrawPlaylist();
        }
    }
}