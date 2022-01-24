package AppPackage;

public class PlayerReadyState extends PlayerPausePlayState{
    public void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, Thread a, MP3Player k){
//        body
        playerHoldingState.setState(new PlayerPauseState());
    };
}
