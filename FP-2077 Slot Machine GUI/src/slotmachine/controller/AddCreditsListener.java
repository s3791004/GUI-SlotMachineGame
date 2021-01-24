package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.dialogs.AddCreditsDialog;

// Listener for the dialog to add credits
public class AddCreditsListener implements ActionListener {

   private AddCreditsDialog dialog;
   private SlotMachineImpl model;

   public AddCreditsListener(AddCreditsDialog dialog,
                             SlotMachineImpl model) {
      this.dialog = dialog;
      this.model = model;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JButton) {
         JButton caller = (JButton) e.getSource();

         switch (caller.getText()) {
            case "Add Credits":
               if (dialog.getCredsToAdd() > 0) {
                  model.addCredits(dialog.getCredsToAdd());
                  JOptionPane.showMessageDialog(null,
                                                "Success! Added " + dialog.getCredsToAdd(),
                                                "OK!",
                                                JOptionPane.INFORMATION_MESSAGE);
               }
               else {
                  JOptionPane.showMessageDialog(null,
                                                "You have to enter something...",
                                                "alert",
                                                JOptionPane.ERROR_MESSAGE);
                  break;
               }
               dialog.dispose();
               break;

            case "Cancel":
               dialog.dispose();
               break;
         }
      }
   }
}
