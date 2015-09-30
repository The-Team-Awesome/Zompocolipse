package gameWorld.items;

import gameWorld.GameObject;
import gameWorld.characters.Player;

import java.util.Map;

/**
 * Item is an interface representing any "usable" item in the game.
 * It represents the component interface class from the composite pattern.
 *
 * @author Kieran Mckay, 300276166
 */
public interface Item extends GameObject {

	/**
	 * Calling use on this item allows it to perform the appropriate behaviour of its class
	 * @param player - the player who is using this item
	 */
	public void use(Player player);

	/**
	 * Whether this item is movable or not.
	 * @return True if this item can be moved, or False if it cannot.
	 */
	public boolean movable();

	/**
	 * Returns a CSV representation of this Item that can be saved to CSV document
	 */
	public String getCSVCode(Map<String, String> textTileMap);


	/**
	 * Get this items unique identifier number
	 * @return an integer which is this items unique identifier
	 */
	public int getUniqueID();
}