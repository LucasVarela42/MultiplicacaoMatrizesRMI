/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.rmi.Naming;

/**
 *
 * @author Lucas
 */
public class MultiplicacaoMatrizesMain {
    
    public static void main(String[] args) {
        MultiplicacaoMatrizes matriz;
        System.out.println("Iniciando o gerenciador de segurança...");
        System.setProperty("java.security.policy", "file:./client.policy");

        System.out.println("Iniciando MultiplicacaoMatrizesClientRMI...");
        try {
            MultiplicacaoMatrizesInterface matrizService = (MultiplicacaoMatrizesInterface) 
                    Naming.lookup("rmi://localhost:1099/MultiplicacaoMatrizes");
            
            matriz = new MultiplicacaoMatrizes();
            
            matriz.setMatC(matrizService.multiplicacao(matriz.getMatA(), matriz.getMatB()));
            System.out.println(matriz.toString());

        } catch (Exception e) {
            System.err.println("\tErro: " + e.getMessage());
            System.exit(1);
        }
    }
}
