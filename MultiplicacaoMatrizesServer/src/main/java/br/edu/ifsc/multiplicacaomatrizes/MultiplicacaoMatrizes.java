/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lucas
 */
public class MultiplicacaoMatrizes extends UnicastRemoteObject implements MultiplicacaoMatrizesInterface {

    private static final long serialVersionUID = 1L;
    private int[][] matrizC;

    public MultiplicacaoMatrizes() throws RemoteException {
    }

    @Override
    public int[][] multiplicacao(int[][] matrizA, int[][] matrizB) throws RemoteException {
        matrizC = new int[matrizA.length][matrizB.length];
        System.out.println("Fazendo multiplicação...");
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizB.length; j++) {
                for (int k = 0; k < matrizB.length; k++) {
                    matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
        System.out.println("Multiplicação Finalizada!");
        
        System.out.println("\n------- matriz C");
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizB.length; j++) {
                System.out.print(matrizC[i][j] + "\t");
            }
            System.out.println("\n");
        }
        return matrizC;
    }

}
