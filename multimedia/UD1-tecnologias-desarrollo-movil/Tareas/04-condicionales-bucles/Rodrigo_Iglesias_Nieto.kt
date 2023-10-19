import androidx.compose.animation.scaleOut
import java.util.Scanner
import kotlin.random.Random

/*
 * Ejercicio 1: Escribe un programa que pida al usuario ingresar un número y 
 * luego determine si ese número es positivo, negativo o igual a cero. Luego,
 *  muestra el resultado en la pantalla.
*/
fun determinarSigno(n: Int) {
    if (n > 0)
        println("El número $n es positivo");
    else if (n < 0)
        println("El número $n es negativo");
    else
        println("El número $n es 0");
}

/* 
 * Ejercicio 2: Utiliza un bucle for para imprimir los números del 1 al 20.
*/
fun imprimirNumerosDelUnoAlDiez() {
    for (i in 1..10)
        println(i)
}

/* 
 * Ejercicio 3: Utiliza un bucle while para imprimir los números del 1 al 10.
*/
fun imprimirNumerosDelUnoAlDiezConWhile() {
    var x = 1;
    while (x <= 10)
        println(x++);
}

/*
 * Ejercicio 3.1: Crea un programa que pida al usuario ingresar un número y luego use un bucle 
 * while para imprimir los números desde 1 hasta ese número. 
*/
fun listarNumeros(n: Int) {
    var x = 1;
    while (x <= n)
        println(x++);
}

/* 
 * Ejercicio 4: Crea una función que determine si un número es par o impar utilizando un when.
*/
fun determinarParOImpar(n: Int) {
    when (n % 2) {
        0 -> println("Es par")
        else -> {
            println("Es impar")
        }
    }

}

/* 
 * Ejercicio 5: Escribe un programa que solicite al usuario que ingrese un día de la semana 
 * (por ejemplo, "lunes", "martes", etc.) y luego use la expresión when para determinar si 
 * es un día laborable o un día de fin de semana. Imprime el resultado en la pantalla.
*/
fun determinarDiaSemana(dia: String) {
    when (dia) {
        "lunes", "martes", "miercoles", "jueves", "viernes" -> println("$dia es un día laborable");
        else -> {
            println("$dia no es un día laborable")
        }
    }
}

/* 
 * Ejercicio 6: Utiliza un bucle for para imprimir los números pares del 2 al 20.
*/
fun imprimirNumerosParesDelDosAlVeinte() {
    for (i in 2..20 step 2)
        print("$i ");
}

/* 
 * Ejercicio 7: Utiliza un bucle for para imprimir los números del 10 al 1 en orden descendente.
*/
fun imprimirNumerosDelDiezAlUnoEnOrdenDescendente() {
    for (i in 10 downTo 1)
        print("$i ");
}

/* 
 * Ejercicio 8: Utiliza un bucle for para sumar los números del 1 al 100 y luego imprimir la suma.
*/
fun sumarNumerosDelUnoAlCien() {
    var x = 0;
    for (i in 1..100)
        x += i;
    println(x)
}

/*
 * Ejercicio 9: Crea un juego simple en el que el programa elija un número aleatorio entre 1 y 10, y 
 * el usuario tenga que adivinarlo. Usa un bucle while para permitir al usuario hacer múltiples intentos
 * hasta que adivine el número. Proporciona pistas sobre si el número es mayor o menor en cada intento.
*/
fun adivinarNumero() {
    var sc = Scanner(System.`in`);
    val aleatorio = Random.nextInt(1, 11);
    var num = -1;
    while (num != aleatorio) {
        println("Introduce un número: ")
        num = sc.nextInt();
        if (num == aleatorio) {
            println("GANASTE!")
            break;
        } else if (num < aleatorio) {
            println("MAYOR");
        } else if (num > aleatorio) {
            println("MENOR");
        }
    }
}

/* 
 * Ejercicio 10: Partiendo del conjunto de números (1, 2, 3, 4, 5) creado en la función MAIN, crea la funcion
 * estaNumero() para que le pasemos un número y verifique si un número está o no en el conjunto
*/

fun estaNumero(n: Int, lista: List<Int>) {
    if (n in lista) {
        println("El número $n está en la lista: $lista");
        return;
    }
    println("El número $n no está en la lista: $lista");
}


/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

fun main() {

    // Prueba los ejercicios aquí
    println("------------EJERCICIO_1-------------")
    determinarSigno(5)
    println(" ")
    println("------------EJERCICIO_2-------------")
    imprimirNumerosDelUnoAlDiez()
    println("")
    println("------------EJERCICIO_3-------------")
    imprimirNumerosDelUnoAlDiezConWhile()
    println("")
    println("------------EJERCICIO_3.1-----------")
    listarNumeros(20)
    println("")
    println("------------EJERCICIO_4-------------")
    determinarParOImpar(7)
    println("")
    println("------------EJERCICIO_5-------------")
    determinarDiaSemana("sabado")
    println("")
    println("------------EJERCICIO_6-------------")
    imprimirNumerosParesDelDosAlVeinte()
    println("")
    println("-------------EJERCICIO_7-------------")
    imprimirNumerosDelDiezAlUnoEnOrdenDescendente()
    println("")
    println("")
    println("------------EJERCICIO_8-------------")
    sumarNumerosDelUnoAlCien()
    println("")
    println("------------EJERCICIO_9-------------")
    //adivinarNumero()
    println("")
    println("------------EJERCICIO_10------------")
    val numeros = listOf(1, 2, 3, 4, 5)
    estaNumero(3,numeros)
    println("")

}
