package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

import java.util.Random;

public class ShuffleCommand implements AlternateCommand {
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        if(mp3Player.getFilePlaylist() != null){
            Random rnd = new Random();
            if( mp3Player.getFilePlaylist().size() > 1 ) {
                int choice = rnd.nextInt(mp3Player.getFilePlaylist().size());
                mp3Player.setFileCurrentlyPlaying( mp3Player.getFilePlaylist().get( choice ) );
                new PlayCommand().execute( frame, mp3Player );
            }
            else{
                System.out.printf("You cannot shuffle a playlist with less than 2 songs.\n");
            }
        }
    }
}