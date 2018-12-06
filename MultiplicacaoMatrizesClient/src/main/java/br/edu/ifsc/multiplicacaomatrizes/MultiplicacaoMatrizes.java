/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class MultiplicacaoMatrizes {

    private int matA[][];
    private int matB[][];
    private int matC[][];
    private int tamanho = 4;

    public MultiplicacaoMatrizes() {
        this.matA = carregarMatriz("src/main/resources/matrizes/matA.txt");
        this.matB = carregarMatriz("src/main/resources/matrizes/matB.txt");
        this.matC = new int[matA.length][matA.length];
    }

    private int[][] carregarMatriz(String archive) {
        int matriz[][] = new int[tamanho][tamanho];
        //Lendo arquivo da matriz
        try {
            Path filePath = Paths.get(new File(archive).getAbsolutePath());
            Scanner input = new Scanner(filePath);
            for (int i = 0; i < tamanho; ++i) {
                for (int j = 0; j < tamanho; ++j) {
                    if (input.hasNextInt()) {
                        matriz[i][j] = input.nextInt();
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return matriz;
    }

    public int[][] getMatA() {
        return matA;
    }

    public void setMatA(int[][] matA) {
        this.matA = matA;
    }

    public int[][] getMatB() {
        return matB;
    }

    public void setMatB(int[][] matB) {
        this.matB = matB;
    }

    public int[][] getMatC() {
        return matC;
    }

    public void setMatC(int[][] matC) {
        this.matC = matC;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < matC.length; i++) {
            for (int j = 0; j < matC[i].length; j++) {
                builder.append(matC[i][j] + "\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
