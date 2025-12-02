package client;

import commands.*;
import devices.*;
import invoker.RemoteControl;

public class Client {
    public static void main(String[] args) {
        // 1. Configuração Inicial
        RemoteControl remote = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        TV livingRoomTV = new TV("Living Room");
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor("Garage");
        Sound stereo = new Sound("Living Room");

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        TVOnCommand livingRoomTVOn = new TVOnCommand(livingRoomTV);
        TVOffCommand livingRoomTVOff = new TVOffCommand(livingRoomTV);
        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);
        GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);
        SoundOnCommand stereoOn = new SoundOnCommand(stereo);
        SoundOffCommand stereoOff = new SoundOffCommand(stereo);

        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, livingRoomTVOn, livingRoomTVOff);
        remote.setCommand(2, ceilingFanHigh, ceilingFanOff);
        remote.setCommand(3, stereoOn, stereoOff);
        remote.setCommand(4, garageDoorUp, garageDoorDown);

        // 2. Usando o controle (Isso vai salvar no disco)
        System.out.println("\n--- USANDO O CONTROLE REMOTO (Gravando no Histórico) ---");
        remote.onButtonWasPushed(0); 
        remote.offButtonWasPushed(0); 
        remote.onButtonWasPushed(1); 
        remote.offButtonWasPushed(1); 
        remote.onButtonWasPushed(2); 
        remote.offButtonWasPushed(2); 
        remote.onButtonWasPushed(3); 
        remote.offButtonWasPushed(3); 
        remote.onButtonWasPushed(4); 
        remote.offButtonWasPushed(4); 

        // 3. Simulando a falha e recuperação
        System.out.println("\n\n--- SIMULANDO PANE ELÉTRICA (Reiniciando Sistema) ---");
        
        RemoteControl newRemote = new RemoteControl();
        newRemote.load(); // Aqui acontece a mágica
    }
}