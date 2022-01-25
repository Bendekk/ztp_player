package AppPackage;

public class PlayState extends State {

    PlayState(MP3Player mp3Player) {
        super(mp3Player);
    }

    @Override
    public String onPlay() {
        return null;
    }

    @Override
    public String onPause() {
        mp3Player.changeState(new PauseState(mp3Player));
        return null;
    }
}
