package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.Song;
import AppPackage.iterator.Playlist;
import AppPackage.state.PlayerHoldingState;
import AppPackage.ProjectForm;

import java.util.Iterator;

public abstract class AlternateCommand {

    public abstract void execute(ProjectForm frame, MP3Player mp3Player);
}
