package AppPackage;

import java.util.Date;

public class Song {
    private String name;
    private Integer duration;
    private Date year;

    public Song(String name, Integer duration, Date year) {
        this.name = name;
        this.duration = duration;
        this.year = year;
    }

    public boolean update(String name){
        if( this.name.compareTo(name) == 0 ) {
            System.out.printf("File: %s is already in the playlist.\n", name);
            return false;
        }
        return true;
    }
}
