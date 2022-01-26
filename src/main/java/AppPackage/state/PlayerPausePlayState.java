package AppPackage.state;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

public abstract class PlayerPausePlayState {
    public abstract void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k);
}
