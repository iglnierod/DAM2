/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.calculator;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class Calculator {

    public static Float add(Float a, Float b) {
        return a + b;
    }

    public static Float substract(Float a, Float b) {
        return a - b;
    }

    public static Float multiply(Float a, Float b) {
        return a * b;
    }

    public static Float divide(Float a, Float b) {
        if (b != 0) {
            return a / b;
        };
        return 0f;
    }
}
