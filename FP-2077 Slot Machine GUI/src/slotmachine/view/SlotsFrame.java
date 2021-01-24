package slotmachine.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import slotmachine.model.SlotMachineImpl;

// Main frame for the GUI 
@SuppressWarnings("serial")
public class SlotsFrame extends JFrame {
	
	public SlotsFrame(GuiCallBack gui, SlotMachineImpl model) {
		super("Slots Game");
		setLayout(new BorderLayout());
		SlotsInfoPanel info = new SlotsInfoPanel(gui);
		add(info, BorderLayout.WEST);
		add(new SlotsToolBar(gui, model, info), BorderLayout.NORTH);
		add(new SlotsWheelPanel(gui, model), BorderLayout.CENTER);
		add(new SlotsStatusBar(gui), BorderLayout.SOUTH);
		add(new SlotsLineNums(), BorderLayout.EAST);
		setJMenuBar(new SlotsMenu(gui, model, info));
		setMinimumSize(new Dimension(480, 360));
		setSize(640, 480);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
				
	}

}
