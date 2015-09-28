package gameWorld.world;

import gameWorld.Drawable;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;

import userInterface.renderWindow.ImageUtils;

/**
 * The Floor class is a tile on the board, which is responsible for drawing
 * the ground and the items that are situated on this part of the Floor.
 *
 * Drawables that the floor contains can be:
 *
 *
 * @author Kieran Mckay, 300276166 & Pauline
 *
 */
public class Floor extends Tile{

	private int x;
	private int y;

	//Floor tiles can contain
	private boolean occupiable;

	protected transient Image[] images;
	protected transient Image currentImage;
	protected String imageName;

	public Floor(int x, int y, String[] filenames) {
		this.x = x;
		this.y = y;

		occupiable = true;

		ImageUtils imu = ImageUtils.getImageUtilsObject();

		this.images = imu.setupImages(filenames);
		this.currentImage = images[0];
		this.imageName = filenames[0];
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public boolean occupiable() {
		return occupiable;
	}

	@Override
	public String getCSVCode(Map<String, String> textTileMap) {
		String result = "0-";
		System.out.println(imageName); //TODO delete
		String[] tileCode = imageName.substring(0, imageName.length() - 4).split("_");
		for (x = 0; x < tileCode.length; x++) {
			result = result + textTileMap.get(tileCode[x]);
			if (x < tileCode.length - 1)
				result = result + "-";
		}
		return result;
	}

	@Override
	public void setOccupiable(boolean bool) {
		occupiable = bool;
	}

	@Override
	public void draw(int x, int y, Graphics g) {
		//System.out.println("drawing floor");
		g.drawImage(currentImage, x, y, null);
	}

	@Override
	public String getFileName() {
		return imageName;
	}
}
