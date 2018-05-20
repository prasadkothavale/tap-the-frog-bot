package com.aehe.apps.tapthefrogbot.jumpthefrog;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TrainBot {

	@Test
	public void testRefreshScreen() throws Exception {
		Adb adb = Adb.getInstance();
		adb.refreshScreen();
	}

	/**
	 * Save clock image. Clock is considered to be at next location which is at 1163, 673
	 * and clock image is 17x17 pixels (only center of clock is matched)
	 * 
	 * @throws Exception
	 */
	@Test
	public void generateClock() throws Exception {
		Adb adb = Adb.getInstance();
		Image image = adb.refreshScreen();

		Image clock = new Image(17, 17);
		BufferedImage bufferedImage = new BufferedImage(17, 17, BufferedImage.TYPE_INT_RGB);
        
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				clock.image[i][j] = image.image[1163 + i][673 + j];
				bufferedImage.setRGB(i, j, clock.image[i][j]);
			}
		}

		FileOutputStream fout = new FileOutputStream("clock.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(clock);
		oos.close();
		
		try {
			File outputfile = new File("clock.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isClockNext();
	}

	/**
	 * Verify clock is at next location
	 * 
	 * @throws Exception
	 */
	@Test
	public void isClockNext() throws Exception {
		Adb adb = Adb.getInstance();
		Image image = adb.refreshScreen();

		Image nextClockLocation = new Image(17, 17);
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				nextClockLocation.image[i][j] = image.image[1163 + i][673 + j];
			}
		}

		// deserialize clock
		FileInputStream fis = new FileInputStream("clock.ser");
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Image clock = (Image) ois.readObject();
		ois.close();

		assertTrue(Arrays.deepEquals(nextClockLocation.image, clock.image));
	}
	
	/**
	 * <b> Clock 2 check is not required </b>
	 * Save clock2 image. Clock2 is considered to be at 2<sup>nd</sup> next location which is at 1370, 673
	 * and clock2 image is 17x17 pixels (only center of clock is matched)
	 * 
	 * @throws Exception
	 */
	@Test
	public void generateClock2() throws Exception {
		Adb adb = Adb.getInstance();
		Image image = adb.refreshScreen();

		Image clock = new Image(17, 17);
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				clock.image[i][j] = image.image[1370 + i][673 + j];
			}
		}

		FileOutputStream fout = new FileOutputStream("clock2.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(clock);
		oos.close();
	}

	/**
	 * <b> Clock 2 is not required </b>
	 * Verify clock2 is at 2<sup>nd</sup> next location
	 * 
	 * @throws Exception
	 */
	@Test
	public void isClock2ndNext() throws Exception {
		Adb adb = Adb.getInstance();
		Image image = adb.refreshScreen();

		Image nextClockLocation = new Image(17, 17);
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				nextClockLocation.image[i][j] = image.image[1370 + i][673 + j];
			}
		}

		// deserialize clock
		FileInputStream fis = new FileInputStream("clock2.ser");
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Image clock = (Image) ois.readObject();
		ois.close();

		assertTrue(Arrays.deepEquals(nextClockLocation.image, clock.image));
	}

	/**
	 * Save no tile image. No tile is considered to be at 2<sup>nd</sup> next location which 
	 * is at 1330, 725 and it is 100x70 pixels
	 * 
	 * @throws Exception
	 */
	@Test
	public void generateNoTile() throws Exception {
		Adb adb = Adb.getInstance();
		Image image = adb.refreshScreen();
		BufferedImage bufferedImage = new BufferedImage(100, 70, BufferedImage.TYPE_INT_RGB);

		Image noTile = new Image(100, 70);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 70; j++) {
				noTile.image[i][j] = image.image[1330 + i][725 + j];
				bufferedImage.setRGB(i, j, noTile.image[i][j]);
			}
		}

		FileOutputStream fout = new FileOutputStream("noTile.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(noTile);
		oos.close();
		
		try {
			File outputfile = new File("noTile.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isNoTile2ndNext();
	}

	/**
	 * Verify no tile is at 2<sup>nd</sup> next location
	 * 
	 * @throws Exception
	 */
	@Test
	public void isNoTile2ndNext() throws Exception {
		Adb adb = Adb.getInstance();
		Image image = adb.refreshScreen();

		Image next2ndNoTileLocation = new Image(100, 70);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 70; j++) {
				next2ndNoTileLocation.image[i][j] = image.image[1330 + i][725 + j];
			}
		}

		// deserialize clock
		FileInputStream fis = new FileInputStream("noTile.ser");
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Image noTile = (Image) ois.readObject();
		ois.close();

		assertTrue(Arrays.deepEquals(next2ndNoTileLocation.image, noTile.image));
	}

}
