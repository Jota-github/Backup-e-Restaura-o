package devices;
import java.io.Serializable;

public class TV implements Serializable {
    private String location;

    public TV(String location) {
        this.location = location;
    }

    public void on() {
        System.out.println(location + " TV is on");
    }

    public void off() {
        System.out.println(location + " TV is off");
    }

    public void setInputChannel() {
        System.out.println(location + " TV channel is set for DVD");
    }

    public void setVolume(int volume) {
        System.out.println(location + " TV volume set to " + volume);
    }
}