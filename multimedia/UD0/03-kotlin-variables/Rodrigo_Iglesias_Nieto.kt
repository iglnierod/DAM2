package com.example.greetingcard.boletin1

import java.util.Calendar
import kotlin.math.pow

fun main() {
    // 1.
    println("~~~~~~~~~EJ 1~~~~~~~~~")
    var num: Int;
    var str: String;
    var dbl: Double;

    num = 5;
    str = "Hola mundo!";
    dbl = 5.2;

    println("num: $num");
    println("str: $str");
    println("dbl: $dbl");

    // 2.
    println("~~~~~~~~~EJ 2~~~~~~~~~")

    var anioNacimiento: Int = 2004;
    var edad: Int = Calendar.getInstance().get(Calendar.YEAR) - anioNacimiento;

    println(edad);

    // 3.
    println("~~~~~~~~~EJ 3~~~~~~~~~")

    var metros: Double = 50.0;
    var pulgadas: Double = metros * 39.37;
    println("$metros en pulgadas: $pulgadas");

    // 4.
    println("~~~~~~~~~EJ 4~~~~~~~~~")

    val nombre: String = "Rodrigo";
    val apellido: String = "Iglesias";
    var nombreCompleto: String = "$nombre $apellido";

    println("Mi nombre es: $nombreCompleto");

    // 5.
    println("~~~~~~~~~EJ 5~~~~~~~~~")

    var celsius = 36.5;
    var farenheit = celsius * 9 / 5 + 32;

    println("${celsius}º en Farenheit son ${farenheit}F");

    // 6.
    println("~~~~~~~~~EJ 6~~~~~~~~~")

    val PI: Double = 3.14159;
    var radio = 3.0;

    print("El area de un circulo con radio $radio es ${PI * (radio.pow(2))}");

    // 7.
    println("~~~~~~~~~EJ 7~~~~~~~~~")

    var numero1 = 10;
    var numero2 = 5;

    println("$numero1 + $numero2: ${numero1 + numero2}");
    println("$numero1 - $numero2: ${numero1 - numero2}");
    println("$numero1 * $numero2: ${numero1 * numero2}");
    println("$numero1 / $numero2: ${numero1 / numero2}");

    // 8.
    println("~~~~~~~~~EJ 8~~~~~~~~~")

    var esMayorDeEdad = false;
    var string: String = "";
    if (!esMayorDeEdad)
        string += "no"
    println("$string es mayor de edad");

    // 9.
    println("~~~~~~~~~EJ 9~~~~~~~~~")

    var name: String?;

    name = "Rodrigo";
    //nombre = null;

    if (name != null)
        println("$name");
    else
        println("El nombre es nulo");

    // 10.
    println("~~~~~~~~~EJ 10~~~~~~~~")

    var letra: Char = 'k';
    val vocales: List<Char> = listOf('A', 'E', 'I', 'O', 'U');

    if (letra.uppercaseChar() in vocales)
        println("La letra '$letra' es una vocal");
    else
        println("La letra '$letra' NO es una vocal");
}