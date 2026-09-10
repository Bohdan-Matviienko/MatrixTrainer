package org.example.model;

public class MatrixServiceImpl implements MatrixService {

    @Override
    public boolean isMatchMultiply(Matrix a, Matrix b) {

        return a.getM() == b.getN();
    }

    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        if (!isMatchMultiply(a, b)) {
            throw new RuntimeException("Матриці неможливо помножити!");
        }


        Matrix result = new Matrix(a.getN(), b.getM());

        for (int i = 0; i < a.getN(); i++) {
            for (int j = 0; j < b.getM(); j++) {
                int sum = 0;
                StringBuilder formula = new StringBuilder();

                for (int k = 0; k < a.getM(); k++) {
                    int valA = a.getValue(i, k);
                    int valB = b.getValue(k, j);
                    sum += valA * valB;


                    String strA = (valA < 0) ? "(" + valA + ")" : String.valueOf(valA);
                    String strB = (valB < 0) ? "(" + valB + ")" : String.valueOf(valB);
                    formula.append(strA).append("*").append(strB);

                    if (k < a.getM() - 1) {
                        formula.append(" + ");
                    }
                }
                result.setValue(i, j, sum);
                result.setFormula(i, j, formula.toString());
            }
        }
        return result;
    }
}