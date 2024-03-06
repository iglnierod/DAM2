package com.example.tresenraya.doc

/**************************************************************************************************/
/************************************** 0-CONFIGURACION *******************************************/
/**************************************************************************************************/

0-CREACIÓN DEL PROYECTO
1-Insertar dependencia material icons material3
2-Insertamos dependencias de daggerhilt/firebase/navigation
3-Creamos la clase TresEnRayaApp.kt
4-Modificamos el AndroidManifest
5-Creamos proyecto en firebase con realtime database, descargamos el fichero json y lo insertamos.
6-Ejecutamos para ver si funciona

/**************************************************************************************************/
/********************************* 1-DEFINIENDO BASE DE DATOS *************************************/
/**************************************************************************************************/

Momento de reflexión, pensar antes de tirar lineas de código, Cómo vamos a estructurar BD, diseño, etc.

Listado de partidas
-board : List<String> = 9 posiciones
-gameId:String
-Player1
-playerId
-playerType
-Player2
-PlayerTurn

/**************************************************************************************************/
/********************************** 2-DEFINIENDO UI HOME ******************************************/
/**************************************************************************************************/

2-Creamos la capa UI, dentro de dicha capa creamos el directorio HOME donde vamos a tener un fichero
que se ocupará de la parte visual (VIEW) al entrar a la APP y otro fichero que se encargara de los
datos de la vista (ViewModel).
2.1-Creamos el HomeScreen.kt (view) - Simplemente crear los elementos básicos del layout, para
hacernos una idea de los datos que queremos guardar, y cómo mostrar los datos al usuario.
2.2-Creamos el HomeViewMode.kt (viewModel) - Simplemente inicializado para ver que funciona y se
lo dejamos pasado al Screen.

CHECK EXECUTION (modificar main)

/**************************************************************************************************/
/********************************** 3-DEFINIENDO UI GAME ******************************************/
/**************************************************************************************************/

3-Creamos en la capa UI el directorio GAME donde vamos a guardar los ficheros que crearán la ventana
de juego (VIEW) y su correspondiente fichero que gestionará los datos de la screen( VIEWMODEL).
3.1 Creamos el GameScreen.kt - Hacemos los layouts basicos para el tablero (tablero,cuadrados)
3.2 Creamos el GameScreenViewModel -Simplemente inicializado para ver que funciona y se
lo dejamos pasado al Screen.

CHECK EXECUTION (modificar main)

/**************************************************************************************************/
/********************************** 4-NAVEGACIÓN BÁSICA *******************************************/
/**************************************************************************************************/

Una vez creadas las dos UI, vamos a crear la navegación entre ellas:

4-Creamos en la capa UI un nuevo directorio CORE y nuevo fichero Navigator.kt
4.1-Para la navegación con Compose utilizaremos la función NavHost con su correspondiente NavController.
4.2Creamos las posibles rutas de navegación de nuestra APP y modificamos la llamada en el mainActivity,
para que delegue en este fichero todo el roll de navegación.
4.2-Modificamos los navHostController
4.3-Añadimos una función lambda para pasar las navegaciones y evitar así el paso de parámetros directos

CHECK EXECUTION

/**************************************************************************************************/
/************************************** 5-FIREBASE DATA *******************************************/
/**************************************************************************************************/

5- Creamos la clase FirebaseService dentro del directorio DATA
5.1-Definimos el PATH o ruta y creamos los primeros métodos para INSERTAR/BORRAR/ACTUALIZAR..
5.2-Creamos un directorio MODEL donde vamos a definir los tipos de datos que vamos a guardar en firebase
5.3-Creamos un directorio DI donde vamos a declarar el Módulo que va a proveer de datos a la UI (HomeViewModel/GameViewModel)
5.4-Injectamos las dependencias de firebase en el HomeViewModel y GameViewModel

/**************************************************************************************************/
/*********************************** 6-CREAMOS PRIMERA PARTIDA ************************************/
/**************************************************************************************************/

