/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class MultiplicacaoMatrizesMain {

    public static void main(String[] args) {
        System.out.println("Iniciando o gerenciador de segurança...");
        System.setProperty("java.security.policy", "file:./client.policy");
        try {
            String hostName = InetAddress.getByName("localhost").getHostAddress();
            System.out.println(hostName);
            System.setProperty("java.rmi.server.hostname", hostName);
        } catch (UnknownHostException ex) {
            System.err.println("Erro server hostname: " + ex.getMessage());
        }

        System.out.println("Iniciando MultiplicacaoMatrizesClientRMI...");
        int tamanho = 4;
        Matriz matrizA = new Matriz("src/main/resources/matrizes/matA.txt", tamanho);
        Matriz matrizB = new Matriz("src/main/resources/matrizes/matB.txt", tamanho);
        Matriz matrizC;

        ArrayList<Thread> threads = new ArrayList();
        MatrizHandler matrizCalculator = null;

        ArrayList<int[][]> subMatrizes = new ArrayList<>();
        subMatrizes = matrizA.separarMatriz(args.length);

        try {
            for (int[][] subMatrize : subMatrizes) {
                MatrizHandler matrizHandler = new MatrizHandler(
                        args,
                        matrizA.getMatriz(),
                        matrizB.getMatriz(),
                        subMatrize);
                matrizCalculator = matrizHandler;
                Thread t = new Thread(matrizHandler);
                threads.add(t);
                t.start();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            matrizC = new Matriz(matrizCalculator.getMatrizC());
            matrizC.exportarMatriz(matrizC.getMatriz(), "src/main/resources/matrizes/matC.txt");
            System.out.println(matrizC.toString());
            System.out.println(MD5Checksum.getMD5Checksum("src/main/resources/matrizes/matC.txt"));

        } catch (Exception e) {
            System.err.println("\tErro Main: " + e.getMessage());
            System.exit(1);
        }
    }
}
