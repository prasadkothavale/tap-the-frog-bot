package com.aehe.apps.tapthefrogbot.jumpthefrog;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * @author prasad.kothavale@yahoo.co.in
 * Handles image processing operations
 *
 */
public class ImageProcessor {

	static int count = 0;
	
	private Image clock;

	private Image noTile2;

	private static ImageProcessor imageProcessor;

	private ImageProcessor() throws Exception {

		// deserialize clock
		FileInputStream fis = new FileInputStream("clock.ser");
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		clock = (Image) ois.readObject();
		ois.close();
		
		// deserialize noTile2
		FileInputStream fis1 = new FileInputStream("noTile.ser");
		BufferedInputStream bis1 = new BufferedInputStream(fis1);
		ObjectInputStream ois1 = new ObjectInputStream(bis1);
		noTile2 = (Image) ois1.readObject();
		ois1.close();
	}
	
	public static ImageProcessor getInstance() throws Exception{
		if (imageProcessor == null) {
			imageProcessor = new ImageProcessor();
		}
		return imageProcessor;
	}
	
	/**
	 * Is next clock
	 * @param image
	 * @return
	 */
	public boolean isNextClock(Image image) {
		Image nextClockLocation = new Image(17, 17);
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				nextClockLocation.image[i][j] = image.image[1163 + i][673 + j];
			}
		}
		return Arrays.deepEquals(nextClockLocation.image, clock.image);
	}
	
	/**
	 * Is 2<sup>nd</sup> next no tile
	 * @param image
	 * @return
	 */
	public boolean isNext2ndNoTile(Image image) {
		Image next2ndNoTileLocation = new Image(100, 70);
		//BufferedImage bufferedImage = new BufferedImage(100, 70, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 70; j++) {
				next2ndNoTileLocation.image[i][j] = image.image[1330 + i][725 + j];
				//bufferedImage.setRGB(i, j, next2ndNoTileLocation.image[i][j]);
			}
		}
		
		/*try {
			File outputfile = new File(String.format("analysis/%d.png", count));
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		boolean deepEquals = Arrays.deepEquals(next2ndNoTileLocation.image, noTile2.image);
		// System.out.println(count+" = "+deepEquals);
		// count++;
		return deepEquals;
	}

}
