package AppPackage;

public class PlayerPauseState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, Thread a, MP3Player k){
        frame.jButtonPause.setText("Resume");
        a.suspend();
        if (k.fileCurrentlyPlaying != null)
            frame.jTextFieldPlayingFile.setText("Paused on: " + k.fileCurrentlyPlaying.getName());
        playerHoldingState.setState(new PlayerPlayState());
    };

}
