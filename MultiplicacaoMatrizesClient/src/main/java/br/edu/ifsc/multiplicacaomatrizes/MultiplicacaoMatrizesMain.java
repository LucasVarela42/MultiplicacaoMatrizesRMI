/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class MultiplicacaoMatrizesMain {

    public static void main(String[] args) {
        System.out.println("Iniciando o gerenciador de segurança...");
        System.setProperty("java.security.policy", "file:./client.policy");
        try {
            String hostName = InetAddress.getByName("10.151.31.157").getHostAddress();
            System.out.println(hostName);
            System.setProperty("java.rmi.server.hostname", hostName);
        } catch (UnknownHostException ex) {

        }

        System.out.println("Iniciando MultiplicacaoMatrizesClientRMI...");

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        int tamanho = 4;
        Matriz matrizA = new Matriz("src/main/resources/matrizes/matA.txt", tamanho);
        Matriz matrizB = new Matriz("src/main/resources/matrizes/matB.txt", tamanho);
        int[][] matrizC = new int[tamanho][tamanho];

        System.out.println("Matriz A");
        System.out.println(matrizA.toString());
        System.out.println("Matriz B");
        System.out.println(matrizB.toString());

        ArrayList<int[][]> subMatrizes = new ArrayList<>();
        subMatrizes = matrizA.separarMatriz(args.length);

        //matrizC = matrizA.montarMatriz(subMatrizes);
        try {
            int count = 0;
            MultiplicacaoMatrizesInterface matrizService = (MultiplicacaoMatrizesInterface) Naming.lookup("rmi://localhost:1099/MultiplicacaoMatrizes");

            for (int[][] subMatrize : subMatrizes) {
                subMatrize = matrizService.multiplicacao(subMatrize, matrizB.getMatriz());
                subMatrizes.set(count, subMatrize);
                System.out.println("\n------- matriz C Cliente");
                for (int i = 0; i < subMatrize.length; i++) {
                    for (int j = 0; j < subMatrize.length; j++) {
                        System.out.print(subMatrize[i][j] + "\t");
                    }
                    System.out.println("\n");
                }
                count++;
            }
            
            matrizC = matrizA.montarMatriz(subMatrizes);
            

            System.out.println("");

//            // first matrix parts
//            int[][] a_1 = new int[tamanho / 2][tamanho / 2];
//            int[][] a_2 = new int[tamanho / 2][tamanho / 2];
//            int[][] a_3 = new int[tamanho / 2][tamanho / 2];
//            int[][] a_4 = new int[tamanho / 2][tamanho / 2];
//
//            // second matrix parts
//            int[][] b_1 = new int[tamanho / 2][tamanho / 2];
//            int[][] b_2 = new int[tamanho / 2][tamanho / 2];
//            int[][] b_3 = new int[tamanho / 2][tamanho / 2];
//            int[][] b_4 = new int[tamanho / 2][tamanho / 2];
//            
//            System.out.println("----------- divide A");
//            Matriz.divideMatrix(matrizA.getMatriz(), a_1, 0, 0);
//            Matriz matrizResultado = new Matriz(a_1);
//            System.out.println(matrizResultado.toString());
//            Matriz.divideMatrix(matrizA.getMatriz(), a_2, 0, tamanho/2);
//            matrizResultado = new Matriz(a_2);
//            System.out.println(matrizResultado.toString());
//            Matriz.divideMatrix(matrizA.getMatriz(), a_3, tamanho/2, 0);
//            matrizResultado = new Matriz(a_3);
//            System.out.println(matrizResultado.toString());
//            Matriz.divideMatrix(matrizA.getMatriz(), a_4, tamanho/2, tamanho/2);
//            matrizResultado = new Matriz(a_4);
//            System.out.println(matrizResultado.toString());
//            
//            System.out.println("\n----------- divide B");
//            
//            Matriz.divideMatrix(matrizB.getMatriz(), b_1, 0, 0);
//            matrizResultado = new Matriz(b_1);
//            System.out.println(matrizResultado.toString());
//            Matriz.divideMatrix(matrizB.getMatriz(), b_2, 0, tamanho/2);
//            matrizResultado = new Matriz(b_2);
//            System.out.println(matrizResultado.toString());
//            Matriz.divideMatrix(matrizB.getMatriz(), b_3, tamanho/2, 0);
//            matrizResultado = new Matriz(b_3);
//            System.out.println(matrizResultado.toString());
//            Matriz.divideMatrix(matrizB.getMatriz(), b_4, tamanho/2, tamanho/2);
//            matrizResultado = new Matriz(b_4);
//            System.out.println(matrizResultado.toString());
//            
//            Registry server_1 = LocateRegistry.getRegistry(args[0], 1099);
//            MultiplicacaoMatrizesInterface calc_1 = (MultiplicacaoMatrizesInterface) server_1.lookup("MultiplicacaoMatrizes");
//
//            Registry server_2 = LocateRegistry.getRegistry(args[1], 1099);
//            MultiplicacaoMatrizesInterface calc_2 = (MultiplicacaoMatrizesInterface) server_2.lookup("MultiplicacaoMatrizes");
//
//            Registry server_3 = LocateRegistry.getRegistry(args[2], 1099);
//            MultiplicacaoMatrizesInterface calc_3 = (MultiplicacaoMatrizesInterface) server_3.lookup("MultiplicacaoMatrizes");
//
//            Registry server_4 = LocateRegistry.getRegistry(args[3], 1099);
//            MultiplicacaoMatrizesInterface calc_4 = (MultiplicacaoMatrizesInterface) server_4.lookup("MultiplicacaoMatrizes");
//            
//            Runnable r1 = () -> {
//                try {
//                    System.out.println("\tProcessando primeira parte da matriz... ");
//                    Matriz.buildMatrix(calc_1.multiplicacao(a_1, b_1), matrizC, 0, 0);
//                    } catch (RemoteException ex) {
//                    System.out.println("Erro Runnable: " + ex.getMessage());
//                }
//            };
//
//            Runnable r2 = () -> {
//                try {
//                    System.out.println("\tProcessando segunda parte da matriz... ");
//                    Matriz.buildMatrix(calc_2.multiplicacao(a_2, b_2), matrizC, 0, tamanho / 2);
//                } catch (RemoteException ex) {
//                    System.out.println("Erro Runnable: " + ex.getMessage());
//                }
//            };
//
//            Runnable r3 = () -> {
//                try {
//                    System.out.println("\tProcessando terceira parte da matriz... ");
//                    Matriz.buildMatrix(calc_3.multiplicacao(a_3, b_3), matrizC, tamanho / 2, 0);
//                } catch (RemoteException ex) {
//                    System.out.println("Erro Runnable: " + ex.getMessage());
//                }
//            };
//
//            Runnable r4 = () -> {
//                try {
//                    System.out.println("\tProcessando quarta parte da matriz... ");
//                    Matriz.buildMatrix(calc_4.multiplicacao(a_4, b_4), matrizC, tamanho / 2, tamanho / 2);
//                } catch (RemoteException ex) {
//                    System.out.println("Erro Runnable: " + ex.getMessage());
//                }
//            };
//            Thread thread_1 = new Thread(r1);
//            Thread thread_2 = new Thread(r2);
//            Thread thread_3 = new Thread(r3);
//            Thread thread_4 = new Thread(r4);
//
//            thread_1.start();
//            thread_2.start();
//            thread_3.start();
//            thread_4.start();
//
//            thread_1.join();
//            thread_2.join();
//            thread_3.join();
//            thread_4.join();
            //matrizResultado = new Matriz(matrizC);
            //System.out.println(matrizResultado.toString());
            //MultiplicacaoMatrizesInterface matrizService = (MultiplicacaoMatrizesInterface) 
            //        Naming.lookup("rmi://localhost:1099/MultiplicacaoMatrizes");
        } catch (Exception e) {
            System.err.println("\tErro Main: " + e.getMessage());
            System.exit(1);
        }
    }
}
