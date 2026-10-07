
import java.util.Scanner;

import modelo.Cocina;
import modelo.Masas;
import modelo.Orden;
import modelo.Pizza;
import modelo.Salsa;
import modelo.Toppings;

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
                    System.out.print("Tipo de Masa (Delgada = 1 | Esponjosa = 2): ");
                    int sc_masa = scanner.nextInt();
                    System.out.print("¿Qué topping desea? (Pepperoni = 1 | Jamón = 2 | Chile Pimiento = 3 | Tocino = 4): ");
                    int sc_topping  = scanner.nextInt();
                    System.out.print("Tipo de Salsa (Normal = 1 | Picante = 2 | Cheddar = 3 | BBQ = 4): ");
                    int sc_salsa  = scanner.nextInt();

                    Orden nuevaOrden = new Orden(cliente, mesa, Masas.DELGADA, Toppings.PEPPERONI, Salsa.NORMAL);
                    nuevaOrden.setId(contadorIds);

                    if (sc_masa == 2) {
                        nuevaOrden.setMasa(Masas.ESPONJOSA);
                    } else {
                        nuevaOrden.setMasa(Masas.DELGADA);
                    }

                    if (sc_topping == 2) {
                        nuevaOrden.setToppings(Toppings.JAMON);
                    } else if (sc_topping == 3) {
                        nuevaOrden.setToppings(Toppings.CHILEPIMIENTO);
                    } else if (sc_topping == 4) {
                        nuevaOrden.setToppings(Toppings.TOCINO);
                    } else {
                        nuevaOrden.setToppings(Toppings.PEPPERONI);
                    }

                    if (sc_salsa == 2) {
                        nuevaOrden.setSalsa(Salsa.PICANTE);
                    } else if (sc_salsa == 3) {
                        nuevaOrden.setSalsa(Salsa.CHEDDAR);
                    } else if (sc_salsa == 4) {
                        nuevaOrden.setSalsa(Salsa.BBQ);
                    } else {
                        nuevaOrden.setSalsa(Salsa.NORMAL);
                    }


                    
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
                        
                        System.out.println("🍕 Detalles de la Pizza:");
                        System.out.println("   - Masa: " + ordenEncontrada.getMasa());
                        System.out.println("   - Topping: " + ordenEncontrada.getToppings());
                        System.out.println("   - Salsa: " + ordenEncontrada.getSalsa());

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