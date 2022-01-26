package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

import java.io.File;

public class PrintPlaylistCommand implements AlternateCommand{
    @Override
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        frame.getReadPlaylistFacade().read( mp3Player, frame.getCheckForDuplicatesManager(), frame.getActualPlaylist() );
        if (mp3Player.getFilePlaylist() != null && !mp3Player.getFilePlaylist().isEmpty()) {
            System.out.printf("Playlist Content: \n\n");
            for (File s : mp3Player.getFilePlaylist()) {
                if (s != null) {
                    System.out.printf("%s\n", s.getName());
                }
            }
            System.out.printf("\nThere are %d songs in the playlist.\n", mp3Player.getFilePlaylist().size());
        } else {
            System.out.printf("Playlist is empty!\n");
        }
    }
}
