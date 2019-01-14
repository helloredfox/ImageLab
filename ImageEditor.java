import java.io.IOException;

public class ImageEditor {

    //this is where all the logic happens

//read in a file
    static public void main(String[] args)
    {
        ppmImage theImage = new ppmImage();
        theImage = ppmReader.readInImage(args[0]);

        // based on the command line input, perform a method
        String methodToInvoke = args[2];

        if(methodToInvoke.equals("grayscale"))
        {
            //perform grayscale command
            grayScale(theImage);
            //write out the final image to the command line argument
            try
            {
                ppmWriter.writeOutImage(theImage, args[1]);
            }
            catch(IOException e)
            {
                System.out.println("IOException my man");
            }
        }
        else if(methodToInvoke.equals("invert"))
        {
            invert(theImage);

            try
            {
                ppmWriter.writeOutImage(theImage, args[1]);
            }
            catch(IOException e)
            {
                System.out.println("IOException my man");
            }
        }
        else if (methodToInvoke.equals("emboss"))
        {
            emboss(theImage);
            try
            {
                ppmWriter.writeOutImage(theImage, args[1]);
            }
            catch(IOException e)
            {
                System.out.println("IOException my man");
            }
        }

    }


    public static void grayScale(ppmImage image)
    {
        for(Pixel pixel : image.pixels)
        {
            int averagePixelValue = (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue())/3;

            pixel.setRedValue(averagePixelValue);
            pixel.setGreenValue(averagePixelValue);
            pixel.setBlueValue(averagePixelValue);

        }

    }
    public static void invert(ppmImage image)
    {
        for(Pixel pixel : image.pixels)
        {

            pixel.setRedValue(255-pixel.getGreenValue());
            pixel.setGreenValue(255-pixel.getGreenValue());
            pixel.setBlueValue(255-pixel.getBlueValue());

        }
    }
    public static void emboss(ppmImage image)
    {
        // we have an Image set up with height = rows, and width  = columns. Iterate through like that

        /*
        h/r = 3 w/c = 3

        [p p p] [p p p] [p p p]




         */
        // loop through the new "image" table
        int numRows = image.height * image.width;
        int numColumns = image.width;
        int rowWidth = image.width;

        for(int i = 0; i < numRows; i+= rowWidth)
        {
            for(int j = 0; j < numColumns; j++)
            {

                int v = 0;

                //check i-1 and j-1 to see if either == 0
                if(i-rowWidth < 0 || j-1 < 0)
                {
                   //set the pixels RGB values to 128
                    v = 128;
                }
                else
                {
                    int redDiff = image.pixels.get(i + j).getRedValue() - image.pixels.get((i - rowWidth) + (j - 1)).getRedValue();
                    int greenDiff = image.pixels.get(i + j).getGreenValue() - image.pixels.get((i - rowWidth) + (j - 1)).getGreenValue();
                    int blueDiff = image.pixels.get(i + j).getBlueValue() - image.pixels.get((i - rowWidth) + (j - 1)).getBlueValue();

                    //find the max difference
                    int intermediateDiff = 0;
                    int maxDiff = 0;
                    if(redDiff > greenDiff)
                    {
                        intermediateDiff = redDiff;
                    }
                    else
                    {
                        intermediateDiff = greenDiff;
                    }

                    if(intermediateDiff > blueDiff)
                    {
                        maxDiff = intermediateDiff;
                    }
                    else
                    {
                        maxDiff = blueDiff;
                    }

                    v = 128 + maxDiff;

                    if(v < 0)
                    {
                        v = 0;
                    }
                    if(v > 255)
                    {
                        v = 255;
                    }


                }

                //set pixel's RGB values = v
                image.pixels.get(i+j).setRedValue(v);
                image.pixels.get(i+j).setGreenValue(v);
                image.pixels.get(i+j).setBlueValue(v);

            }
        }
    }
}
