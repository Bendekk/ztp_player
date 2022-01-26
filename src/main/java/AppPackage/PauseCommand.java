package AppPackage;

public class PauseCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, MP3Player k, PlayerHoldingState playerHoldingState) {
        if( k.getA() != null ) {
            playerHoldingState.getState().doAction( playerHoldingState, frame, k);
        }
    }
}
