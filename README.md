# ARSW-Lab01
### Juliana Garzon Duque
### Juan David Navarro
### Eduardo Ocampo Arellano
### Sarah Vieda Castro
## Parte I - Introducción de Threads en JAVA

1. ¿Cual es la diferencia entre start() y run()?
- Al ejecutar el metodo start() , los threads se corren al mismo tiempo y su salida es en un orden aleatorio.
- Al ejecutar el metodo run() ,los threads se corren en un orden consecutivo y su salida es en el orden de ejecución de los threads.

## Parte III
Pruebas utilizando 1 Thread
![A](https://user-images.githubusercontent.com/53972469/73075095-27f48780-3e89-11ea-8264-af0b5f26bb7d.png) 

Pruebas utilizando 200 threads 
![B](https://user-images.githubusercontent.com/53972469/73075097-29be4b00-3e89-11ea-86c7-3c08250ed516.png)

Pruebas utilizando 500 threads
![C](https://user-images.githubusercontent.com/53972469/73075099-2aef7800-3e89-11ea-8ca0-8c74052ca57a.png)

## ¿Por qué no se logra el mejor rendimiento con los 500 hilos? ¿Cómo se compara este rendimiento cuando se usan 200?

Realizando un análisis de los resultados anteriores, podemos observar que utilizar 200 hilos es mucho más eficiente que usar 500 hilos, esto se debe a que se debe guardar el estado de cada uno de los hilos y luego restaurar su valor al momento de ejecutar. 

## ¿Cómo se comporta usando tantos hilos como el doble de procesadores?

Es mucho más efectivo que utilizar la misma cantidad de hilos y de procesadores ya que se realiza un uso mas óptimo de los procesadores debido a que cada uno se hace cargo de algo de más que no le provoca una carga.

## De acuerdo a la situación descrita anteriormente, ¿se aplicaría mejor la ley de Amdahls? 

De acuerdo a esta ley, el programa será menos eficiente si se agregan mas CPUs por lo que si tenemos 500 CPUs y cada una de estas para un hilo no se estaría usando la máxima capacidad del procesador. 

La solución mas adecuada seria que al tener c hilos y ls distribuimos sobre 500 CPUs entonces se le estarían dando un mejor uso al tener que manejar esos hilos, por esta razón aplicariamos de una forma más adecuada la ley de Amdahls.




