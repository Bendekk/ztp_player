package AppPackage;

public abstract class State {
    MP3Player mp3Player;

    State(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    public abstract String onPlay();
    public abstract String onPause();
}
