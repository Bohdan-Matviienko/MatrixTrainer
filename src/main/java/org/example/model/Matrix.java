package org.example.model;

import java.util.Random;

public class Matrix {
    static final int MAX_SIZE = 4;
    static final int MIN_SIZE = 2;
    static final int MIN_VALUE = -9;
    static final int MAX_VALUE = 9;

    private int n;
    private int m;
    private int[][] arr;
    private String[][] formulas;


    public Matrix(int n, int m) {
        if (n < 1 || m < 1) {
            throw new RuntimeException("Матриці не можуть мати такі вимірності");
        }
        this.n = n;
        this.m = m;
        arr = new int[n][m];
        formulas = new String[n][m];
    }


    public Matrix() {
        Random random = new Random();
        n = random.nextInt(MAX_SIZE - MIN_SIZE + 1) + MIN_SIZE;
        m = random.nextInt(MAX_SIZE - MIN_SIZE + 1) + MIN_SIZE;
        arr = new int[n][m];
        formulas = new String[n][m];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
            }
        }
    }

    public int getN() { return n; }


    public int getM() { return m; }


    public int getValue(int i, int j) { return arr[i][j]; }


    public void setValue(int i, int j, int value) { arr[i][j] = value; }


    public boolean isCorrect(int value, int i, int j) { return arr[i][j] == value; }


    public String getFormula(int i, int j) { return formulas[i][j]; }


    public void setFormula(int i, int j, String formula) { formulas[i][j] = formula; }
}