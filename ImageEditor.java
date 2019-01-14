import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

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
           ppmImage finalImage = emboss(theImage);
            try
            {
                ppmWriter.writeOutImage(finalImage, args[1]);
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
    public static ppmImage emboss(ppmImage image)
    {
    }
}
