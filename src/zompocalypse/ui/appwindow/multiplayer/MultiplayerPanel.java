package zompocalypse.ui.appwindow.multiplayer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zompocalypse.ui.appwindow.UICommand;

/**
 * This panel presents the options for Multiplayer games to the user.
 * Those options are either:
 * <ul>
 * <li>Start Server</li>
 * <li>Connect to Server</li>
 * </ul>
 *
 * @author Sam Costigan
 */
public class MultiplayerPanel extends JPanel {

	private JButton btnServer;
	private JButton btnClient;

	private ActionListener action;

	public MultiplayerPanel(ActionListener action) {
		super();
		this.action = action;

		arrangeComponents();
	}

	private void arrangeComponents() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Insets buttonsInset = new Insets(20, 0, 0, 0);
		int positionY = 0;

		btnServer = new JButton("Start Server");
		btnServer.setActionCommand(UICommand.SERVER.getValue());
		btnServer.addActionListener(action);

		btnClient = new JButton("Connect to Server");
		btnClient.setActionCommand(UICommand.CLIENT.getValue());
		btnClient.addActionListener(action);

		constraints.insets = buttonsInset;
		constraints.gridy = positionY++;
		this.add(btnServer, constraints);

		constraints.gridy = positionY++;
		this.add(btnClient, constraints);
	}

}