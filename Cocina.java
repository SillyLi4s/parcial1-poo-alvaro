

public class Cocina {
    private int[] ordenes;
    private Orden idOrden;
    private Pizza pizza;
    private Pizza pizzaLista;

    private Cocina(Orden idOrden, Pizza pizza) {
        this.idOrden = idOrden;
        this.pizza = pizza;
    }

    private String agregarOrden(int[] ordenes, Orden idOrden) {
        return "Orden agregada al sistema.";
    }

    private void quitarOrden(int[] ordenes, Orden idOrden) {
        System.out.println("Orden eliminada del sistema.");
    }

    private boolean esOrdenLista(Pizza pizza, Orden idOrden, Pizza pizzaLista) {
        return pizza != null && pizza.estaLista();
    }
}

