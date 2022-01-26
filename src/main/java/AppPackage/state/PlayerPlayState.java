package AppPackage.state;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

public class PlayerPlayState implements PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k){
        frame.getjButtonPause().setText("Pause");
        frame.getjTextFieldPlayingFile().setText("Playing: " + k.getFileCurrentlyPlaying().getName());
        k.getA().resume();
        playerHoldingState.setState(new PlayerPauseState());
    };
}
