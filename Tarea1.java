
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/* RandomAccessFile | DataInputStream, DataOutputStream */ /* ALEJANDRO MEDINA LAFUENTE */

public class Tarea1{
    public static void main(String[] args) {
       ArrayList<Integer> arr = new ArrayList<>(20);
       File f = new File("datos.bin");

       /* Inicializamos los objetos */
       DataInputStream dis;
       DataOutputStream dos;
       Scanner sc;
       RandomAccessFile raf;

       try {
           if (f.exists()) {
                dis = new DataInputStream(new FileInputStream("datos.bin")); 
                for(int i = 0; i < 20; i++){ 
                    arr.add(dis.readInt()); // Añadimos a nuestra ArrayList los valores que se van a leer de fichero datos.bin
                }
                dis.close();
                System.out.println(arr); // Y mostramos su valor actual
           }else{
                dos = new DataOutputStream(new FileOutputStream("datos.bin"));
                for(int i = 0; i < 20; i++){ 
                    dos.writeInt(0); // Escribimos en nuestro fichero datos.bin en binario 20 ceros
                }
                dos.close();
           }
           
           sc = new Scanner(System.in);
           raf = new RandomAccessFile(f, "rwd");

           String posicion = "";
           while(!posicion.equals("-1"))
                {
                System.out.print("Añada una posicion para modificar o salir del programa -1: " );
                posicion = sc.nextLine(); // ponemos una variable en la cual guardara la posicion que le añadamos y asi acceder mediante AccessRandomFile a la posicion * los 4 BYTES
                System.out.print("Ahora añade el numero que quiere añadir: ");
                int dato = Integer.parseInt(sc.nextLine()); // ponemos una variable en la que almacene el numero que vamos a añadir a nuestro fichero
                
                if (posicion.equals("-1")) 
                break;

                arr.set(Integer.parseInt(posicion), dato); //Actualizar los valores de arr 
                        
                raf.seek(Integer.parseInt(posicion) * 4); // Convertimos nuestro posicion por int y le multiplicamos a la posicion * 4 BYTES para avanzar un bit |1|posicionPuntero = 2|3|4|5 
                raf.writeInt(dato); // Escribir los datos pasados por el Scanner solo int
                
                int position = 0; 
                raf.seek(0); // Poner el puntero en la posicion 0 para que lea desde el principio del fichero
                long longitudRaf = raf.length(); // Coger la longitud de raf
                while(position < longitudRaf){ // convertir nuestro Str a int para usarlo en el while
                    int datos = raf.readInt(); 
                    System.out.println("Posición:valor " + (position / 4) + "-" + datos);
                    position += 4; // Aumentamos un bit para que posicion pase 1 en el fichero => |1|pos=2|pos=3|4|5
            
                }
            }

       } catch (IOException | NumberFormatException e) {
            System.err.println("Error " + e);
             
       }

}

  
}