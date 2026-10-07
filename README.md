# parcial1-poo-alvaro
Modelo de un juego sobre ensamblar pizzas según especificaciones.

# Participantes
- Alvaro Elias Flores Pardo, 261868

## Análisis de Cambios Realizados

### Visibilidad de constructores y métodos (Modificadores de acceso)
- **Error original:** En el diagrama UML, los constructores de las clases `Orden`, `Pizza` y `Cocina`, así como todos los métodos operativos de `Cocina`, estaban marcados como privados.
- **Por qué se cambió:** Si un constructor es privado, ninguna otra clase puede crear instancias de esa clase ni interactuar con sus métodos.

### Estructura de datos para almacenar pedidos en Cocina
- **Error original:** El diagrama indicaba el atributo `ordenes[]: int` para registrar los pedidos. Esto asume que solo se guardan números sueltos y no los detalles del pedido.
- **Por qué se cambió:** Se reemplazó por un `ArrayList<Orden>`. Al usar una lista dinámica de objetos `Orden`, la cocina puede acceder directamente al cliente, la mesa y el estado de la comida. Además, usar una colección facilitó establecer y validar el límite estricto de 5 pedidos simultáneos requeridos.

### Firmas y parámetros redundantes en los métodos de Cocina
- **Error original:** Métodos como `agregarOrden(int ordenes[], Orden idOrden)` obligaban a pasar el arreglo de órdenes como parámetro externo cada vez. Otro caso era `esOrdenLista(Pizza pizza, Orden idOrden, Pizza pizzaLista)`, el cual exigía tres parámetros para una verificación simple.
- **Por qué se cambió:** Se aplicó el principio de encapsulamiento. La clase `Cocina` ahora administra su propia lista internamente, por lo que `agregarOrden(Orden orden)` solo necesita recibir el pedido nuevo. Los métodos de validación se simplificaron para usar la lógica interna de los objetos sin requerir parámetros redundantes.

### Sincronización del estado entre Pizza y Orden
- **Error original:** El UML poseía un booleano `pendiente` en `Orden` y un booleano `lista` en `Pizza`, pero ningún método los conectaba de forma lógica.
- **Por qué se cambió:** Se creó el método `marcarComoLista()` dentro de la clase `Pizza`. Esto centraliza el comportamiento: al marcar la pizza como terminada, el código cambia automáticamente el atributo `pendiente` de la `Orden` a `false`. Esto evita tener datos contradictorios (por ejemplo, una pizza que diga estar terminada pero cuya orden siga pendiente).

### Sobrecarga innecesaria de constructores en Pizza y Orden
- **Error original:** La clase `Pizza` exigía crear objetos pasando manualmente todos los ingredientes (masas, salsas, toppings) aunque estos ya existieran dentro del objeto `Orden` que se le estaba pasando como parámetro.
- **Por qué se cambió:** Se optimizó dejando un solo constructor principal para `Pizza` que recibe una `Orden`. A partir de ese objeto, la pizza extrae automáticamente qué masa y qué ingredientes necesita, evitando duplicidad de código y posibles errores de digitación al crear los objetos.