package cine.helpers;

import cine.validateItem.*;
import java.util.Objects;
import java.util.Scanner;

public class consultData {
    

    public static String optionSelect(String optionSelect){
        String text="";
        Scanner enter=new Scanner(System.in);

        if (Objects.equals(optionSelect,"nombre")) {
            text="Ingrese el NOMBRE del cliente a buscar: ";
            System.out.println(text);
            return Validate.valString(optionSelect,text); 
        }
        return null;
    }
}
