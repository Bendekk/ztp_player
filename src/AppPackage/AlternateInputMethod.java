package AppPackage;

public abstract class AlternateInputMethod {
    public MP3Player mp3Player;
    private State state;

    public AlternateInputMethod(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
    }

    void setState(State state) {
        this.state = state;
    }

    public void play() {
        state.onPlay();
    }

    public void pause() {
        state.onPause();
    }



}
