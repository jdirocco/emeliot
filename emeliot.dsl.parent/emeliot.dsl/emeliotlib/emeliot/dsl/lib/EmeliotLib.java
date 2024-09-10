package emeliot.dsl.lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmeliotLib {
	//Interface 
	
    public static String read(String fileName) {
        String fileContente = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContente += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContente;
    }

}