package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import slotmachine.controller.RegisterListener;
import slotmachine.model.SlotMachineImpl;
import slotmachine.view.SlotsInfoPanel;

// Dialog when trying to register a player.
@SuppressWarnings("serial")
public class RegisterDialog extends JDialog {

   private final static int COLUMNS = 20;

   private JTextField idText;
   private JTextField nameText;
   private JTextField creditsText;
   
   private JToggleButton elf;
   private JToggleButton warlock;
   private JToggleButton wizard;

   public RegisterDialog(SlotMachineImpl model, SlotsInfoPanel info) {
      
      RegisterListener rl = new RegisterListener(this, model, info);
      
      setTitle("Register A New Player");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel("Player ID:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      idText = new JTextField(COLUMNS);
      add(idText, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(new JLabel("Player Name:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      nameText = new JTextField(COLUMNS);
      add(nameText, gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      add(new JLabel("Initial Credits:"), gbc);
      gbc.gridx = 1;
      gbc.gridy = 2;
      creditsText = new JTextField(COLUMNS);
      add(creditsText, gbc);

      // Toggles to select a player icon
      ButtonGroup iconsGroup = new ButtonGroup();

      gbc.gridx = 0;
      gbc.gridy = 3;
      elf = new JToggleButton(new ImageIcon("images/elf.png"));
      iconsGroup.add(elf);
      add(elf, gbc);

      gbc.gridx = 1;
      gbc.gridy = 3;
      warlock = new JToggleButton(new ImageIcon("images/warlock.png"));
      warlock.setSelected(true);
      iconsGroup.add(warlock);
      add(warlock, gbc);

      gbc.gridx = 2;
      gbc.gridy = 3;
      wizard = new JToggleButton(new ImageIcon("images/wizard.png"));
      iconsGroup.add(wizard);
      add(wizard, gbc);

      gbc.gridx = 0;
      gbc.gridy = 4;
      gbc.anchor = GridBagConstraints.LINE_START;
      JButton registerButton = new JButton("Register");
      registerButton.addActionListener(rl);
      add(registerButton, gbc);

      gbc.gridx = 1;
      gbc.gridy = 4;
      gbc.anchor = GridBagConstraints.LINE_END;
      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(rl);
      add(cancelButton, gbc);

      setSize(200, 300);
      setResizable(false);
      setModal(true);
      setLocationRelativeTo(null);
      pack();
      setVisible(true);
   }

   // Getters for the listener to do some validating
   public String getIdText() {
      return idText.getText();
   }

   public String getNameText() {
      return this.nameText.getText();
   }

   public String getCreditsText() {
      return creditsText.getText();
   }
   
   public String getAvatar() {
      String selection = "empty";
      if (elf.isSelected()) {
         selection = "elf";
      } else if (warlock.isSelected()) {
         selection = "warlock";
      } else if (wizard.isSelected()) {
         selection = "wizard";
      }
      
      return selection;
   }

}
