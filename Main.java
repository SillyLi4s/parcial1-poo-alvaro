import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cocina cocina = new Cocina();
        int contadorIds = 1;
        boolean salir = false;

        System.out.println("🍕 PIZZERÍA 🍕");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear pedido");
            System.out.println("2. Quitar pedido");
            System.out.println("3. Ver estado de un pedido");
            System.out.println("4. Ver todos los pedidos en cocina");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- NUEVO PEDIDO ---");
                    System.out.print("Nombre del cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Número de mesa: ");
                    int mesa = scanner.nextInt();
                    
                    Orden nuevaOrden = new Orden(cliente, mesa, Masas.DELGADA, Toppings.PEPPERONI, Salsa.NORMAL);
                    nuevaOrden.setId(contadorIds);
                    
                    if (cocina.agregarOrden(nuevaOrden)) {
                        contadorIds++; // incrementar solo si se puede
                    }
                    break;

                case 2:
                    System.out.print("\nIngrese el ID de la orden que desea quitar: ");
                    int idQuitar = scanner.nextInt();
                    if (cocina.quitarOrden(idQuitar)) {
                        System.out.println("Pedido #" + idQuitar + " eliminado correctamente de la cocina.");
                    } else {
                        System.out.println("No se encontró ninguna orden con el ID #" + idQuitar);
                    }
                    break;

                case 3:
                    System.out.print("\nIngrese el ID de la orden a consultar: ");
                    int idEstado = scanner.nextInt();
                    Orden ordenEncontrada = cocina.buscarOrden(idEstado);
                    
                    if (ordenEncontrada != null) {
                        System.out.println("\nESTADO DE LA ORDEN:");
                        System.out.println(ordenEncontrada.toString());
                        
                        // Pequeña opción extra para marcar la pizza como lista
                        if (ordenEncontrada.isPendiente()) {
                            System.out.print("¿Desea marcar este pedido como LISTO? (S/N): ");
                            String resp = scanner.next();
                            if (resp.equalsIgnoreCase("s")) {
                                Pizza p = new Pizza(ordenEncontrada);
                                p.marcarComoLista();
                                System.out.println("El pedido esta listo para servir");
                            }
                        }
                    } else {
                        System.out.println("No se encontró ninguna orden con el ID #" + idEstado);
                    }
                    break;

                case 4:
                    cocina.mostrarPedidosActuales();
                    break;

                case 5:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}