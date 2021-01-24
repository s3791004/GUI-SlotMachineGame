package slotmachine.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import slotmachine.controller.ToolBarListener;
import slotmachine.model.SlotMachineImpl;

// Status bar incorporating some of the features from the main menu
@SuppressWarnings("serial")
public class SlotsToolBar extends JToolBar implements PropertyChangeListener {

   private JButton register;
   private JButton placeBet;
   private JButton spin;
   private JButton cashOut;

   private SlotsInfoPanel info;

   public SlotsToolBar(GuiCallBack gui, SlotMachineImpl model, SlotsInfoPanel info) {

      gui.addPropertyChangeListener(this);

      this.info = info;

      setLayout(new FlowLayout(FlowLayout.LEFT));
      setBackground(Color.BLUE);
      setFloatable(false);

      ToolBarListener tl = new ToolBarListener(model, info);

      register = new JButton(new ImageIcon("images/register.png"));
      register.setToolTipText("Register a new Player");
      register.setName("Register");
      register.addActionListener(tl);
      add(register);

      placeBet = new JButton(new ImageIcon("images/place_bet.png"));
      placeBet.setToolTipText("Place a Bet");
      placeBet.setName("Place Bet");
      placeBet.setEnabled(false);
      placeBet.addActionListener(tl);
      add(placeBet);

      spin = new JButton(new ImageIcon("images/spin.png"));
      spin.setToolTipText("Spin to Win!");
      spin.setName("Spin");
      spin.setEnabled(false);
      spin.addActionListener(tl);
      add(spin);

      cashOut = new JButton(new ImageIcon("images/cash_out.png"));
      cashOut.setToolTipText("Cash Out the current Player");
      cashOut.setName("Cash Out Player");
      cashOut.setEnabled(false);
      cashOut.addActionListener(tl);
      add(cashOut);

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
      }

      if (evt.getPropertyName().equals("Cash Out")) {
         register.setEnabled(true);
         placeBet.setEnabled(false);
         cashOut.setEnabled(false);
         spin.setEnabled(false);
      }

      if (evt.getPropertyName().equals("Spin")) {
         register.setEnabled(false);
         placeBet.setEnabled(false);
         cashOut.setEnabled(false);
         spin.setEnabled(false);
      }

      if (evt.getPropertyName().equals("Spin Complete")) {
         register.setEnabled(false);
         placeBet.setEnabled(true);
         cashOut.setEnabled(true);
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