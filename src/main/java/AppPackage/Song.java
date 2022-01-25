package AppPackage;

import java.io.File;
import java.util.Date;

public class Song {
    private String name;
    private Integer duration;
    private File file;
    private String artist;

    public Song(String name, Integer duration, String artist, File file) {
        this.name = name;
        this.duration = duration;
        this.artist = artist;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean update(String name){
        if( this.name.compareTo(name) == 0 ) {
            System.out.printf("File: %s is already in the playlist.\n", name);
            return false;
        }
        return true;
    }
    public File getFile(){
        return file;
    }
}
