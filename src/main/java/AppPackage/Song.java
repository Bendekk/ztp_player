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
}
