package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.dialogs.CashOutDialog;

// Controller to cash a player out of the game
public class CashOutListener implements ActionListener {

   CashOutDialog dialog;
   SlotMachineImpl model;

   public CashOutListener(CashOutDialog dialog, SlotMachineImpl model) {
      this.dialog = dialog;
      this.model = model;
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getSource() instanceof JButton) {
         JButton caller = (JButton) e.getSource();

         switch (caller.getText()) {
            case "Take the Cash!":
               model.cashOut();
               dialog.dispose();
            case "Cancel":
               dialog.dispose();
         }
      }

   }
}