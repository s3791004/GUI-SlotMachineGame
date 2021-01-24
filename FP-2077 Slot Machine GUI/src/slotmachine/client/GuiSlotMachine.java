package slotmachine.client;

import javax.swing.SwingUtilities;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.ConsoleLoggerCallback;
import slotmachine.view.GuiCallBack;
import slotmachine.view.SlotsFrame;

public class GuiSlotMachine {

   public static void main(String[] args) {

      SlotMachineImpl model = new SlotMachineImpl();

      GuiCallBack gui = new GuiCallBack();

      model.addCallback(new ConsoleLoggerCallback());
      model.addCallback(gui);

      SwingUtilities.invokeLater(new Runnable() {

         @Override
         public void run() {
            new SlotsFrame(gui, model);
         }
      });

   }

}