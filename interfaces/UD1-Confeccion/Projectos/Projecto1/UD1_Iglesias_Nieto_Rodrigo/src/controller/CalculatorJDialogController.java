/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.calculator.Calculator;
import view.calculator.CalculatorJDialog;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class CalculatorJDialogController {

    private final CalculatorJDialog calculatorView;
    private Float firstNumber = null;
    private Float secondNumber = null;
    private String operator;
    private boolean decimalNumber;

    public CalculatorJDialogController(CalculatorJDialog view) {
        this.calculatorView = view;
        this.setAllListeners();
    }

    private void setAllListeners() {
        // OPERATORS
        this.calculatorView.setAddButtonActionListener(setAddButtonListener());
        this.calculatorView.setSubstractButtonActionListener(setSubstractButtonListener());
        this.calculatorView.setMultiplyButtonActionListener(setMultiplyButtonListener());
        this.calculatorView.setDivideButtonActionListener(setDivideButtonListener());
        // NUMBERS
        this.calculatorView.setNumberZeroButtonActionListener(setNumberZeroButtonListener());
        this.calculatorView.setNumberOneButtonActionListener(setNumberOneButtonListener());
        this.calculatorView.setNumberTwoButtonActionListener(setNumberTwoButtonListener());
        this.calculatorView.setNumberThreeButtonActionListener(setNumberThreeButtonListener());
        this.calculatorView.setNumberFourButtonActionListener(setNumberFourButtonListener());
        this.calculatorView.setNumberFiveButtonActionListener(setNumberFiveButtonListener());
        this.calculatorView.setNumberSixButtonActionListener(setNumberSixButtonListener());
        this.calculatorView.setNumberSevenButtonActionListener(setNumberSevenButtonListener());
        this.calculatorView.setNumberEightButtonActionListener(setNumberEightButtonListener());
        this.calculatorView.setNumberNineButtonActionListener(setNumberNineButtonListener());
        // OTHER
        this.calculatorView.setClearDisplayButtonActionListener(setClearDisplayButtonListener());
        this.calculatorView.setDecimalButtonActionListener(setDecimalButtonListener());
        this.calculatorView.setEqualButtonActionListener(setEqualButtonListener());
    }

    /*
    * ALL CALCULATOR FUNCTIONS
     */
    private void updateCalculatorDisplay(String newTxt) {
        String txt = calculatorView.getDisplayText();
        System.out.println(txt);
        if (txt.equals("0")) {
            clearDisplay();
            calculatorView.updateDisplay(newTxt);
        } else {
            calculatorView.updateDisplay(txt + newTxt);
        }
    }

    private void clearDisplay() {
        calculatorView.updateDisplay("");
    }

    private void setDecimalNumber(boolean boo) {
        if (boo) {
            calculatorView.disableDecimalButton();
        } else {
            calculatorView.enableDecimalButton();
        }
        this.decimalNumber = boo;
    }

    private void saveOperator(String operator) {
        saveNumberFromDisplay();
        setDisplayToZero();
        this.operator = operator;
        if (secondNumber == null) {
            return;
        }
        switch (operator) {
            case "+":
                firstNumber = Calculator.add(firstNumber, secondNumber);
                break;
            case "-":
                firstNumber = Calculator.substract(firstNumber, secondNumber);
                break;
            case "*":
                firstNumber = Calculator.multiply(firstNumber, secondNumber);
                break;
            case "/":
                firstNumber = Calculator.divide(firstNumber, secondNumber);
        }
    }

    private void saveNumberFromDisplay() {
        Float number = Float.valueOf(calculatorView.getDisplayText());
        if (firstNumber == null && operator == null) {
            firstNumber = number;
            return;
        }
        secondNumber = number;
    }

    private void resetNumbers() {
        firstNumber = null;
        secondNumber = null;
        operator = null;
    }

    private void setDisplayToZero() {
        clearDisplay();
        updateCalculatorDisplay("0");
        setDecimalNumber(false);
    }

    private void operate() {
        saveNumberFromDisplay();
        if (secondNumber == null) {
            return;
        }
        switch (operator) {
            case "+":
                firstNumber = Calculator.add(firstNumber, secondNumber);
                break;
            case "-":
                firstNumber = Calculator.substract(firstNumber, secondNumber);
                break;
            case "*":
                firstNumber = Calculator.multiply(firstNumber, secondNumber);
                break;
            case "/":
                firstNumber = Calculator.divide(firstNumber, secondNumber);
        }
        clearDisplay();
        secondNumber = null;
        updateCalculatorDisplay(String.valueOf(firstNumber));
        setDecimalNumber(false);
    }

    /* 
    * ALL LISTENERS
     */
    // OPERATORS
    private ActionListener setAddButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOperator("+");
            }
        };
        return al;
    }

    private ActionListener setSubstractButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOperator("-");

            }
        };
        return al;
    }

    private ActionListener setMultiplyButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOperator("*");

            }
        };
        return al;
    }

    private ActionListener setDivideButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOperator("/");
            }
        };
        return al;
    }

    // NUMBERS
    private ActionListener setNumberZeroButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("0");
            }
        };
        return al;
    }

    private ActionListener setNumberOneButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("1");
            }
        };
        return al;
    }

    private ActionListener setNumberTwoButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("2");
            }
        };
        return al;
    }

    private ActionListener setNumberThreeButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("3");
            }
        };
        return al;
    }

    private ActionListener setNumberFourButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("4");
            }
        };
        return al;
    }

    private ActionListener setNumberFiveButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("5");
            }
        };
        return al;
    }

    private ActionListener setNumberSixButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("6");

            }
        };
        return al;
    }

    private ActionListener setNumberSevenButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("7");
            }
        };
        return al;
    }

    private ActionListener setNumberEightButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("8");
            }
        };
        return al;
    }

    private ActionListener setNumberNineButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalculatorDisplay("9");
            }
        };
        return al;
    }

    // OTHER
    private ActionListener setDecimalButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (calculatorView.getDisplayText().equals("0")) {
                    updateCalculatorDisplay("0.");
                } else {
                    updateCalculatorDisplay(".");
                }
                setDecimalNumber(true);
            }
        };
        return al;
    }

    private ActionListener setClearDisplayButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("AC");
                setDisplayToZero();
                setDecimalNumber(false);
                resetNumbers();
            }
        };
        return al;
    }

    private ActionListener setEqualButtonListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operate();
            }
        };
        return al;
    }
}
