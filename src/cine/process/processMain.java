package cine.process;

import java.io.IOException;

public class processMain {
    
    public static void processIni(int[][]revenue, int[][][]cinema,int[]losses,String[]movies){
        Process.iniMatrix(cinema);
        Process.iniArray(revenue);
        Process.iniArray(losses);
        Process.iniArray(movies);
    }

    public static void proccessData(String[] movies, int[][][] cinema, int[][] revenue, int[] losses, String router) throws IOException {
        Process.dataEntry(movies);
        Process.purchaseProcessStart(cinema, movies);
        Process.calcRevenueLosses(cinema, revenue, losses);
        Process.showCase(cinema, revenue, losses, router);
    }
}