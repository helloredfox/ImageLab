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

	public String toString()
	{
		String objectAsText = "";

		objectAsText += fileID + "\n" + Integer.toString(width)+ "\t" + Integer.toString(height)+ "\n" + Integer.toString(maxColorValue)+ "\n";
		//add all the pixels
		for(Pixel p : this.pixels)
		{
			objectAsText += Integer.toString(p.getRedValue()) + "\n";
			objectAsText += Integer.toString(p.getGreenValue())+ "\n";
			objectAsText += Integer.toString(p.getBlueValue())+ "\n";
		}

		return objectAsText;
	}
	/*

	q.assign(p);

public void assign(Queue other) {
  this.primitive = other.primitive;
  this.someObject = new SomeObject(other.someObject);
}
	 */

	public void assign(ppmImage image)
	{
		this.fileID = image.fileID;
		this.width = image.width;
		this.height = image.height;
		this.maxColorValue = image.maxColorValue;
		this.pixels = image.pixels;
	}
	// Data Members
	String fileID;
	int width;
	int height;
	int maxColorValue;

	//structure to hold a list of pixels - possibly an arrayList
	ArrayList<Pixel> pixels = new ArrayList<>();
}