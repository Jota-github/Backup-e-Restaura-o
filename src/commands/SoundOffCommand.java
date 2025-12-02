package commands;
import devices.Sound;

public class SoundOffCommand implements Command {
    private Sound sound;

    public SoundOffCommand(Sound sound) {
        this.sound = sound;
    }

    public void execute() {
        sound.off();
    }
}