package slotmachine.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import slotmachine.model.SlotMachineImpl;
import slotmachine.view.SlotsInfoPanel;
import slotmachine.view.dialogs.AboutDialog;
import slotmachine.view.dialogs.AddCreditsDialog;
import slotmachine.view.dialogs.BettingDialog;
import slotmachine.view.dialogs.CashOutDialog;
import slotmachine.view.dialogs.RegisterDialog;
import slotmachine.view.dialogs.WinningOddsDialog;

// Controller for the whole drop-down menu
public class MenuListener implements ActionListener {

   private SlotMachineImpl model;
   private SlotsInfoPanel info;

   // Default values for wheel spin characteristics
   private static int turns = 20;
   private static int delay = 50;

   public MenuListener(SlotMachineImpl model, SlotsInfoPanel info) {
      this.model = model;
      this.info = info;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JMenuItem) {
         JMenuItem caller = (JMenuItem) e.getSource();

         switch (caller.getText()) {
            // File Menu
            case "Place Bet":
               bettingDialog(model, info);
               break;

            case "Reset Bets":
               model.resetBets();
               break;

            case "Spin to Win":
               spin(model, turns, delay);
               break;

            case "Winning Odds":
               new WinningOddsDialog();
               break;

            case "Exit":
               System.exit(0);
               break;

            // Player Menu
            case "Register Player":
               registerPlayerDialog(model, info);
               break;

            case "Add Credits":
               new AddCreditsDialog(model);
               break;

            case "Cash Out Player":
               cashingOut(model, info);
               break;

            // Help Menu
            case "About":
               new AboutDialog();
               break;
         }
      }

      if (e.getSource() instanceof JRadioButtonMenuItem) {
         JRadioButtonMenuItem caller = (JRadioButtonMenuItem) e.getSource();

         switch (caller.getText()) {
            // Turns
            case "Quick (5 turns)":
               turns = 5;
               break;
            case "Short (10 turns)":
               turns = 10;
               break;
            case "Default (20 turns)":
               turns = 20;
               break;
            case "Long (50 turns)":
               turns = 50;
               break;

            // Delay
            case "Fast (0.05s)":
               delay = 50;
               break;
            case "Default (0.1s)":
               delay = 100;
               break;
            case "Slow (2.5s)":
               delay = 250;
               break;
            case "Very Slow (1s)":
               delay = 1000;
               break;
         }
      }
   }

   public static void registerPlayerDialog(SlotMachineImpl model, SlotsInfoPanel info) {
      new RegisterDialog(model, info);
   }

   public static void bettingDialog(SlotMachineImpl model, SlotsInfoPanel info) {
      new BettingDialog(model, info);
   }

   public static void cashingOut(SlotMachineImpl model, SlotsInfoPanel info) {
      new CashOutDialog(model, info);
   }

   // New thread on the slow-running spinToWin method
   public static void spin(SlotMachineImpl model, int turns, int delay) {
      new Thread() {
         @Override
         public void run() {
            model.spinToWin(turns, delay);

         }
      }.start();
   }

   // Used when called from the tool bar
   public static void spin(SlotMachineImpl model) {
      spin(model, turns, delay);
   }

}
