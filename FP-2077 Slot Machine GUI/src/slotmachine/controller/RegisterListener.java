package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.SlotsInfoPanel;
import slotmachine.view.dialogs.RegisterDialog;

// Controller for when registering a player includes some validation to prevent
// errors being thrown from the console logger
public class RegisterListener implements ActionListener {

   private RegisterDialog dialog;
   private SlotMachineImpl model;
   private SlotsInfoPanel info;

   public RegisterListener(RegisterDialog dialog, SlotMachineImpl model, SlotsInfoPanel info) {
      this.dialog = dialog;
      this.model = model;
      this.info = info;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JButton) {
         JButton caller = (JButton) e.getSource();

         switch (caller.getText()) {

            // Validation for empties
            case "Register":
               if (dialog.getIdText().equals("") ||
                   dialog.getNameText().equals("") ||
                   dialog.getCreditsText().equals("")) {
                  JOptionPane.showMessageDialog(null, "Fields cannot be empty!",
                                                "alert", JOptionPane.ERROR_MESSAGE);
                  break;
               }

               // Validate for string pattern in "12345A" format
               if (!dialog.getIdText().matches("\\d{5}[a-zA-z]")) {
                  JOptionPane.showMessageDialog(null,
                                                "ID must be in format \"12345A\"!",
                                                "alert",
                                                JOptionPane.ERROR_MESSAGE);
                  break;
               }

               // Check for not a number. Also could have used a
               // FormattedTextField on the dialog.
               try {
                  Integer.parseInt(dialog.getCreditsText());
               }
               catch (Exception e1) {
                  JOptionPane.showMessageDialog(null, "Credits must be a number!",
                                                "alert", JOptionPane.ERROR_MESSAGE);
                  break;
               }

               // Register the player if all is good
               model.registerPlayer(dialog.getIdText(), dialog.getNameText(),
                                    Integer.parseInt(dialog.getCreditsText()));
               info.setAvatar(dialog.getAvatar());
               dialog.dispose();
               break;

            case "Cancel":
               dialog.dispose();
         }
      }

   }

}
