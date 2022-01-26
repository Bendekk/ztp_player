package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.state.PlayerHoldingState;
import AppPackage.ProjectForm;

public class PauseCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, MP3Player k, PlayerHoldingState playerHoldingState) {
        if( k.getA() != null ) {
            playerHoldingState.getState().doAction( playerHoldingState, frame, k);
        }
    }
}
