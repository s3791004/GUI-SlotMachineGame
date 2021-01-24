package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.LineNum;
import slotmachine.view.dialogs.BettingDialog;

// Control for the place a bet option
public class BettingListener implements ActionListener {

   private BettingDialog dialog;
   private SlotMachineImpl model;

   public BettingListener(BettingDialog dialog, SlotMachineImpl model) {
      this.dialog = dialog;
      this.model = model;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JButton) {
         JButton caller = (JButton) e.getSource();

         switch (caller.getText()) {
            case "Place Bet":
               if (dialog.getAvailCredits() < sumBets()) {
                  JOptionPane
                           .showMessageDialog(null,
                                              "Not enough credits for that bet! :( ",
                                              "alert", JOptionPane.ERROR_MESSAGE);
                  break;
               }
               
               // Conditionals to only bother setting a bet where amount is not zero
               if (dialog.getLine1() > 0) {
                  model.placeBet(dialog.getLine1(), LineNum.LINE1);
               }
               if (dialog.getLine2() > 0) {
                  model.placeBet(dialog.getLine2(), LineNum.LINE2);
               }
               if (dialog.getLine3() > 0) {
                  model.placeBet(dialog.getLine3(), LineNum.LINE3);
               }
               if (dialog.getLine4() > 0) {
                  model.placeBet(dialog.getLine4(), LineNum.LINE4);
               }
               if (dialog.getLine5() > 0) {
                  model.placeBet(dialog.getLine5(), LineNum.LINE5);
               }
               dialog.dispose();
               break;

            case "Cancel":
               dialog.dispose();
         }
      }
   }

   private int sumBets() {
      return dialog.getLine1() + dialog.getLine2() + dialog.getLine3() +
             dialog.getLine4() + dialog.getLine5();
   }

}
