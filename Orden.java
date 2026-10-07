public class Orden {
    private int id;
    private String cliente;
    private int mesa;
    private Masas masa;
    private Toppings toppings;
    private Salsa salsa;
    private boolean pendiente;
    
    public Orden(String cliente, int mesa, Masas masa, Toppings toppings, Salsa salsa) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.masa = masa;
        this.toppings = toppings;
        this.salsa = salsa;
        this.pendiente = true;
    }
    public Orden(String cliente, Masas masa, Toppings toppings, Salsa salsa) {
        this.cliente = cliente;
        this.masa = masa;
        this.toppings = toppings;
        this.salsa = salsa;
        this.pendiente = true;
    }

    public Masas getMasa(){
        return masa;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    
    public boolean isPendiente() {
        return pendiente;
    }
    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    @Override
    public String toString() {
        String estado = pendiente ? "En preparación (Pendiente)" : "¡Lista para servir!";
        return "Orden #" + id + " | Cliente: " + cliente + " | Mesa: " + mesa + " | Estado: " + estado;
    }
}
