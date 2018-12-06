/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.multiplicacaomatrizes;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Matriz {

    private int matriz[][];
    private int tamanho;
    private String path;

    public Matriz(String path, int tamanho) {
        this.path = path;
        this.tamanho = tamanho;
        this.matriz = new int[tamanho][tamanho];
        carregarMatriz();
    }

    public Matriz(int[][] matriz) {
        this.matriz = matriz;
        this.tamanho = matriz.length;
    }

    private void carregarMatriz() {
        //Lendo arquivo da matriz
        try {
            Path filePath = Paths.get(new File(path).getAbsolutePath());
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
    }

    public ArrayList<int[][]> separarMatriz(int qtd) {
        ArrayList<int[][]> matrizSeparada = new ArrayList<int[][]>();
        int n = tamanho / qtd;
        int m = 0;
        for (int i = 0; i < qtd; i++) {
            int[][] subMatriz = new int[n][tamanho];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < tamanho; k++) {
                    subMatriz[j][k] = matriz[m][k];
                }
                m++;
            }

            matrizSeparada.add(subMatriz);
        }
        return matrizSeparada;
    }

    public ArrayList<int[]> montarMatriz(ArrayList<int[]> matrizSeparada) {
        ArrayList<int[]> matrizCompleta = new ArrayList<>();

        matrizCompleta = matrizSeparada;

        return matrizCompleta;
    }

    public static void divideMatrix(int[][] matrix, int[][] subMatrix, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < subMatrix.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < subMatrix.length; j1++, j2++) {
                subMatrix[i1][j1] = matrix[i2][j2];
            }
        }
    }

    public static void buildMatrix(int[][] subMatrix, int[][] matrix, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < subMatrix.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < subMatrix.length; j1++, j2++) {
                matrix[i2][j2] = subMatrix[i1][j1];
            }
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                builder.append(matriz[i][j] + "\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
