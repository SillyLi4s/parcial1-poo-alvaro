package modelo;

public class Pizza {
    private Masas masa;
    private Toppings toppings;
    private Salsa salsa;
    private Orden ordenAsociada;
    private boolean lista;

    public Pizza(Orden ordenAsociada) {
        this.ordenAsociada = ordenAsociada;
        this.masa = ordenAsociada.getMasa();
        this.salsa = ordenAsociada.getSalsa();
        this.toppings = ordenAsociada.getToppings();
        this.lista = false;
    }

    public void marcarComoLista() {
        this.lista = true;
        this.ordenAsociada.setPendiente(false);
    }

    public boolean estaLista() {
        return this.lista;
    }
}
