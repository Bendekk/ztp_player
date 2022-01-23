package AppPackage;

import javax.swing.*;

public class PauseCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, Thread a, MP3Player k) {
        if( k.a!=null ) {
            if (frame.jButtonPause.getText().compareTo("Pause") == 0) {
                frame.jButtonPause.setText("Resume");
                a.suspend();
                if (k.fileCurrentlyPlaying != null) {
                    frame.jTextFieldPlayingFile.setText("Paused on: " + k.fileCurrentlyPlaying.getName());
                }
            } else {
                frame.jButtonPause.setText("Pause");
                frame.jTextFieldPlayingFile.setText("Playing: " + k.fileCurrentlyPlaying.getName());
                a.resume();
            }
        }
    }
}
