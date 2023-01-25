import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[])throws Exception {
        FileWriter ratios = new FileWriter("ratios.txt");
        //Reading the image
        File path = new File("ibtd");
        File maskPath = new File("Mask");
        List imageCollection = new ArrayList();
        List maskImageCollection = new ArrayList();
        File [] file= path.listFiles();
        File [] maskFile = maskPath.listFiles();
        Arrays.sort(file);
        Arrays.sort(maskFile                                               );
        int [][][] skinArray = new int[256][256][256];
        int [][][] nonSkinArray = new int[256][256][256];
        double[][][] skinRatio = new double[256][256][256];
        double[][][] nonSkinRatio = new double[256][256][256];
        double[][][] probabilityArray = new double[256][256][256];
        int skin = 0;
        int nonSkin = 0;
        for(int i = 0; i < file.length;i++) {
            BufferedImage img = ImageIO.read(file[i]);
            BufferedImage maskImg = ImageIO.read(maskFile[i]);
            System.out.println(i);

            //mask image
            for (int y = 0; y < maskImg.getHeight(); y++) {
                for (int x = 0; x < maskImg.getWidth(); x++) {

                    //Retrieving contents of a pixel
                    int maskPixel = maskImg.getRGB(x, y);
                    int pixel = img.getRGB(x, y);
                    //Creating a Color object from pixel value
                    Color maskColor = new Color(maskPixel, true);
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    int maskRed = maskColor.getRed();
                    int maskGreen = maskColor.getGreen();
                    int maskBlue = maskColor.getBlue();

                    if (maskRed > 225 && maskGreen > 225 && maskBlue > 225) {
                        nonSkinArray[red][green][blue]++;
                        //System.out.println(nonSkinArray[red][green][blue]);
                        nonSkin++;
                    } else {
                        skinArray[red][green][blue]++;
                        //System.out.println(skinArray[red][green][blue]);
                        skin++;
                    }

                }
            }
        }

        System.out.println(skin + " " + nonSkin);

        for(int r = 0; r < 256; r++){
            for(int g=0; g<256; g++){
                for(int b=0; b<256;b++){
                    //skinRatio[r][g][b] = skinArray[r][g][b]/skin;
                    //nonSkinRatio[r][g][b] = nonSkinArray[r][g][b]/nonSkin;
                    double temp;
                    if(nonSkinArray[r][g][b]==0){
                        probabilityArray[r][g][b] = 1;
                    }
                    else {
                        probabilityArray[r][g][b] = skinArray[r][g][b]/ nonSkinArray[r][g][b];
                    }

                    ratios.write(String.valueOf(probabilityArray[r][g][b]+"\n"));
                    //ratios.write(temp.toString());
                    //System.out.println(skinArray[r][g][b] + " " + nonSkinArray[r][g][b]);
                }
            }
        }
        ratios.close();

        System.out.println("Training Done!!!");


    }
}