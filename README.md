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



### TODO
 * Posible refactor de ProcesarFacturacion y ProcesarCancelacion

