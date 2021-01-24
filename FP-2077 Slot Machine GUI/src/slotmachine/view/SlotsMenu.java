package slotmachine.view;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import slotmachine.controller.MenuListener;
import slotmachine.model.SlotMachineImpl;

// The drop-down menu bar which contains all the functions
@SuppressWarnings("serial")
public class SlotsMenu extends JMenuBar implements PropertyChangeListener {

   // Member variables so they can be enabled/disabled as required by methods
   private JMenuItem placeBet;
   private JMenuItem spin;
   private JMenuItem register;
   private JMenuItem cashOut;
   private JMenuItem resetBets;
   private JMenuItem addCredits;

   private SlotsInfoPanel info;

   public SlotsMenu(GuiCallBack gui, SlotMachineImpl model, SlotsInfoPanel info) {

      gui.addPropertyChangeListener(this);

      this.info = info;

      MenuListener ml = new MenuListener(model, info);

      // Game Menu
      JMenu gameMenu = new JMenu("Game");
      gameMenu.setMnemonic(KeyEvent.VK_G);

      placeBet = new JMenuItem("Place Bet", KeyEvent.VK_B);
      placeBet.setEnabled(false);
      placeBet.addActionListener(ml);
      gameMenu.add(placeBet);

      resetBets = new JMenuItem("Reset Bets", KeyEvent.VK_R);
      resetBets.setEnabled(false);
      resetBets.addActionListener(ml);
      gameMenu.add(resetBets);

      gameMenu.addSeparator();

      spin = new JMenuItem("Spin to Win", KeyEvent.VK_S);
      spin.setEnabled(false);
      spin.addActionListener(ml);
      gameMenu.add(spin);

      // Turns Sub-Menu
      JMenu turnsMenu = new JMenu("Number of Turns");
      turnsMenu.setMnemonic(KeyEvent.VK_N);
      ButtonGroup turnsGroup = new ButtonGroup();
      JRadioButtonMenuItem quickT = new JRadioButtonMenuItem("Quick (5 turns)");
      quickT.setMnemonic(KeyEvent.VK_Q);
      quickT.addActionListener(ml);
      turnsGroup.add(quickT);
      turnsMenu.add(quickT);

      JRadioButtonMenuItem shortT = new JRadioButtonMenuItem("Short (10 turns)");
      shortT.setMnemonic(KeyEvent.VK_S);
      shortT.addActionListener(ml);
      turnsGroup.add(shortT);
      turnsMenu.add(shortT);

      JRadioButtonMenuItem defaultT = new JRadioButtonMenuItem("Default (20 turns)");
      defaultT.setMnemonic(KeyEvent.VK_D);
      defaultT.addActionListener(ml);
      turnsGroup.add(defaultT);
      defaultT.setSelected(true);
      turnsMenu.add(defaultT);

      JRadioButtonMenuItem longT = new JRadioButtonMenuItem("Long (50 turns)");
      longT.setMnemonic(KeyEvent.VK_L);
      longT.addActionListener(ml);
      turnsGroup.add(longT);
      turnsMenu.add(longT);

      gameMenu.add(turnsMenu);

      // Delay Sub-Menu
      JMenu delayMenu = new JMenu("Turn Delay");
      delayMenu.setMnemonic(KeyEvent.VK_T);
      ButtonGroup delayGroup = new ButtonGroup();
      JRadioButtonMenuItem fastD = new JRadioButtonMenuItem("Fast (0.05s)");
      fastD.setMnemonic(KeyEvent.VK_F);
      fastD.addActionListener(ml);
      delayGroup.add(fastD);
      delayMenu.add(fastD);

      JRadioButtonMenuItem defaultD = new JRadioButtonMenuItem("Default (0.1s)");
      defaultD.setMnemonic(KeyEvent.VK_D);
      defaultD.addActionListener(ml);
      delayGroup.add(defaultD);
      defaultD.setSelected(true);
      delayMenu.add(defaultD);

      JRadioButtonMenuItem slowD = new JRadioButtonMenuItem("Slow (2.5s)");
      slowD.setMnemonic(KeyEvent.VK_S);
      slowD.addActionListener(ml);
      delayGroup.add(slowD);
      delayMenu.add(slowD);

      JRadioButtonMenuItem verySlowD = new JRadioButtonMenuItem("Very Slow (1s)");
      verySlowD.setMnemonic(KeyEvent.VK_V);
      verySlowD.addActionListener(ml);
      delayGroup.add(verySlowD);
      delayMenu.add(verySlowD);

      gameMenu.add(delayMenu);

      gameMenu.addSeparator();

      // Game Menu
      JMenuItem results = new JMenuItem("Winning Odds", KeyEvent.VK_W);
      results.addActionListener(ml);
      gameMenu.add(results);

      gameMenu.addSeparator();

      JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_X);
      exit.addActionListener(ml);
      gameMenu.add(exit);

      add(gameMenu);

      // Player Menu
      JMenu playerMenu = new JMenu("Player");
      playerMenu.setMnemonic(KeyEvent.VK_P);

      register = new JMenuItem("Register Player", KeyEvent.VK_R);
      register.addActionListener(ml);
      playerMenu.add(register);

      addCredits = new JMenuItem("Add Credits", KeyEvent.VK_A);
      addCredits.setEnabled(false);
      addCredits.addActionListener(ml);
      playerMenu.add(addCredits);

      cashOut = new JMenuItem("Cash Out Player", KeyEvent.VK_C);
      cashOut.setEnabled(false);
      cashOut.addActionListener(ml);
      playerMenu.add(cashOut);

      add(playerMenu);

      // Help Menu
      JMenu helpMenu = new JMenu("Help");
      helpMenu.setMnemonic(KeyEvent.VK_H);

      JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);
      about.addActionListener(ml);

      helpMenu.add(about);
      add(helpMenu);
   }

   // Switching enabled/disabled helps to reduce the need for extra validation
   // because the user features are controlled
   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName() == "Update Player") {
         register.setEnabled(false);
         placeBet.setEnabled(true);
         cashOut.setEnabled(true);
         spin.setEnabled(false);
         resetBets.setEnabled(true);
         addCredits.setEnabled(true);
      }

      if (evt.getPropertyName().equals("Cash Out")) {
         register.setEnabled(true);
         placeBet.setEnabled(false);
         cashOut.setEnabled(false);
         resetBets.setEnabled(false);
         spin.setEnabled(false);
         addCredits.setEnabled(true);
      }

      if (evt.getPropertyName().equals("Spin")) {
         register.setEnabled(false);
         placeBet.setEnabled(false);
         cashOut.setEnabled(false);
         spin.setEnabled(false);
         resetBets.setEnabled(false);
         addCredits.setEnabled(false);
      }

      if (evt.getPropertyName().equals("Spin Complete")) {
         register.setEnabled(false);
         placeBet.setEnabled(true);
         cashOut.setEnabled(true);
         spin.setEnabled(true);
         resetBets.setEnabled(true);
         addCredits.setEnabled(true);
      }

      if (evt.getPropertyName().equals("Bet Totals") ||
          evt.getPropertyName().equals("Place Bet")) {
         if (info.getBetAmt() > 0) {
            spin.setEnabled(true);
         }
         else {
            spin.setEnabled(false);
         }
      }
   }

}