6.1-Creamos la función createGame en el HomeViewModel y probamos a ejecutar y ver que llega a firebase.
6.2-En caso de que falle, podemos seguir con el LogCat la traza de ejecución.

/**************************************************************************************************/
/*********************************** 7-RECUPERAMOS EL GAMEID **************************************/
/**************************************************************************************************/

7-Nos falta el gameId, tenemos una clave en firebase, vamos a tener que utilizar esa ID como gameId.
7.1-Modificamos el FirebaseService para recuperar el key y escribirlo en nuestro gameData
7.2-Guardamos en una variable el nuevo valor devuelto.

/**************************************************************************************************/
/*********************************** 8-NAVEGACIÓN COMPLETA ****************************************/
/**************************************************************************************************/

8-Con el partido creado tenemos que corregir la navegación para que nos lleve al tablero específico.
8.1-Modificar la navegación desde el HomeScreen() para el onCreate()
8.1.1-Modificar los parámetros a pasar en el HomeViewModel empleando función Lambda.
8.1.2-Corregir la navegación en el Navigator.kt desde el composable Home
8.2-Modificar la navegación desde el HomeScreen() para el onJoinGame()
8.2.1-Modificar los parámetros a pasar en el HomeViewModel empleando función Lambda.
8.2.2-Corregir la navegación en el Navigator.kt desde el composable Home
8.3.1-Corregir llamadas en HomeScreen.kt
8.3.2-Corregir llamadas en GameScreen.kt

/**************************************************************************************************/
/**************************** 9-RECUPERAMOS EL PARTIDO COMO OWNER *********************************/
/**************************************************************************************************/

9- Ahora ya tenemos todos los datos es hora de empezar a conectar las partidas y utilizar esos datos

/**************************************************************************************************/
/**************************** 10-RECUPERAMOS EL PARTIDO COMO GUEST ********************************/
/**************************************************************************************************/

10- Es momento de recuperar los datos del partido como guest o invitado. GameViewModel.kt

/**************************************************************************************************/
/************************************* 11-ASIGNAMOS TURNOS ****************************************/
/**************************************************************************************************/

11.1-Modificamos el GameModel añadiendo una variable más a la dataclass
11.2-Modificamos el GameViewModel para calcular el turno con la información que tenemos
11.3-Modificamos el GameScreen para que lo muestre en la UI

/**************************************************************************************************/
/*********************************** 12-JUGAMOS AL 3 en RAYA **************************************/
/**************************************************************************************************/

12.1-Modificamos el GameScreen para que no devuelva X solamente teniendo en cuenta el PlayerType
12.2-Modificamos el board para que devuelva los datos
12.3-Modificamos el gameViewModel pasandole una lambda para que podamos guardar los datos del jugador
que ha marcado una casilla y sus respectivas comprobaciones antes de guardar.
12.3.1-Obtenemos datos jugador 1
12.3.2-Obtenemos datos de jugador 2
12.4-Actualizamos el valor de la casilla en firebase.

/**************************************************************************************************/
/************************************ 13-AÑADIMOS GANADOR *****************************************/
/**************************************************************************************************/

13.1-Creamos una funcion IsGameWon que nos dirá las combinaciones ganadoras del tablero.
13.2-Creamos una función verifyWInner que comprobará si se cumple alguna de las posiciones establecidas
en el IsGameWon en función del tablero que se le pase.
13.3-Creamos una variable donde almacenar el jugador ganador
13.4-Llamamos a verifyWinner trás cada movimiento para verificar que nadie ha ganado.
13.5-Modificamos el Screen para que pinte un card si tenemos ganador, sino pinta e tablero.

/**************************************************************************************************/
/*************************************** 14-FIXEAMOS TURNOS ***************************************/
/**************************************************************************************************/

14- Voy a comprobar los turnos...porque algo no está bien
------GameViewModel

/**************************************************************************************************/
/**************************************** 15-DISEÑO UI HOME ***************************************/
/**************************************************************************************************/

15-HomeScreen.kt
/**************************************************************************************************/
/*************************************** 16-DISEÑO UI GAME ****************************************/
/**************************************************************************************************/
16- GameScreen.kt