package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

public class DisplayCommand implements AlternateCommand {
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        if( frame.getIsHidden() ) {
            frame.setSize(695, frame.getDisplayHeight() );
            frame.getScrollPane().setVisible(true);
            frame.getSortlabel().setVisible(true);
            frame.revalidate();
            frame.repaint();
            frame.getjButtonDisplayPlaylist().setText("Hide Playlist");
        }
        else{
            frame.setSize(578, frame.getDisplayHeight() );
            frame.getScrollPane().setVisible(false);
            frame.getSortlabel().setVisible(false);
            frame.revalidate();
            frame.repaint();
            frame.getjButtonDisplayPlaylist().setText("Display Playlist");
        }
        frame.setHidden( !frame.getIsHidden() );
    }
}