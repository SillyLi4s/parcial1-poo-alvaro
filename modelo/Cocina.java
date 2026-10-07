package modelo;
import java.util.ArrayList;

public class Cocina {
    private ArrayList<Orden> ordenes;
    private static final int LIMITE_PEDIDOS = 5;

    public Cocina() {
        this.ordenes = new ArrayList<>();
    }

    public boolean agregarOrden(Orden orden) {
        if (ordenes.size() >= LIMITE_PEDIDOS) {
            System.out.println("Capacidad máxima de " + LIMITE_PEDIDOS + " pedidos alcanzada.");
            return false;
        }
        ordenes.add(orden);
        System.out.println("Orden #" + orden.getId() + " agregada exitosamente a la cocina.");
        return true;
    }

    public boolean quitarOrden(int idOrden) {
        for (int i = 0; i < ordenes.size(); i++) {
            if (ordenes.get(i).getId() == idOrden) {
                ordenes.remove(i);
                return true;
            }
        }
        return false;
    }

    public Orden buscarOrden(int idOrden) {
        for (Orden o : ordenes) {
            if (o.getId() == idOrden) {
                return o;
            }
        }
        return null;
    }

    public void mostrarPedidosActuales() {
        System.out.println("\nPedidos Actuales en Cocina (" + ordenes.size() + "/" + LIMITE_PEDIDOS + ")");
        if (ordenes.isEmpty()) {
            System.out.println("No hay pedidos activos.");
        } else {
            for (Orden o : ordenes) {
                System.out.println(o.toString());
            }
        }
    }
}

