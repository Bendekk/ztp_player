package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.state.PlayerHoldingState;
import AppPackage.ProjectForm;
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class PlayCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, MP3Player k, PlayerHoldingState playerHoldingState) {
        if( k.getA() != null ) {
            if( k.getA().isAlive() ) {
                k.getA().stop();
            }
        }
        k.setA( new Thread () {
            public void run() {
                try {
                    if( k.getFileCurrentlyPlaying() != null ) {
                        FileInputStream buff = new FileInputStream( k.getFileCurrentlyPlaying() );
                        k.setMyplayer( new Player( buff ) );
                        if( k.getMyplayer() != null ) {
                            frame.getjTextFieldPlayingFile().setText("Playing: " + k.getFileCurrentlyPlaying().getName());
                            k.getMyplayer().play();
//                            k.Browsed = false;
                        }
                    }
                }
                catch(Exception e) {
                    System.out.printf("Error Playing File: %s\n", e);
                }
            }
        });
        k.getA().start();
    }
}
