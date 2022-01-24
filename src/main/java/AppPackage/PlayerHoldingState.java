package AppPackage;

public class PlayerHoldingState {
    private PlayerPausePlayState state;
    public PlayerHoldingState(PlayerPausePlayState state){
        this.state = state;
    }
    public void setState(PlayerPausePlayState state){
        this.state = state;
    }
    public PlayerPausePlayState getState(){
        return state;
    }
}
