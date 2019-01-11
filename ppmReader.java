import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ppmReader {

    // This class reads in a ppm file, creates a ImageLab.ppmImage object from it, and returns that object

    public static ppmImage readInImage(String fileName) {
        File imageFile = new File(fileName);
        ppmImage theImage = new ppmImage();
        try
        {


            Scanner scanner = new Scanner(imageFile);
            // all the code to read in the file goes here

            // this delimits by whitespace AND comments. Comments taken care of. Phew.
            scanner.useDelimiter("((#[^\\n]*\\n)|(\\s+))+");

            //read the filetype
            String fileID = scanner.next();

            //read the width
            int width = scanner.nextInt();

            //read height
            int height = scanner.nextInt();

            //read max color value
            int maxColorValue = scanner.nextInt();

            //adds all the values of the parameters as datamembers on the object it is called on
            theImage.setUpImage(fileID, width, height, maxColorValue);

            //read all the pixels
            while(scanner.hasNext())
            {
                //get and create pixels

                //create a pixel object
                Pixel pixel = new Pixel();
                String str = scanner.next();
                int redValue = Integer.parseInt(str);
                pixel.setRedValue(redValue);

                if(scanner.hasNext())
                {
                    int greenValue = Integer.parseInt(scanner.next());
                    pixel.setGreenValue(greenValue);
                }
                if(scanner.hasNext())
                {
                    int blueValue = Integer.parseInt(scanner.next());
                    pixel.setBlueValue(blueValue);
                }

                //add the pixel to the ppmImage object
                theImage.addPixel(pixel);
            }
            // All full pixels(all 3 color values) have been added
        }
    catch(FileNotFoundException error)
        {
            error.printStackTrace();
        }

        return theImage;
    }
}
