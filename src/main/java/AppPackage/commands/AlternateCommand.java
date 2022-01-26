package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;


public interface AlternateCommand {

    void execute(ProjectForm frame, MP3Player mp3Player);
}
