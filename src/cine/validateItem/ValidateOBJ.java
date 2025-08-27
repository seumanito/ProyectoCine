package cine.validateItem;

import java.io.IOException;
import java.util.Scanner;



public class ValidateOBJ {
    public static String valOption(String text, String option, Scanner scanner) throws IOException {
        
        if (!Validate.valString(option)) {
            System.out.println("ERROR - [No se permiten caracteres especiales o numeros]");
            System.out.println(text);
            return valOption(text, option,scanner);
        }else{
            return option;
        }

    }
}
