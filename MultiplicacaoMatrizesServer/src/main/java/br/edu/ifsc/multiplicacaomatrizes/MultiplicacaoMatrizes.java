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

    public MultiplicacaoMatrizes() throws RemoteException {
    }

    public float add(float vlrA, float vlrB) throws RemoteException {
        System.out.println("\t\tCliente remoto invocou o método ADD...");
        return vlrA + vlrB;
    }

    @Override
    public String multiplicacao(String matrizA, String matrizB) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
