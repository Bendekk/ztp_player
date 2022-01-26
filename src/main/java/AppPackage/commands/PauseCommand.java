package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.state.PlayerHoldingState;
import AppPackage.ProjectForm;

public class PauseCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, MP3Player mp3Player ) {
        if( mp3Player.getA() != null ) {
            PlayerHoldingState playerHoldingState = frame.getPlayerHoldingState();
            playerHoldingState.getState().doAction( playerHoldingState, frame, mp3Player);
        }
    }
}
