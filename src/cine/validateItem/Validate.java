package cine.validateItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class Validate {
    public static int valUnsoldSeats1(int[][][] cinemaMatrix, int movieOption, int showTimeOption){
        int count=0;
        if (cinemaMatrix != null) {
            for (int i = 0; i < cinemaMatrix[0][0].length; i++) {
                if (cinemaMatrix[movieOption - 1][showTimeOption - 1][i] == 0) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public static int valUnsoldSeats2(int[][][] cinemaMatrix, int x, int j){
        int count=0;
        if (cinemaMatrix != null) {
            for (int z = 0; z < cinemaMatrix[0][0].length; z++) {
                if (cinemaMatrix[x][j][z] == 0) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public static String valSchedule(String text) throws IOException {
        String schedule;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
        LocalTime minHour = LocalTime.parse("12:00 PM", format);
        LocalTime maxHour = LocalTime.parse("10:30 PM", format);

        while (true){
            try {
                System.out.println(text);
                schedule = scanner.nextLine().toUpperCase(Locale.ROOT).trim();

                LocalTime inputtedHour = LocalTime.parse(schedule, format);
                if (!inputtedHour.isBefore(minHour) && !inputtedHour.isAfter(maxHour) && schedule.endsWith("PM")) {
                    return schedule.replace(" ", "");
                }else {
                    System.out.println("  - ERROR: [Hora inválida, fuera del rango permitido (12:00 PM - 10:30 PM)]");
                }

            } catch (DateTimeParseException e) {
                addError("Formato de horas inválido "+e+"/"+e.getMessage()+"/"+e.getLocalizedMessage());
                System.out.print("  - ERROR: [Formato invalido, utilice el formato hh:mm PM (ej. 04:30 PM) / Verifique que la hora sea real]");
                scanner.nextLine();
            }

        }
    }

    public static int valInt(String text, int maxValue, int minValue) throws IOException {
        int size;
        Scanner scanner = new Scanner(System.in);

        while (true){
            try {
                System.out.println(text);
                size = scanner.nextInt();

                if (size < minValue || size > maxValue){
                    System.out.println(" - ERROR: [No se permiten valores MENORES a "+ minValue + " ni MAYORES a " + maxValue + "]");
                } else {
                    return size;
                }
            } catch (InputMismatchException e) {
                addError("No se pueden usar caracteres "+e+"/"+e.getMessage()+"/"+e.getLocalizedMessage());
                System.out.println("- Error: [No se pueden usar caracteres, palabras o letras]");
                scanner.nextLine();
            }
        }
    }

    public static boolean valString(String text){
        String limit="0123456789!#$%/()=?¡¨*[]_";
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < limit.length(); j++) {
                if (text.charAt(i)==limit.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
    

    public static String valString(String option, String text){

        if (!valString(option)) {
            System.out.println("ERROR - [No se permiten caracteres especiales o numeros]");
            System.out.println(text);
            return valString(option, text);
        }
        return option;
    }


    public static int valInt(String text, int maxValue) throws IOException {
        int size;
        Scanner scanner = new Scanner(System.in);

        while (true){
            try {
                System.out.println(text);
                size = scanner.nextInt();

                if (size < 1 || size > maxValue){
                    System.out.println(" - ERROR: [No se permiten valores negativos ni valores MAYORES a " + maxValue + "]");
                } else {
                    return size;
                }
            } catch (InputMismatchException e) {
                addError("No se pueden usar caracteres "+e+"/"+e.getMessage()+"/"+e.getLocalizedMessage());
                System.out.println("- Error: [No se pueden usar caracteres, palabras o letras]");
                scanner.nextLine();
            }
        }
    }


    public static void addError(String error) throws IOException {
        String router = Paths.get("").toRealPath()+"/src/cine";
        router = utilDirectory(router)+"/ErrorSystem.log";
        useArchive(nameArchiveGenerate("ErrorSystemValues")+" ["+error+"]", router, true);
    }

    public static String nameArchiveGenerate(String name) {
        LocalDateTime actualDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        int rand = (int) (Math.random() * 100) + 1;
        return name+"_"+actualDateTime.format(formatter)+"_"+"Serial"+rand;
    }

}