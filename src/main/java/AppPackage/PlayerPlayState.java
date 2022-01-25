package AppPackage;

public class PlayerPlayState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k){
        frame.jButtonPause.setText("Pause");
        frame.jTextFieldPlayingFile.setText("Playing: " + k.fileCurrentlyPlaying.getName());
        k.a.resume();
        playerHoldingState.setState(new PlayerPauseState());
    };
}
