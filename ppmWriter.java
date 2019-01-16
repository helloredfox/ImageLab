
import java.io.*;
import java.nio.file.Files;

public class ppmWriter {

public static void writeOutImage(ppmImage image, String filePath) throws IOException
        {

            // get the string you want to write
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));


            String textToWrite = image.toString();

            writer.write(textToWrite);
            writer.close();
          //  writer.write(desiredString)

        }


        public static void writeOutArrayImage(ppmImage image, String filePath) throws IOException
        {
            // get the string you want to write
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));


            String textToWrite = image.pixelArrayToString();

            writer.write(textToWrite);
            writer.close();
            //  writer.write(desiredString)
        }

}
