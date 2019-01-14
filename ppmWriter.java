
import java.io.*;
import java.nio.file.Files;

public class ppmWriter {

public static void writeOutImage(ppmImage image, String filePath) throws IOException
        {

            /*

            public void whenWriteStringUsingBufferedWritter_thenCorrect()
  throws IOException {
    String str = "Hello";
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    writer.write(str);

    writer.close();
}


             */

            // get the string you want to write
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            //write the fileID
            String textToWrite = image.toString();

            writer.write(textToWrite);
            writer.close();
          //  writer.write(desiredString)

        }

}
