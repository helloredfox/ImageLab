public class ImageEditor {

    //this is where all the logic happens

//read in a file
    static public void main(String[] args)
    {
        ppmImage theImage = new ppmImage();
        theImage = ppmReader.readInImage(args[0]);

    }





}
