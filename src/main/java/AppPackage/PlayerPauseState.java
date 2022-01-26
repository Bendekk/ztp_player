package AppPackage;

public class PlayerPauseState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k){
        frame.getjButtonPause().setText("Resume");
        k.a.suspend();
        if (k.fileCurrentlyPlaying != null)
            frame.getjTextFieldPlayingFile().setText("Paused on: " + k.fileCurrentlyPlaying.getName());
        playerHoldingState.setState(new PlayerPlayState());
    };

}
