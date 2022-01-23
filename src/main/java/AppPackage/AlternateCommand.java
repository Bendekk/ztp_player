package AppPackage;

import javax.swing.*;

public abstract class AlternateCommand {
//    public MP3Player mp3Player;
//    private State state;

    //    public AlternateInputMethod(MP3Player mp3Player) {
//        this.mp3Player = mp3Player;
//    }
    AlternateCommand() {

    }

    public abstract void execute(ProjectForm frame, Thread a, MP3Player k);
}
