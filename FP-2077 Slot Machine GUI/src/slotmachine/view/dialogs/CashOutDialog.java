package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import slotmachine.controller.CashOutListener;
import slotmachine.model.SlotMachineImpl;
import slotmachine.view.SlotsInfoPanel;

// Dialog to do some cashing out
@SuppressWarnings("serial")
public class CashOutDialog extends JDialog {
   
   final int COLUMNS = 20;

   public CashOutDialog(SlotMachineImpl model, SlotsInfoPanel info) {
      
      CashOutListener col = new CashOutListener(this, model);
      
      this.setTitle("Cash Out");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel("Current Credits:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      JTextField creds = new JTextField(Integer.toString(info.getCreditsAmt()), COLUMNS);
      creds.setEditable(false);
      add(creds, gbc);
      
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.anchor = GridBagConstraints.LINE_START;
      JButton registerButton = new JButton("Take the Cash!");
      registerButton.addActionListener(col);
      add(registerButton, gbc);
      
      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.anchor = GridBagConstraints.LINE_END;
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(col);
      add(cancelButton, gbc);
      
      setSize(200, 300);
      setModal(true);
      setLocationRelativeTo(null);
      pack();
      setVisible(true);
      
   }

}
