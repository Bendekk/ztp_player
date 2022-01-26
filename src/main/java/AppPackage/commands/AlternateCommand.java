package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.state.PlayerHoldingState;
import AppPackage.ProjectForm;

public abstract class AlternateCommand {
    AlternateCommand() {}

    public abstract void execute(ProjectForm frame, MP3Player k, PlayerHoldingState playerHoldingState);
}
