package com.aehe.apps.tapthefrogbot.jumpthefrog;

public class Main {
	
	public static int[] jumpOneStepLocation = {260, 920};
	public static int[] jumpTwoeStepLocation = {1650, 920};

	public static void main(String[] args) throws Exception {
		System.out.println("Initializing...");
		Adb adb = Adb.getInstance();
		ImageProcessor imageProcessor = ImageProcessor.getInstance();
		
		// Start playing
		System.out.println("start");
		while(true) {
			System.out.println("------------------------");
			long start = System.currentTimeMillis();
			Image image = adb.refreshScreen();
			System.out.println("Image refresh after: "+(System.currentTimeMillis() - start));
			if (imageProcessor.isNext2ndNoTile(image) || imageProcessor.isNextClock(image)) {
				System.out.println("decided to jump 1 after: "+(System.currentTimeMillis() - start));
				adb.tap(jumpOneStepLocation[0], jumpOneStepLocation[1]);
			} else {
				System.out.println("decided to jump 2 after: "+(System.currentTimeMillis() - start));
				adb.tap(jumpTwoeStepLocation[0], jumpTwoeStepLocation[1]);
			}
			System.out.println("Tapped button after: "+(System.currentTimeMillis() - start));
			// Thread.sleep(200);
		}
		
	}

}
