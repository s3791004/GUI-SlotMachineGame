package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.NumberFormatter;

import slotmachine.controller.AddCreditsListener;
import slotmachine.model.SlotMachineImpl;

// Dialog for adding credits
@SuppressWarnings("serial")
public class AddCreditsDialog extends JDialog {

   final int COLUMNS = 20;

   private JFormattedTextField credsToAdd;

   public AddCreditsDialog(SlotMachineImpl model) {

      AddCreditsListener acl = new AddCreditsListener(this, model);

      // Formatter to prevent invalid (non-integer) data entry. Inspiration
      // from https://stackoverflow.com/questions/11093326/restricting-
      // jtextfield-input-to-integers and https://docs.oracle.com/javase
      // /tutorial/uiswing/components/formattedtextfield.html
      NumberFormat format = NumberFormat.getInstance();
      NumberFormatter formatter = new NumberFormatter(format);
      formatter.setValueClass(Integer.class);
      formatter.setMinimum(1);
      formatter.setAllowsInvalid(false);

      this.setTitle("Cash Out");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel("Amounts of Credits to Add:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      credsToAdd = new JFormattedTextField(formatter);
      credsToAdd.setColumns(COLUMNS);
      add(credsToAdd, gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.anchor = GridBagConstraints.LINE_START;
      JButton addButton = new JButton("Add Credits");
      addButton.addActionListener(acl);
      add(addButton, gbc);

      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.anchor = GridBagConstraints.LINE_END;
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(acl);
      add(cancelButton, gbc);

      setSize(200, 300);
      setModal(true);
      setLocationRelativeTo(null);
      pack();
      setVisible(true);
   }

   // Getter to validate in the control and display success message
   public int getCredsToAdd() {
      int val = 0;
      if (credsToAdd.getValue() != null) {
         val = (int) credsToAdd.getValue();
      }
      return val;
   }
}
