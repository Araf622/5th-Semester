import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Testing {
    public static void main(String[] args) throws IOException {
        File newFile = new File("ratios.txt");
        BufferedReader br = new BufferedReader(new FileReader(newFile));
        String st=null;
        double[][][] probabilityArray = new double[256][256][256];
        for (int r = 0; r < 256; r++) {
            for (int g = 0; g < 256; g++) {
                for (int b = 0; b < 256; b++) {
                    probabilityArray[r][g][b] = Double.parseDouble(br.readLine());
                }
            }
        }

        BufferedImage testImg = ImageIO.read(new File("test.jpg"));

        int white = new Color(255,255,255).getRGB();
        for (int y = 0; y < testImg.getHeight(); y++) {
            for (int x = 0; x < testImg.getWidth(); x++) {
                int pixel = testImg.getRGB(x, y);
                Color color = new Color(pixel, true);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                if(probabilityArray[red][green][blue]<0.4){
                    testImg.setRGB(x,y,white);
                }
            }
        }

        File output = new File("output.bmp");
        ImageIO.write(testImg, "bmp", output);

    }
}
