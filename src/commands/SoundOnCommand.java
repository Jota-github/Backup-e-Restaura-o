package commands;
import devices.Sound;
import java.io.Serializable;

public class SoundOnCommand implements Command {
    private Sound sound;

    public SoundOnCommand(Sound sound) {
        this.sound = sound;
    }

    public void execute() {
        sound.on();
        sound.setDvd();
        sound.setVolume(11);
    }
}