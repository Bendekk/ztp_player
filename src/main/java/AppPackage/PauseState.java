package AppPackage;

public class PauseState  extends State {
    PauseState(MP3Player mp3Player) {
        super(mp3Player);
    }

    @Override
    public String onPlay() {
        mp3Player.changeState(new PlayState(mp3Player));
        return null;
    }

    @Override
    public String onPause() {
        return null;
    }
}
