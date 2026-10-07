public class Pizza {
    private Masas masa;
    private Toppings toppings;
    private Salsa salsa;
    private Orden idOrden;
    private boolean lista;

    private Pizza(Masas masa, Toppings toppings, Salsa salsa, Orden idOrden) {
        this.masa = masa;
        this.toppings = toppings;
        this.salsa = salsa;
        this.idOrden = idOrden;
    }

    private Pizza(Masas masa, Salsa salsa, Orden idOrden) {
        this.masa = masa;
        this.salsa = salsa;
        this.idOrden = idOrden;
    }

    private Pizza(Orden idOrden) {
        this.idOrden = idOrden;
    }

    public boolean estaLista() {
        return this.lista;
    }
}
