package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.SlotsInfoPanel;

// Controller for tool bar buttons being clicked
public class ToolBarListener implements ActionListener {

   private SlotMachineImpl model;
   private SlotsInfoPanel info;

   public ToolBarListener(SlotMachineImpl model, SlotsInfoPanel info) {
      this.model = model;
      this.info = info;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JButton) {
         JButton caller = (JButton) e.getSource();

         switch (caller.getName()) {
            case "Register":
               MenuListener.registerPlayerDialog(model, info);
               break;

            case "Place Bet":
               MenuListener.bettingDialog(model, info);
               break;

            case "Spin":
               MenuListener.spin(model);
               break;

            case "Cash Out Player":
               MenuListener.cashingOut(model, info);
               break;

         }

      }

   }

}
