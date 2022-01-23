package AppPackage;

import java.io.File;
import java.util.Date;

public class Song {
    private String name;
    private Integer duration;
    private Date year;
    private File file;

    public Song(String name, Integer duration, Date year, File file) {
        this.name = name;
        this.duration = duration;
        this.year = year;
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
