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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
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
