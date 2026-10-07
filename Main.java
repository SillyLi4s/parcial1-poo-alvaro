import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        int[] arregloOrdenes = new int[10]; 

        System.out.println("--- GESTIÓN DE PIZZERÍA (UML ESTRICTO) ---");

        while (!salir) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear pedido");
            System.out.println("2. Quitar pedido");
            System.out.println("3. Ver estado de un pedido");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre del cliente: ");
                    String cliente = scanner.nextLine();
                    
                    // Estos fallarán por ser métodos privados
                    Orden nuevaOrden = new Orden(cliente, Masas.DELGADA, Toppings.PEPPERONI, Salsa.NORMAL);
                    nuevaOrden.setId(1);
                    nuevaOrden.pendiente = true;

                    Pizza nuevaPizza = new Pizza(Masas.DELGADA, Toppings.PEPPERONI, Salsa.NORMAL, nuevaOrden);
                    Cocina cocina = new Cocina(nuevaOrden, nuevaPizza);
                    
                    String resultado = cocina.agregarOrden(arregloOrdenes, nuevaOrden);
                    System.out.println(">>> " + resultado);
                    break;

                case 2:
                    System.out.println("Procesando eliminación de pedido...");
                    Orden ordenARemover = new Orden("Dummy", Masas.ESPONJOSA, Toppings.JAMON, Salsa.BBQ);
                    Cocina cocinaRemover = new Cocina(ordenARemover, null);
                    
                    cocinaRemover.quitarOrden(arregloOrdenes, ordenARemover);
                    System.out.println(">>> Pedido eliminado exitosamente.");
                    break;

                case 3:
                    System.out.println("Consultando estado del pedido...");
                    Orden ordenEstado = new Orden("Dummy", Masas.DELGADA, Toppings.CHILEPIMIENTO, Salsa.CHEDDAR);
                    Pizza pizzaEstado = new Pizza(ordenEstado);
                    Cocina cocinaEstado = new Cocina(ordenEstado, pizzaEstado);
                    
                    boolean estado = cocinaEstado.esOrdenLista(pizzaEstado, ordenEstado, pizzaEstado);
                    if (estado) {
                        System.out.println(">>> El pedido ESTÁ LISTO.");
                    } else {
                        System.out.println(">>> El pedido AÚN NO ESTÁ LISTO.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}