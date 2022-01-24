package AppPackage;

import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.FileInputStream;

public class PlayCommand extends AlternateCommand {
    @Override
    public void execute(ProjectForm frame, Thread a, MP3Player k, PlayerHoldingState playerHoldingState) {
        if(k.a!=null)
        {
            if(k.a.isAlive())
            {
                k.a.stop();
            }
        }
        k.a = new Thread ()
        {
            public void run()
            {
                try
                {
                    if(k.fileCurrentlyPlaying!=null)
                    {
                        FileInputStream buff = new FileInputStream(k.fileCurrentlyPlaying);
                        k.myplayer = new Player(buff);
                        if (k.myplayer != null)
                        {
                            frame.jTextFieldPlayingFile.setText("Playing: " + k.fileCurrentlyPlaying.getName());
                            k.myplayer.play();
                            k.Browsed = false;
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.printf("Error Playing File: %s\n", e);
                }
            }

        };
        k.a.start();
    }
}
