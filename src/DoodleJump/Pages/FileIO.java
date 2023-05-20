package DoodleJump.Pages;

////////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
////////////////////////////////////////////////////////////////////////////////
public class FileIO {
    private static  String data;
////////////////////////////////////////////////////////////////////////////////
    public static String Read(String fileName){
        try {
            Scanner input = new Scanner(new File(fileName));
            data = "";
            while (input.hasNext()) {
                data = input.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can’t find file with the given path ");
        }
        return data;
    }
////////////////////////////////////////////////////////////////////////////////
    public static void Write(String path,String fileName){
        try {
                Scanner input = new Scanner(path);
                PrintWriter output = new PrintWriter(new File(fileName));
                while (input.hasNext()) {
                    output.println(input.nextLine());
                }
                output.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Can’t find file with the given path ");
            }
    }
////////////////////////////////////////////////////////////////////////////////   
    
}
