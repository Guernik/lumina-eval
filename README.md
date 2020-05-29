# Lumina Americas
[![Build Status](https://travis-ci.com/Guernik/lumina-eval.svg?branch=master)](https://travis-ci.com/Guernik/lumina-eval)
## Evaluación a candidatos

### Dudas sobre el diseño a "consultar con el cliente"
Las siguientes son una serie de dudas que fueron surgiendo durante el diseño, normalmente se consultarían con el cliente o product owner.
* El archivo a enviar a ARBA no tiene forma de identificar si es factura o nota de crédito. Momentaneamente, las notas de credito se ingresan como valores negativos, pero seguramnete se debería consultar si es necesario el agregado de una columna
* En el mismo archivo, "Cliente" se asume como numero de cliente, pero habría que consultarlo
* En el detalle de la factura, no queda claro qué quieren representar con "Producto", probablemente sería el nombre y código. También habría que consultar
* Para el requerimiento 1.b _"Recibir una lista de facturas a anular y Realizar la cancelación de pedidos"_ se asume que lo que se pide es generar una lista de notas de crédito, (ya que lo que está ingresando son facturas, caso contrario ingresarian pedidos a cancelar).
* No especifican como nombrar al archivo de texto


### Respuestas a las pregutnas teóricas
1. ¿A que nos referimos cuando decimos que ocurrió un error de NullPointerException?  
> Es una _runtime exception_, es decir una exepcion no checkeada, que lanza la JVM al intentar acceder a una referencia nula, (un objeto que contenga NULL)

2. ¿En qué casos usaría un foreach y en casos usaría un While para recorrer un arreglo?
> El _forEach_ tiene cierto overhead, por lo que evitaría utilizarlo en bucles críticos
3. ¿En java que es un HashMap? ¿Qué métodos debería sobre escribir para que funcione?
> Es una estructura de datos, no ordenada, parte del framework de _Collections_ de java, de tipo "key-value". Si van a almacenarce objetos, debe sobre escrivbirse el metodo hashCode() y consecuentemente el equals()
4. ¿Cuál es la ventaja que brinda de usar el patrón singleton en un sistema?
> No creo que brinde ninguna ventaje perse, es una herramienta que debe utilizase si es apropiado. Esto es cuando se requiere que exista una única instancia de un determinado objeto en todo el ciclo de vida de la aplicación.
Muchos singletons en un sistema suelen ser indicios de un mal diseño.
5. ¿Sabiendo que el Nro de factura es único e irrepetible como manejaría el numerador en un sistema multi hilo o en una aplicación multi usuarios?
> Hay multiples alternativas, la primera sería justamente con un singleton (esto es de alguna manera lo que está hecho en el ejercicio, mediante la variable estática __billing_number_).  
Otra opcion es generar un identificador del tipo UUID, unicos e irrepetibles, pero para el caso de un numero de factura sería dificil de leer por el usuario.  
Tambien se podría generar a partir de la cantidad de milisegundos desde el _epoch time_, o desde que se inició el sistema, más algún numero aleatorio en caso de que se generen multiples facturas en un lapso menor a un milisegundo.
6. ¿Cuál es la diferencia entre un patrón strategy y un patrón state? Brinde un ejemplo
> El patrón _strategy_ sirve para intercambiar algoritmos de forma polimórfica. Por ejemplo, podría utilizarse para elegir un cierto algoritmo de serialización por sobre otro dependiendo las condiciones del sistema (quizá se necesita priorizar la velocidad por sobre el tamaño que se va a transmitir, etc.,). A cualquier sitación que requiera distintos algoritmos según el contexto, se le puede aplicar el patrón strategy.  
El patrón _state_ se utiliza para alterar el comportamiento de un determinado objeto, según su estado. Por ejemplo, una conexión TCP pasa por diferentes estados, para los cuales debe comportarse diferente.
7. ¿Qué es un servidor de aplicaciones?
> Es basicamente una aplicación que provee acceso a otras aplicaciones. Por ejemplo, un tomcat
8. ¿Qué es un repositorio de código y que beneficios brinda a un equipo el contar con esta herramienta?
> Es una herramienta que permite gestionar el código de una organización, equipo, o proyecto de forma efectiva y eficiente, permitiendo el trabajo en equipo, y proveyendo de una historia a la que se puede volver en caso de existir problemas. Es fundamental en cualqueir proyecto de software.
9. ¿Cuál es la ventaja/desventaja de un sistema web vs un sistema stand alone cliente servidor?
> (Vale la aclaración, un sistema web _es_ un sistema cliente servidor, donde el cliente es el browser)
   * ventajas:    
     * la aplicación tiene bajo nivel de acomplamiento al sistema operativo;
     * permite realizar ports en menor tiempo;
     * si está bien diseñado puede ser más fácil de escalar.
   * desventajas:
     * Hay que brindar soporte a un gran número de browsers diferentes
     * Son menos performantes que una solución nativa
     * se está sujeto a los constantes cambios en el ecosistema web y de browsers
    
 



### TODO
 * Posible refactor de ProcesarFacturacion y ProcesarCancelacion

