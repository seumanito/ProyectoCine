package cine;

import cine.objects.ArchiveUtil;
import cine.process.processMain;
import cine.validateItem.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String text="";
        String archiveName = "InformeVentasCine";
        ArchiveUtil archive=null;
        int[][][] cinema;
        int[][] revenue;
        int[] losses;
        String[] movieTimes;
        int showTimesQuant, seatsQuant;
        int moviesQuant = 7;
        
        /*Se valida si el archivo de busqueda existe para iniciar el metodo de busqueda
        if (Validate.useArchive(text, text, bool)) {
            
        }*/

        //Detectamos automaticamente donde se encuentra la ruta donde queremos crear el archivo
        String router = Paths.get("").toRealPath()+"/src/cine";
        router  = Validate.utilDirectory(router)+"/"+Validate.nameArchiveGenerate(archiveName)+".txt";

        String routerArchive= Paths.get("").toRealPath()+"/src/cine/objects/";
        routerArchive  = Validate.utilDirectory(routerArchive);

        try {
            archive = new ArchiveUtil(routerArchive);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println("-ERROR-INSTANCIA-: ["+e.getMessage()+"]");
        }



        if (archive.directoriesExist()) {
            text="¿Desea realizar una busqueda o realizar una compra? \nBusqueda \nCompra";
            System.out.println(text);

            text=(Objects.equals(ValidateOBJ.valOption(text,scanner.nextLine(),scanner),"busqueda"))
            ?"¿Por cual metodo desea buscar?" :null;
        }

        if (text!=null) {
            System.out.println(text);
            String option=ValidateOBJ.valOption(text, scanner.nextLine(),scanner);
            
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
