package AppPackage.commands;

import AppPackage.MP3Player;
import AppPackage.ProjectForm;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowseCommand implements AlternateCommand{
    public void execute(ProjectForm frame, MP3Player mp3Player) {
        JFileChooser fileChooser = new JFileChooser();
        frame.setIsFileBrowsed( true );

        fileChooser.setFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            mp3Player.setFileCurrentlyPlaying(fileChooser.getSelectedFile());
            frame.getjTextFieldPlayingFile().setText("Selected: " + mp3Player.getFileCurrentlyPlaying().getName());
        }
    }
}
