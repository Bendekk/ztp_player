package AppPackage;

public class PauseCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, Thread a, MP3Player k, PlayerHoldingState playerHoldingState) {
        if( k.a!=null ) {
            playerHoldingState.getState().doAction( playerHoldingState, frame, a, k);
        }
    }
}
