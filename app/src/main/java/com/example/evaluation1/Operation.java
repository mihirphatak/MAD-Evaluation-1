package com.example.evaluation1;

import java.io.Serializable;

public class Operation implements Serializable {
    double a, b;
    String operation;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Operation(double a, double b, String operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }
}
