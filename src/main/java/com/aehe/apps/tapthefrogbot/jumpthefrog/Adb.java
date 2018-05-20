package com.aehe.apps.tapthefrogbot.jumpthefrog;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/**
 * @author prasadkothavale@yahoo.co.in
 * 
 *         The Adb helper
 *
 */
public class Adb {

	private static Adb adb;
	private Runtime runtime;

	private Adb() {
		runtime = Runtime.getRuntime();
	}

	/**
	 * Get singleton instance of {@link Adb}
	 * 
	 * @return
	 */
	public static Adb getInstance() {
		if (adb == null) {
			adb = new Adb();
		}

		return adb;
	}

	
	/**
	 * Get current screen as {@link Image}
	 * 
	 * @return
	 * @throws IOException
	 */
	public Image refreshScreen() throws IOException {
		final Process process = runtime.exec("adb exec-out screencap -p");
		InputStream input = process.getInputStream();
		BufferedImage image = ImageIO.read(input);
		return new Image(image);
	}
	
	/**
	 * Perform tap at x, y
	 * @param x
	 * @param y
	 * @throws IOException
	 */
	public void tap(int x, int y) throws IOException {
		final Process process = runtime.exec(String.format("adb shell input tap %d %d", x, y));
		InputStream input = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while(reader.readLine()!=null) {}
		
	}
	

}
