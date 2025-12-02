package client;

import commands.*;
import devices.*;
import invoker.RemoteControl;

public class Client {
    public static void main(String[] args) {
        // 1. Criar o Controle Remoto
        RemoteControl remote = new RemoteControl();

        // 2. Criar os Dispositivos (Receivers)
        Light livingRoomLight = new Light("Living Room");
        TV livingRoomTV = new TV("Living Room");
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor("Garage");
        Sound stereo = new Sound("Living Room");

        // 3. Criar os Comandos (Command Objects)
        // Iluminação
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        // TV
        TVOnCommand livingRoomTVOn = new TVOnCommand(livingRoomTV);
        TVOffCommand livingRoomTVOff = new TVOffCommand(livingRoomTV);

        // Ventilador
        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        // Garagem
        GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

        // Som
        SoundOnCommand stereoOn = new SoundOnCommand(stereo);
        SoundOffCommand stereoOff = new SoundOffCommand(stereo);

        // 4. Configurar o Controle (Set Commands)
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, livingRoomTVOn, livingRoomTVOff);
        remote.setCommand(2, ceilingFanHigh, ceilingFanOff);
        remote.setCommand(3, stereoOn, stereoOff);
        remote.setCommand(4, garageDoorUp, garageDoorDown);

        // 5. Simular uso (Pressionar botões)
        System.out.println("\n--- USANDO O CONTROLE REMOTO (Gravando no Histórico) ---");
        remote.onButtonWasPushed(0); // Luz Liga
        remote.offButtonWasPushed(0); // Luz Desliga
        remote.onButtonWasPushed(1); // TV Liga
        remote.offButtonWasPushed(1); // TV Desliga
        remote.onButtonWasPushed(2); // Ventilador Alto
        remote.offButtonWasPushed(2); // Ventilador Desliga
        remote.onButtonWasPushed(3); // Som Liga
        remote.offButtonWasPushed(3); // Som Desliga
        remote.onButtonWasPushed(4); // Garagem Abre
        remote.offButtonWasPushed(4); // Garagem Fecha

        // 6. Simular Falha e Recuperação
        System.out.println("\n\n--- SIMULANDO PANE ELÉTRICA (Reiniciando Sistema) ---");
        
        // Criamos um NOVO controle, que não sabe de nada (memória vazia)
        RemoteControl newRemote = new RemoteControl();
        
        // Pedimos para ele carregar o backup do disco
        newRemote.load();
    }
}