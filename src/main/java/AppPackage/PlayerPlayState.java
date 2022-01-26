package AppPackage;

public class PlayerPlayState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k){
        frame.getjButtonPause().setText("Pause");
        frame.getjTextFieldPlayingFile().setText("Playing: " + k.getFileCurrentlyPlaying().getName());
        k.getA().resume();
        playerHoldingState.setState(new PlayerPauseState());
    };
}
