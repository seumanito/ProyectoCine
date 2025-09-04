package cine.composablesData;

import cine.objects.*;
import cine.validateItem.Validate;
import java.time.LocalDateTime;

public class storeArchive {

    static int rand=(int)(Math.random()*100 + 1);
    

    public static void storeName(String[]names, ArchiveUtil storage,boolean check){
        LocalDateTime actualDateTime = LocalDateTime.now();
        String nameRoute=(!check)? "nameStore_"+actualDateTime+"_serial"+rand : "nameStore";

        String text="";

        if (names!=null) {
            for(int i=0;i<names.length;i++){
                text=String.valueOf(names[i])+"/";
                storage.setCreateArchive(text, nameRoute, false);
            }
            String lastText = names[names.length - 1];
            storage.setCreateArchive(lastText, nameRoute, true); 

        }   
        actualDateTime = null;
    }

    public static void storeId(int[]ids,ArchiveUtil storage,boolean check){
        LocalDateTime actualDateTime = LocalDateTime.now();
        String nameRoute=(!check)? "idStore_"+actualDateTime+"_serial"+rand : "nameStore";

        String text="";

        if (ids!=null) {
            for(int i=0;i<ids.length;i++){
                text=String.valueOf(ids[i])+"/";
                storage.setCreateArchive(text, nameRoute, false);
            }
            
            String lastText = String.valueOf(ids[ids.length - 1]);
            storage.setCreateArchive(lastText, nameRoute, true); 

        }   
        actualDateTime = null;

    }

    public static final String[] MOVIE_LISTINGS = {
            "Avatar", "Top gun: Maverick", "Avengers: End Game",
            "Oppenheimer", "Titanic", "Mission: Impossible",
            "Rapidos y Furiosos"
    };

    public static void storeShowCase(int[][][]cinemaMatrix,int[][]revenue,int[]losses,ArchiveUtil storage){
        String text;
        int band, sum;
        double porcentaje;
        LocalDateTime actualDateTime = LocalDateTime.now();

        String routeName="showCase_"+actualDateTime+"_serial"+rand;

        text = "C.E.V.S = Cantidad de Entradas Vendidas por Horario. \nT.I.S.P = Total Ingresos por Horario y por pelicula.\nT.G.P.P = Total de Ganancias Potenciales Perdidas por pelicula.";
        storage.setCreateArchive(text, routeName, true);
        storage.setCreateArchive("|________________________|           ~ INFORME DE VENTAS DEL CINE ("+actualDateTime+") ~          |_________________________|", routeName, true);
        storage.setCreateArchive("|        PELICULA        |            C.E.V.H            |              T.I.S.P             | T.G.P.P |   ESTATUS   |", routeName, true);
        if (cinemaMatrix!=null && revenue!=null && losses!=null) {
            for (int i = 0; i < cinemaMatrix.length; i++) {
                text = String.format("| %-25s", MOVIE_LISTINGS[i]);
                storage.setCreateArchive(text, routeName, true);
                band=0;sum=0;
                for (int j = 0; j < cinemaMatrix[0].length; j++) {
                    if (band==0) {
                        for (int y = 0; y <cinemaMatrix[0].length; y++) {
                            text = String.format("  %-4d", (cinemaMatrix[0][0].length - Validate.valUnsoldSeats2(cinemaMatrix, i, y)));
                            storage.setCreateArchive(text, routeName, true);
                            sum += cinemaMatrix[0][0].length - Validate.valUnsoldSeats2(cinemaMatrix, i, y);
                        }
                        text="       ";
                        storage.setCreateArchive(text, routeName, false);
                        band=1;
                    }else if (band==1){
                        for (int x = 0; x < revenue[0].length - 1; x++) {
                            text = String.format("  %02d$", revenue[i][x]);
                            storage.setCreateArchive(text, routeName, false);
                        }
                        band=2;
                    }   
                }
                text = String.format(" = %02d$     ", revenue[i][cinemaMatrix[0].length]);
                storage.setCreateArchive(text, routeName, false);
                text = String.format("  %02d$   ", losses[i]*2);
                storage.setCreateArchive(text, routeName, false);
                porcentaje =  (cinemaMatrix[0][0].length * cinemaMatrix[0].length)*0.6; // Se mulriplica por 0,6 porques el criterio es que si pasa de
                if (sum<Math.round(porcentaje)) {
                    text = String.format(" %7s", "FRACASO");
                    storage.setCreateArchive(text, routeName, true);
                }else{
                    text = String.format("  %5s", "EXITO");
                    storage.setCreateArchive(text, routeName, true);
                }
            }
        }





    }
}