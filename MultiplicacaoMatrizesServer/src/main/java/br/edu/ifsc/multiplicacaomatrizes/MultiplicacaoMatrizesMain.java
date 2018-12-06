/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Lucas
 */
public class MultiplicacaoMatrizesMain {

    public static void main(String[] args) {
        System.out.println("Iniciando CalculadoraServerRMI...");
        try {
            // Inicia o gerenciador de segurança
            System.out.println("\tIniciando o gerenciador de segurança...");
            System.setProperty("java.security.policy","file:./server.policy");
            String hostName = InetAddress.getByName("localhost").getHostAddress();
            System.out.println(hostName);
            System.setProperty("java.rmi.server.hostname", hostName);

            //usar apenas se for RMI codebase 
            //System.setSecurityManager(new SecurityManager());

            // Instancia o objeto localmente
            System.out.println("\tInstanciado o objeto localmente...");
            MultiplicacaoMatrizes matrizes = new MultiplicacaoMatrizes();

            // Registra o objeto para acesso remoto
            System.out.println("\tRegistrando o objeto para acesso remoto...");
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/MultiplicacaoMatrizes", matrizes);

            // Aguardando requisições
            System.out.println("\tAguardando requisições...");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            System.exit(1);
        }
    }
}
