package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;
import AppPackage.state.PlayerPlayState;

public class StopCommand implements AlternateCommand {
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        if( mp3Player.getA() != null ){
            mp3Player.getPlayerHoldingState().setState(new PlayerPlayState() );
            mp3Player.getPlayerHoldingState().getState().doAction( mp3Player.getPlayerHoldingState(), frame, mp3Player);
            mp3Player.getA().suspend();
            frame.getjTextFieldPlayingFile().setText("Stopped on: " + mp3Player.getFileCurrentlyPlaying().getName());
        }
    }
}
