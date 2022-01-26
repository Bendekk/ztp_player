package AppPackage.proxy;

import AppPackage.iterator.Playlist;

import javax.swing.*;

public interface ProxyInterface {
    public void changeJlist(Playlist playlist, JList jListPlaylist, String text, JLabel labelText);
}
