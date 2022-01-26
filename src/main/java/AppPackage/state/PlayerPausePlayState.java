package AppPackage.state;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

public interface PlayerPausePlayState {
    void doAction(PlayerHoldingState playerHoldingState, ProjectForm frame, MP3Player k);
}
