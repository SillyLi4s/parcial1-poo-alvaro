public class Orden {
    private int id;
    private String cliente;
    private int mesa;
    private Masas masa;
    private Toppings toppings;
    private Salsa salsa;
    public Boolean pendiente = false;
    
    private Orden(String cliente, Masas masa, Toppings toppings, Salsa salsa) {
        this.cliente = cliente;
        this.masa = masa;
        this.toppings = toppings;
        this.salsa = salsa;
    }

    private Orden(String cliente, int mesa, Masas masa, Toppings toppings, Salsa salsa) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.masa = masa;
        this.toppings = toppings;
        this.salsa = salsa;
    }

    // getters
    public int getId() {
         return id;
        }
    public String getCliente() {
        return cliente;

    }
    public int getMesa() {
        return mesa;

    }
    public Masas getMasa() {
        return masa;

    }
    public Toppings getToppings() {
        return toppings;

    }
    public Salsa getSalsa() {
        return salsa;

    }

    
    // set id
    public void setId(int id) { 
        this.id = id; 
    }
}
