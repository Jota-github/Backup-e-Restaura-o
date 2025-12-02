package invoker;

import commands.Command;
import commands.NoCommand;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private List<Command> commandHistory; // Lista para o histórico de backup

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
        commandHistory.add(onCommands[slot]); // Adiciona ao histórico
        store(); // Salva no disco a cada ação (simulação de log)
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        commandHistory.add(offCommands[slot]); // Adiciona ao histórico
        store(); // Salva no disco
    }

    // Funcionalidade 1: STORE (Backup)
    public void store() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/command_log.ser"))) {
            out.writeObject(commandHistory);
            // System.out.println("[LOG] Backup saved to disk."); // Opcional: comentar para não poluir o console
        } catch (IOException e) {
            System.err.println("Error saving backup: " + e.getMessage());
        }
    }

    // Funcionalidade 2: LOAD (Restore)
    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/command_log.ser"))) {
            System.out.println("--- PANE ELÉTRICA RECUPERADA: Restaurando Estado... ---");
            commandHistory = (List<Command>) in.readObject();
            
            for (Command command : commandHistory) {
                command.execute(); // Re-executa cada comando salvo
            }
            System.out.println("--- Restauração Concluída ---");
            
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum backup encontrado. Iniciando sistema limpo.");
        } catch (Exception e) {
            System.err.println("Erro ao restaurar backup: " + e.getMessage());
        }
    }
    
    // Método auxiliar para imprimir o controle (opcional, igual ao livro)
    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuff.toString();
    }
}