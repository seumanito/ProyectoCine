package cine;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String text;
        String archiveName = "InformeVentasCine";
        int[][][] cinema;
        int[][] revenue;
        int[] losses;
        String[] movieTimes;
        int showTimesQuant, seatsQuant;
        int moviesQuant = 7;


        //Detectamos automaticamente donde se encuentra la ruta donde queremos crear el archivo
        String router = Paths.get("").toRealPath()+"/src/cine";
        router  = Validate.utilDirectory(router)+"/"+Validate.nameArchiveGenerate(archiveName)+".txt";


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
        Process.iniMatrix(cinema);
        Process.iniArray(revenue);
        Process.iniArray(losses);
        Process.iniArray(movieTimes);

        // Desarrollo
        Process.dataEntry(movieTimes);
        Process.purchaseProcessStart(cinema, movieTimes);
        Process.calcRevenueLosses(cinema, revenue, losses);
        Process.showCase(cinema, revenue, losses, router);

        // Eliminamos las instancias
        cinema = null;
        revenue = null;
        movieTimes = null;
        scanner.close();
    }
}
