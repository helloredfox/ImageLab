import java.util.ArrayList;

public class ppmImage {

	//constructor
	ppmImage() {}

	public void setUpImage(String fileID, int width, int height, int maxColorValue) {
		this.fileID = fileID;
		this.width = width;
		this.height = height;
		this.maxColorValue = maxColorValue;
		this.pixels2dArray = new Pixel[this.height][this.width];
	}

	// Methods
	public void addPixel (Pixel pixel)
	{
		this.pixels.add(pixel);
	}

	public String toString()
	{
		StringBuilder objectAsText = new StringBuilder();


		objectAsText.append(fileID + "\n" + Integer.toString(width)+ "\t" + Integer.toString(height)+ "\n" + Integer.toString(maxColorValue)+ "\n");
		//add all the pixels
		for(Pixel p : this.pixels)
		{
			objectAsText.append(Integer.toString(p.getRedValue()) + "\n");
			objectAsText.append(Integer.toString(p.getGreenValue())+ "\n");
			objectAsText.append(Integer.toString(p.getBlueValue())+ "\n");
		}

		return objectAsText.toString();
	}

	public String pixelArrayToString()
	{

		StringBuilder objectAsText = new StringBuilder();

		objectAsText.append(fileID + "\n" + Integer.toString(width)+ "\t" + Integer.toString(height)+ "\n" + Integer.toString(maxColorValue)+ "\n");

		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				objectAsText.append(Integer.toString(this.pixels2dArray[i][j].getRedValue()) + "\n");
				objectAsText.append(Integer.toString(this.pixels2dArray[i][j].getGreenValue())+ "\n");
				objectAsText.append(Integer.toString(this.pixels2dArray[i][j].getBlueValue())+ "\n");
			}
		}

		return objectAsText.toString();
	}

	public void set2dArray(Pixel [][] pixels, int height, int width)
	{
		if(this.height == height && this.width == width)
		{
			//intitialize the 2dArray
			this.pixels2dArray = new Pixel[height][width];

			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					this.pixels2dArray[i][j] = pixels[i][j];
				}
			}
		}

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
		this.set2dArray(image.pixels2dArray, image.height, image.width);
	}
	// Data Members
	String fileID;
	int width;
	int height;
	int maxColorValue;
	Pixel [][] pixels2dArray;

	//structure to hold a list of pixels - possibly an arrayList
	ArrayList<Pixel> pixels = new ArrayList<>();
}