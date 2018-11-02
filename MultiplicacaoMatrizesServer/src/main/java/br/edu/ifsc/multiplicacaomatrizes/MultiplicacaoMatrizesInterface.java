/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Lucas
 */
public interface MultiplicacaoMatrizesInterface extends Remote {
    public float add(float valA, float valB) throws RemoteException; // Teste para ver se está funcionando o RMI
    public String multiplicacao(String matrizA, String matrizB) throws RemoteException;
}
