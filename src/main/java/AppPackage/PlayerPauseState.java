package AppPackage;

public class PlayerPauseState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k){
        frame.getjButtonPause().setText("Resume");
        k.getA().suspend();
        if (k.getFileCurrentlyPlaying() != null)
            frame.getjTextFieldPlayingFile().setText("Paused on: " + k.getFileCurrentlyPlaying().getName());
        playerHoldingState.setState(new PlayerPlayState());
    };

}
