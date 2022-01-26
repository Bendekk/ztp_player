package AppPackage;

import javax.swing.*;

public class ColorSelectedProxy implements ProxyInterface{
    PrintingPlaylistOnJlist printingPlaylistOnJlist;
    public ColorSelectedProxy( PrintingPlaylistOnJlist printingPlaylistOnJlist ){
         this.printingPlaylistOnJlist = printingPlaylistOnJlist;
    }

    public void color(Playlist playlist, JList jListPlaylist, String text, JLabel labelText){
        printingPlaylistOnJlist.color(playlist, jListPlaylist, text, labelText);
        labelText.setText(text);
    }


}
