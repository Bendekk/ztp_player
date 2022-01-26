package AppPackage.proxy;

import AppPackage.iterator.Playlist;

import javax.swing.*;

public class LabelChangingProxy implements ProxyInterface {
    PrintingPlaylistOnJlist printingPlaylistOnJlist;
    public LabelChangingProxy(PrintingPlaylistOnJlist printingPlaylistOnJlist ){
         this.printingPlaylistOnJlist = printingPlaylistOnJlist;
    }

    public void changeJlist(Playlist playlist, JList jListPlaylist, String text, JLabel labelText){
        printingPlaylistOnJlist.changeJlist(playlist, jListPlaylist, text, labelText);
        labelText.setText(text);
    }


}
