/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class MatrizHandler implements Runnable {
    
    private static final long serialVersionUID = 1L;

    private int[][] subMatriz;
    private int[][] matrizB;
    private static ArrayList<int[][]> matrizC;
    private String[] args;
    
    private ArrayList<MultiplicacaoMatrizesInterface> servers = new ArrayList();
    private boolean started = false;
    private static int nextServer = 0;

    public MatrizHandler(String[] args, int[][] subMatriz, int[][] matrizB) {
        this.subMatriz = subMatriz;
        this.matrizB = matrizB;
        this.args = args;

        if (!started) {
            matrizC = new ArrayList<int[][]>();
            connection();
        }
    }

    private synchronized void connection() {
        for (int i = 0; i < args.length; i++) {
            try {
                MultiplicacaoMatrizesInterface matrizService = (MultiplicacaoMatrizesInterface) 
                        Naming.lookup("rmi://" + args[i] + ":1099/MultiplicacaoMatrizes");
                servers.add(matrizService);
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
            } catch (NotBoundException ex) {
                System.err.println(ex.getMessage());
            } catch (MalformedURLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        started = true;

    }

    private synchronized MultiplicacaoMatrizesInterface getServer() {
        MultiplicacaoMatrizesInterface matrizesInterface = servers.get(nextServer);
        System.out.println(servers.get(nextServer));
        System.out.println(nextServer);
        nextServer++;
        
        if (nextServer >= servers.size()) {
            nextServer = 0;
        }
        return matrizesInterface;
    }

    public int[][] getSubMatriz() {
        return subMatriz;
    }

    public void setSubMatriz(int[][] subMatriz) {
        this.subMatriz = subMatriz;
    }

    public static ArrayList<int[][]> getMatrizC() {
        return matrizC;
    }

    public static void setMatrizC(ArrayList<int[][]> matrizC) {
        MatrizHandler.matrizC = matrizC;
    }
    

    @Override
    public void run() {
        try {
            MultiplicacaoMatrizesInterface matrizService = getServer();
            matrizC.add(matrizService.multiplicacao(subMatriz, matrizB));
        } catch (Exception e) {
            System.err.println("\tErro: " + e.getMessage());
            System.exit(1);
        }
    }

}
