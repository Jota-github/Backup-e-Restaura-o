package devices;
import java.io.Serializable;

public class Sound implements Serializable {
    private String description;

    public Sound(String description) {
        this.description = description;
    }

    public void on() {
        System.out.println(description + " sound is on");
    }

    public void off() {
        System.out.println(description + " sound is off");
    }

    public void setCd() {
        System.out.println(description + " sound is set for CD input");
    }

    public void setDvd() {
        System.out.println(description + " sound is set for DVD input");
    }

    public void setRadio() {
        System.out.println(description + " sound is set for Radio");
    }

    public void setVolume(int volume) {
        System.out.println(description + " sound volume set to " + volume);
    }
}