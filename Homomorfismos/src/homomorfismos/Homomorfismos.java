/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homomorfismos;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Homomorfismos {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el tamano del grafo:");
        int a = s.nextInt();
        int[][] m = new int[a][a];
        System.out.println("Ingrese la matriz de adyasencia (solo 1 y 0)");
        for (int i = 0; i < a; i++){
            int j = 0;
            while (j < a){
                int u = s.nextInt();
                if (u == 0 || u == 1){
                    m[i][j] = u;
                    j++;
                } else {
                    System.out.println("\t"+"Eso no es válido, pon otro");
                    System.out.println("(0_");
                    System.out.println("//\\");
                    System.out.println("v_/_");
                }
            }
        }
        System.out.println();
        
        Matriz mat = new Matriz(m);
        System.out.println("\t"+"El grupo de automorfismos esta conformado por los grafos con las siguientes matrices de adyacencia");
        System.out.println("\t"+"Notar que esto sólo aplica para grafos no dirigidos");
        System.out.println("    /_");
        System.out.println("_||_");
        System.out.println(" (o_");
        System.out.println(" //\\");
        System.out.println(" V_/_");
        System.out.println();
        mat.seeAut();
    }
}
