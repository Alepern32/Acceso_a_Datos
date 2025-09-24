
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.imageio.ImageIO;

/* .bmp */ 

public class Tarea2{
    public static void main(String[] args) { 
      try {

          BufferedImage img;
          img = ImageIO.read(new File("image.bmp"));
          RandomAccessFile raf;

          /* Calcular los pixeles de la imagen */

          raf = new RandomAccessFile("./image.bmp", "rw");
          raf.seek(240); // Sacar la anchura en pixeles de la imagen
          int width = Integer.reverseBytes(raf.readInt());
          System.out.println("La anchura es de: " + width);

          raf.seek(240); // Sacar la altura en pixeles de la imagen
          int heigth = Integer.reverseBytes(raf.readInt());

          System.out.println("La altura es de: " + heigth);
        
         /* Leer bytes en un fichero .bmp */ 

            for(int x = 0; x <= width; x++){
                for(int y = 0; y <= heigth; y++){
                    int rgb = img.getRGB(x, y);
                    Color color = new Color(rgb, true);

                    int r = color.getRed();
                    int b = color.getBlue();
                    int g = color.getGreen();

                    System.out.println(r);
                    System.out.println(b);
                    System.out.println(g);
                }
            }

        /* Escribir bytes en un fichero .bmp */

            for(int i = 0; i <= width;i++){
                for(int o = 0; o <= heigth; o ++ ){
                        int r, g, b;

                        r = 255;
                        b = 122;
                        g = 100;

                        Color color = new Color(r,g,b);
                        img.setRGB(i, o, color.getRGB());
                }
            }

         raf.close();

      } catch (IOException e) {
        System.out.println("Error " + e);
    }
    
    }

}

