package com.aehe.apps.tapthefrogbot.jumpthefrog;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * @author prasad.kothavale@yahoo.co.in
 * The image holder
 *
 */
public class Image  implements Serializable{
	
	private static final long serialVersionUID = 2365052231869909483L;
	public int[][] image;
	private static int count = 0;
	
	
	public Image(BufferedImage img) {
		image = new int[img.getWidth()][img.getHeight()];
		for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {
	            image[x][y] = new Color(img.getRGB(x, y)).getRGB();
	        }
	    }
		
		// Uncomment to save image
		/*try {
			File outputfile = new File(String.format("analysis/%d-img.png", count++));
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public Image (int x, int y) {
		image = new int[x][y];
	}

}
