package invoker;

import commands.Command;
import commands.NoCommand;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private List<Command> commandHistory;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        commandHistory = new ArrayList<>();

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
        commandHistory.add(onCommands[slot]);
        store(); 
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        commandHistory.add(offCommands[slot]);
        store(); 
    }

    // Backup: Salva o histórico no disco
    public void store() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/command_log.ser"))) {
            out.writeObject(commandHistory);
        } catch (IOException e) {
            System.err.println("Error saving backup: " + e.getMessage());
        }
    }

    // Restore: Carrega o histórico e reexecuta
    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/command_log.ser"))) {
            System.out.println("--- PANE ELÉTRICA RECUPERADA: Restaurando Estado... ---");
            commandHistory = (List<Command>) in.readObject();
            
            for (Command command : commandHistory) {
                command.execute();
            }
            System.out.println("--- Restauração Concluída ---");
            
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum backup encontrado. Iniciando sistema limpo.");
        } catch (Exception e) {
            System.err.println("Erro ao restaurar backup: " + e.getMessage());
        }
    }
}