package devices;
import java.io.Serializable;

public class SecurityControl implements Serializable {
    public void arm() {
        System.out.println("Security Control is armed");
    }

    public void disarm() {
        System.out.println("Security Control is disarmed");
    }
}