import java.util.ArrayList;

public class ppmImage {

	//constructor
	ppmImage() {}

	public void setUpImage(String fileID, int width, int height, int maxColorValue) {
		this.fileID = fileID;
		this.width = width;
		this.height = height;
		this.maxColorValue = maxColorValue;
	}

	// Methods
	public void addPixel (Pixel pixel)
	{
		this.pixels.add(pixel);
	}

	// Data Members
	String fileID;
	int width;
	int height;
	int maxColorValue;

	//structure to hold a list of pixels - possibly an arrayList
	ArrayList<Pixel> pixels = new ArrayList<>();
}