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
           ppmImage finalImage =  grayScale(theImage);
            //write out the final image to the command line argument
            try
            {
                ppmWriter.writeOutImage(finalImage, args[1]);
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
                ppmWriter.writeOutArrayImage(finalImage, args[1]);
            }
            catch(IOException e)
            {
                System.out.println("IOException my man");
            }
        }
        else if(methodToInvoke.equals("motionblur"))
        {

            //get the third commandline argument
            if(args.length == 4)
            {
                int blurAmount = Integer.parseInt(args[3]);
                ppmImage finalImage = blur(theImage, blurAmount);

                try
                {
                    ppmWriter.writeOutArrayImage(finalImage, args[1]);
                }
                catch(IOException e)
                {
                    System.out.println("IOException my man");
                }
            }
            else
            {
                //print the usage statement
                System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
            }


        }
        else
        {
           // print usage statement
            System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
        }

    }


    public static ppmImage grayScale(ppmImage image)
    {
//        for(Pixel pixel : image.pixels)
//        {
//            int averagePixelValue = (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue())/3;
//
//            pixel.setRedValue(averagePixelValue);
//            pixel.setGreenValue(averagePixelValue);
//            pixel.setBlueValue(averagePixelValue);
//
//        }

        ppmImage modifiedImage = new ppmImage();
        modifiedImage.assign(image);

        for(int i = 0; i < image.height; i ++)
        {
            for(int j = 0; j < image.width; j++)
            {
                int averagePixelValue = (image.pixels2dArray[i][j].getRedValue() + image.pixels2dArray[i][j].getGreenValue() + image.pixels2dArray[i][j].getBlueValue())/3;

                modifiedImage.pixels2dArray[i][j].setRedValue(averagePixelValue);
                modifiedImage.pixels2dArray[i][j].setGreenValue(averagePixelValue);
                modifiedImage.pixels2dArray[i][j].setBlueValue(averagePixelValue);
            }
        }
return modifiedImage;

    }
    public static void invert(ppmImage image)
    {
        for(Pixel pixel : image.pixels)
        {

            pixel.setRedValue(255-pixel.getRedValue());
            pixel.setGreenValue(255-pixel.getGreenValue());
            pixel.setBlueValue(255-pixel.getBlueValue());

        }
    }

    public static int getMaxDiff(int redDiff, int greenDiff, int blueDiff)
    {

        int maxDiff = 0;

        int absRedDiff = Math.abs(redDiff);
        int absGreenDiff = Math.abs(greenDiff);
        int absBlueDiff = Math.abs(blueDiff);

        int[] absValesArr = {absRedDiff, absGreenDiff, absBlueDiff};

       //first , find the largest number
        for(int i = 0; i < 3; i++)
        {
            if (absValesArr[i] > maxDiff)
            {
                maxDiff = absValesArr[i];
            }
        }

        //now we have the largest number

        if(maxDiff == absRedDiff)
        {
            maxDiff = redDiff;
        }
        else if(maxDiff == absGreenDiff)
        {
            //check to see if it equals redDiff, if so, use redDiff original value
            if(maxDiff == absRedDiff)
            {
                maxDiff = redDiff;
            }
            else
            {
                maxDiff = greenDiff;
            }
        }
        else if(maxDiff == absBlueDiff)
        {
            if(maxDiff == absRedDiff)
            {
                maxDiff = redDiff;
            }
            else if(maxDiff == absGreenDiff)
            {
                maxDiff = greenDiff;
            }
            else
            {
                maxDiff = blueDiff;
            }
        }
        return maxDiff;
    }

    public static ppmImage emboss(ppmImage theImage)
    {
        ppmImage modifiedImage = new ppmImage();
        modifiedImage.assign(theImage);

        int v = 0;

        //starting from the bottom right pixel(this is what I was missing)
        for(int i = (theImage.height-1); i >= 0; i--)
        {
            for(int j = (theImage.width-1); j >= 0; j--)
            {
                //calculate red, blue and green differences
                if(i-1 >= 0 && j-1 >= 0)
                {
                    int redDiff = theImage.pixels2dArray[i][j].getRedValue() - theImage.pixels2dArray[i-1][j-1].getRedValue();
                    int greenDiff = theImage.pixels2dArray[i][j].getGreenValue() - theImage.pixels2dArray[i-1][j-1].getGreenValue();
                    int blueDiff = theImage.pixels2dArray[i][j].getBlueValue() - theImage.pixels2dArray[i-1][j-1].getBlueValue();

                    int maxDiff = getMaxDiff(redDiff, greenDiff, blueDiff);

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
                else if(i-1 < 0 || j-1 < 0)
                {
                    v = 128;
                }

                modifiedImage.pixels2dArray[i][j].setRedValue(v);
                modifiedImage.pixels2dArray[i][j].setGreenValue(v);
                modifiedImage.pixels2dArray[i][j].setBlueValue(v);

            }
        }

        return modifiedImage;

    }


    public static ppmImage blur(ppmImage image, int blur)
    {

        //make a copy
        ppmImage modifiedImage = new ppmImage();

        modifiedImage.assign(image);


        //make sure to check if blur = 1 and blur = 0;  blur = 1 is a test case!!
        //special cases
        if(blur == 0)
        {
            //do nothing
        }
        else if(blur == 1)
        {

        }
        else if (blur > 1)
        {
            //iterate through the pixels in the 2d array
            for(int i = 0; i < image.height; i++)
            {
                for(int j = 0; j < image.width; j++)
                {
                    //for each pixel, do the blur
                    int numPixelsToBlur = blur;

                    int totalRedCount = 0;
                    int totalGreenCount = 0;
                    int totalBlueCount = 0;

                    if(blur > (image.width-j))
                    {
                        numPixelsToBlur = image.width-j;
                    }
                    //blur the numPixelsToBlur amount, starting with the current pixel
                    for(int k = 0; k < numPixelsToBlur; k++)
                    {
                        totalRedCount += image.pixels2dArray[i][j+k].getRedValue();
                        totalGreenCount += image.pixels2dArray[i][j+k].getGreenValue();
                        totalBlueCount += image.pixels2dArray[i][j+k].getBlueValue();
                    }
                    //find averages
                    if(numPixelsToBlur != 0)
                    {
                        int redAvg = totalRedCount/numPixelsToBlur;
                        int blueAvg = totalBlueCount/numPixelsToBlur;
                        int greenAvg = totalGreenCount/numPixelsToBlur;

                        //assign average to the current pixel
                        modifiedImage.pixels2dArray[i][j].setRedValue(redAvg);
                        modifiedImage.pixels2dArray[i][j].setGreenValue(greenAvg);
                        modifiedImage.pixels2dArray[i][j].setBlueValue(blueAvg);
                    }
                    else
                    {
                        //do nothing
                    }


                }
            }

        }

        return modifiedImage;

    }

}
