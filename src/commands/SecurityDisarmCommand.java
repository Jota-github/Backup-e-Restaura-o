package commands;
import devices.SecurityControl;

public class SecurityDisarmCommand implements Command {
    private SecurityControl securityControl;

    public SecurityDisarmCommand(SecurityControl securityControl) {
        this.securityControl = securityControl;
    }

    public void execute() {
        securityControl.disarm();
    }
}