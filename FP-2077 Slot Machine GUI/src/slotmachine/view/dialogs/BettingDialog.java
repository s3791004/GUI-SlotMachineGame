package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

import slotmachine.controller.BettingListener;
import slotmachine.model.SlotMachineImpl;
import slotmachine.view.SlotsInfoPanel;

// Dialog for placing some bets. Utilises formatter to do avoid the need
// for some validation/error messages e.g no negative bets
@SuppressWarnings("serial")
public class BettingDialog extends JDialog {

   private final static int COLUMNS = 10;

   private JFormattedTextField line1;
   private JFormattedTextField line2;
   private JFormattedTextField line3;
   private JFormattedTextField line4;
   private JFormattedTextField line5;
   private int availCredits;

   public BettingDialog(SlotMachineImpl model, SlotsInfoPanel info) {

      BettingListener bl = new BettingListener(this, model);

      availCredits = info.getCreditsAmt() - info.getBetAmt();

      // Formatter to prevent invalid (non-integer) data entry. Inspiration
      // from https://stackoverflow.com/questions/11093326/restricting-
      // jtextfield-input-to-integers and https://docs.oracle.com/javase
      // /tutorial/uiswing/components/formattedtextfield.html
      NumberFormat format = NumberFormat.getInstance();
      NumberFormatter formatter = new NumberFormatter(format);
      formatter.setValueClass(Integer.class);
      formatter.setMinimum(0);
      formatter.setAllowsInvalid(false);

      setTitle("Place A Bet");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(15, 15, 15, 15);

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel("Available Credits: "), gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      JFormattedTextField availCreditsText = new JFormattedTextField(formatter);
      availCreditsText.setColumns(COLUMNS);
      availCreditsText.setValue(availCredits);
      availCreditsText.setEditable(false);
      availCreditsText.setFocusable(false);
      add(availCreditsText, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(new JLabel("Line 1:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      line1 = new JFormattedTextField(formatter);
      line1.setColumns(COLUMNS);
      add(line1, gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      add(new JLabel("Line 2:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 2;
      line2 = new JFormattedTextField(formatter);
      line2.setColumns(COLUMNS);
      add(line2, gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
      add(new JLabel("Line 3:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 3;
      line3 = new JFormattedTextField(formatter);
      line3.setColumns(COLUMNS);
      add(line3, gbc);

      gbc.gridx = 0;
      gbc.gridy = 4;
      add(new JLabel("Line 4:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 4;
      line4 = new JFormattedTextField(formatter);
      line4.setColumns(COLUMNS);
      add(line4, gbc);

      gbc.gridx = 0;
      gbc.gridy = 5;
      add(new JLabel("Line 5:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 5;
      line5 = new JFormattedTextField(formatter);
      line5.setColumns(COLUMNS);
      add(line5, gbc);

      gbc.gridx = 0;
      gbc.gridy = 6;
      gbc.anchor = GridBagConstraints.LINE_START;
      JButton registerButton = new JButton("Place Bet");
      registerButton.addActionListener(bl);
      add(registerButton, gbc);

      gbc.gridx = 1;
      gbc.gridy = 6;
      gbc.anchor = GridBagConstraints.LINE_END;
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(bl);
      add(cancelButton, gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      gbc.gridheight = 6;
      gbc.gridwidth = 6;
      add(new JLabel(new ImageIcon("images/lines.png")), gbc);

      setSize(200, 300);
      setModal(true);
      setLocationRelativeTo(null);
      pack();
      setVisible(true);
   }

   // Getters for the Listener to validate before placing bets
   public int getLine1() {
      int val = 0;
      if (line1.getValue() != null) {
         val = (int) line1.getValue();
      }
      return val;
   }
   public int getLine2() {
      int val = 0;
      if (line2.getValue() != null) {
         val = (int) line2.getValue();
      }
      return val;
   }
   public int getLine3() {
      int val = 0;
      if (line3.getValue() != null) {
         val = (int) line3.getValue();
      }
      return val;
   }
   public int getLine4() {
      int val = 0;
      if (line4.getValue() != null) {
         val = (int) line4.getValue();
      }
      return val;
   }
   public int getLine5() {
      int val = 0;
      if (line5.getValue() != null) {
         val = (int) line5.getValue();
      }
      return val;
   }
   
   // Getter used to validate if enough credits available in listener
   public int getAvailCredits() {
      return availCredits;
   }
}
