package zompocalypse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.KeyStroke;

import zompocalypse.gameworld.world.World;
import zompocalypse.ui.appwindow.MainFrame;
import zompocalypse.ui.appwindow.UICommand;

/**
 * This is the Single Player Event Listener which passes input from the user to
 * the game.
 *
 * @author Sam Costigan
 */
public class SinglePlayer extends GameListener {

	private final int id;
	private final World game;
	private MainFrame frame;

	public SinglePlayer(World game, int id) {
		this.game = game;
		this.id = id;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT
				|| code == KeyEvent.VK_KP_LEFT) {
			game.processCommand(id, UICommand.WEST.getValue());

		} else if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP
				|| code == KeyEvent.VK_KP_UP) {
			game.processCommand(id, UICommand.NORTH.getValue());

		} else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT
				|| code == KeyEvent.VK_KP_RIGHT) {
			game.processCommand(id, UICommand.EAST.getValue());

		} else if ((code == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			frame.processKeyPress(code, UICommand.OPTIONS.getValue());

		} else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN
				|| code == KeyEvent.VK_KP_DOWN) {
			game.processCommand(id, UICommand.SOUTH.getValue());

		} else if (code == KeyEvent.VK_COMMA) {
			frame.processKeyPress(code, UICommand.ROTATECLOCKWISE.getValue());

		} else if (code == KeyEvent.VK_PERIOD) {
			frame.processKeyPress(code,
					UICommand.ROTATEANTICLOCKWISE.getValue());

			// TODO this is just something for me to work with to be able to
			// start editing screens
		} else if (code == KeyEvent.VK_F8) { // expand north
			game.setEditMode();
		} else if (code == KeyEvent.VK_P) { // expand north
			game.toggleWalls();
		} else if (code == KeyEvent.VK_Y) { // expand north
			game.expandMap("north");
		} else if (code == KeyEvent.VK_H) { // shrink north
			game.shrinkMap("north");
		} else if (code == KeyEvent.VK_U) { // expand east
			game.expandMap("east");
		} else if (code == KeyEvent.VK_J) { // shrink east
			game.shrinkMap("east");
		} else if (code == KeyEvent.VK_I) { // expand south
			game.expandMap("south");
		} else if (code == KeyEvent.VK_K) { // shrink south
			game.shrinkMap("south");
		} else if (code == KeyEvent.VK_O) { // expand west
			game.expandMap("west");
		} else if (code == KeyEvent.VK_L) { // shrink west
			game.shrinkMap("west");
		} else if (code == KeyEvent.VK_Z) { // toggle zombie spawn point
			game.toggleZombieSpawnPoint();
		} else if (code == KeyEvent.VK_X) { // toggle zombie spawn point
			game.togglePlayerSpawnPoint();
		} else if (code == KeyEvent.VK_T) { // edit tile
			game.editTile();
		} else if (code == KeyEvent.VK_R) { // rotate tile
			game.rotateTile();
		} else if (code == KeyEvent.VK_G) { // edit wall
			game.editWall();
		} else if (code == KeyEvent.VK_B) { // edit object
			game.editObject();
		} else if (code == KeyEvent.VK_C) { // copy location
			game.copyLocation();
		} else if (code == KeyEvent.VK_V) { // paste location
			game.pasteLocation();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		if (game.processMouseClick(id, x, y)) {

		} else {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (!game.processCommand(id, command)) {
			frame.processAction(id, command);
		}

		// After processing an action, give control back to the frame
		frame.requestFocus();
	}

}
