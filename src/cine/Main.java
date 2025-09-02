package cine;

import cine.helpers.*;
import cine.objects.ArchiveUtil;
import cine.process.processMain;
import cine.validateItem.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String text="";
        String archiveName = "InformeVentasCine";
        ArchiveUtil storage=null;
        int[][][] cinema;
        int[][] revenue;
        int[] losses;
        String[] movieTimes;
        int showTimesQuant, seatsQuant;
        int moviesQuant = 7;

        String router=Paths.get("").toRealPath().toString()+"src/cine/storage";

        try {
            storage= new ArchiveUtil(router);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println("-ERROR-STORAGE-: ["+e.getMessage()+"]");
        }

        if (storage.directoriesExist()) {
            text="¿Desea realizar una busqueda o realizar una compra? \nBusqueda \nCompra";
            System.out.println(text);

            text=(Objects.equals(ValidateOBJ.valOption(text,scanner.nextLine(),scanner),"busqueda"))
            ?"¿Por cual metodo desea buscar? \n-Nombre \n-Cedula \n-Fecha" :null;
        }

        if (text!=null) {
            System.out.println(text);
            String option=ValidateOBJ.valOption(text, scanner.nextLine(),scanner);
            consultMain.consultDataMain(option.toLowerCase(), storage);
        }
        
        // Pedimos los datos para la inicializacion
        text = "- Ingrese cuantos HORARIOS estaran disponibles por pelicula: ";
        showTimesQuant = Validate.valInt(text, 6, 4);

        text = "- Ingrese cuantos asientos tiene cada sala: ";
        seatsQuant =  Validate.valInt(text, 20, 10);


        // Instanciamos los arreglos
        cinema = new int[moviesQuant][showTimesQuant][seatsQuant];
        revenue = new int[moviesQuant][(showTimesQuant+1)];
        losses = new int[moviesQuant];
        movieTimes = new String[showTimesQuant];

        // Inicializamos los arreglos
        processMain.processIni(revenue, cinema, losses, movieTimes);
        

        // Desarrollo
        processMain.proccessData(movieTimes, cinema, revenue, losses, router);
        

        // Eliminamos las instancias
        cinema = null;
        revenue = null;
        movieTimes = null;
        scanner.close();
    }
}
