package cine.helpers;

import cine.objects.*;
import java.util.Scanner;

public class consultMain {
    public static void consultDataMain(String option, ArchiveUtil storage){
        Name userDefault=null;
        String data="";
        String[] listDirectories;
        int[] index=new int[2];

        if (storage.directoriesExist()) {
            data=consultData.optionSelect(option);
        }
        if (data==null) {
            System.out.println("Este dato no es valido, intente de nuevo");
        }

        if (option.equals("nombre")){
            try {
                userDefault=new Name(data,"Default");
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }
            listDirectories=storage.getDirectories();
            String[] choice=consultData.getDirectoriesPerName(listDirectories, userDefault);
            if (choice==null){
                System.out.println("ERROR: No se pudo abrir el archivo");
                return;
            }
            Scanner useAverageArch=archive.getArchive(choice[1]);
            Scanner useNameArch=archive.getArchive(choice[0]);
           // index=consultData
        }
    }
}
