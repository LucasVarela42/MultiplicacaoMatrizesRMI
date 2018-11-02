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
        System.out.println("Iniciando MultiplicacaoMatrizesClientRMI...");
        try {
            MultiplicacaoMatrizesInterface matriz = (MultiplicacaoMatrizesInterface) Naming.lookup("rmi://localhost:1099/MultiplicacaoMatrizes");
            System.out.println("\tExecutando ADD(2,2): "+matriz.add(2, 2)); // Teste do RMI
            //System.out.println("\tExecutando multiplicacao(matA,matB): " + calc.multiplicacao("", "")); //Testar
        } catch (Exception e) {
            System.err.println("\tErro: " + e.getMessage());
            System.exit(1);
        }
    }
}
