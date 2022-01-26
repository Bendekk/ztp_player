package AppPackage;

public class PlayerPlayState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k){
        frame.getjButtonPause().setText("Pause");
        frame.getjTextFieldPlayingFile().setText("Playing: " + k.fileCurrentlyPlaying.getName());
        k.a.resume();
        playerHoldingState.setState(new PlayerPauseState());
    };
}
