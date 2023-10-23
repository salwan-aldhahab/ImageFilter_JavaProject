// Salwan Aldhahab

/**
 * The class is meant to illustrate a couple of image-processing algorithms:
 * three R-G-B channel extraction methods
 */
public class ImageFilter{
	/**
	 * private constructor, by design
	 */
	private ImageFilter() {
	}
	
	/**
	 * This private method can be used inside the ImageFilter class 
	 * to manipulate pixel colors.
	 * @param color can only take a string of either "red", "green", "blue".
	 * @param imagePixel takes pixel data to be manipulated.
	 * @return imagePixel after pixel color manipulation.
	 */
	private static int setPixelColor(String color, int imagePixel) {
		
		int alphaImagePixel = 0;
		int redImagePixel = 0;
		int greenImagePixel = 0;
		int blueImagePixel = 0;
		
		if (color == "red") {
			alphaImagePixel = imagePixel & 0xff000000;
			redImagePixel = imagePixel & 0x00ff0000;
			greenImagePixel = (redImagePixel >> 8) & 0x0000ff00;
			blueImagePixel = (redImagePixel >> 16) & 0x000000ff;
		} else if (color == "green"){
			alphaImagePixel = imagePixel & 0xff000000;
			greenImagePixel = imagePixel & 0x0000ff00;
			redImagePixel = (greenImagePixel << 8) & 0x00ff0000;
			blueImagePixel = (greenImagePixel >> 8) & 0x000000ff;
		} else if (color == "blue"){
			alphaImagePixel = imagePixel & 0xff000000;
			blueImagePixel = imagePixel & 0x000000ff;
			redImagePixel = (blueImagePixel << 16) & 0x00ff0000;
			greenImagePixel = (blueImagePixel << 8) & 0x0000ff00;
		}
		
		imagePixel = alphaImagePixel | redImagePixel | greenImagePixel | blueImagePixel;
		return imagePixel;
	}

	/**
	 * This public method extract red color from image.
	 * @param imageData RGB pixels data from image.
	 * @param width: the width of the image. 
	 * @return imageData monochrome with red color extracted
	 */
	public static int [] getRed(int[] imageData, int width) {
		
		for (int i=0; i < imageData.length; i++) {
			imageData[i]= setPixelColor("red", imageData[i]);
		}
		return imageData;
	}

	
	/**
	 * This public method extract green color from image.
	 * @param imageData RGB pixels data from image.
	 * @param width: the width of the image. 
	 * @return imageData monochrome with green color extracted
	 */
	public static int [] getGreen(int[] imageData, int width) {
		
		for (int i=0; i < imageData.length; i++) {
			imageData[i]= setPixelColor("green", imageData[i]);
		}
		return imageData;
	}

	
	/**
	 * This public method extract blue color from image.
	 * @param imageData RGB pixels data from image.
	 * @param width: the width of the image. 
	 * @return imageData monochrome with blue color extracted
	 */
	public static int [] getBlue(int[] imageData, int width) {
		
		for (int i=0; i < imageData.length; i++) {
			imageData[i]= setPixelColor("blue", imageData[i]);
		}
		return imageData;
	}

}