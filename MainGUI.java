import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import modelo.Cocina;
import modelo.Masas;
import modelo.Orden;
import modelo.Pizza;
import modelo.Salsa;
import modelo.Toppings;

public class MainGUI {
    public static void main(String[] args) {
        Cocina cocina = new Cocina();
        int[] contadorIds = {1};
        ArrayList<Integer> idsActivos = new ArrayList<>();

        JFrame frame = new JFrame("Pizzeria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel campos = new JPanel(new GridLayout(6, 2, 5, 5));
        campos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField nombre = new JTextField();
        JTextField mesa = new JTextField();
        JComboBox<Masas> masaBox = new JComboBox<>(Masas.values());
        JComboBox<Toppings> toppingBox = new JComboBox<>(Toppings.values());
        JComboBox<Salsa> salsaBox = new JComboBox<>(Salsa.values());

        campos.add(new JLabel("Nombre:"));
        campos.add(nombre);
        campos.add(new JLabel("Mesa:"));
        campos.add(mesa);
        campos.add(new JLabel("Masa:"));
        campos.add(masaBox);
        campos.add(new JLabel("Topping:"));
        campos.add(toppingBox);
        campos.add(new JLabel("Salsa:"));
        campos.add(salsaBox);

        JButton btnCrear = new JButton("Crear Pedido");
        campos.add(new JLabel(""));
        campos.add(btnCrear);

        JPanel acciones = new JPanel(new FlowLayout());
        JTextField idEntrada = new JTextField(5);
        JButton btnQuitar = new JButton("Quitar");
        JButton btnEstado = new JButton("Estado y Listo");
        JButton btnVerTodos = new JButton("Ver Todos");

        acciones.add(new JLabel("ID:"));
        acciones.add(idEntrada);
        acciones.add(btnQuitar);
        acciones.add(btnEstado);
        acciones.add(btnVerTodos);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(campos, BorderLayout.NORTH);
        panelSuperior.add(acciones, BorderLayout.CENTER);

        JTextArea salida = new JTextArea(10, 45);
        salida.setEditable(false);

        btnCrear.addActionListener(e -> {
            if (idsActivos.size() >= 5) {
                salida.setText("Capacidad maxima de pedidos alcanzada.");
                return;
            }
            
            String n = nombre.getText().trim();
            String m = mesa.getText().trim();
            
            if (n.isEmpty() || m.isEmpty()) {
                salida.setText("Llene el campo de nombre y mesa.");
                return;
            }
            
            try {
                int numMesa = Integer.parseInt(m);
                Masas masa = (Masas) masaBox.getSelectedItem();
                Toppings topping = (Toppings) toppingBox.getSelectedItem();
                Salsa salsa = (Salsa) salsaBox.getSelectedItem();

                Orden nuevaOrden = new Orden(n, numMesa, masa, topping, salsa);
                nuevaOrden.setId(contadorIds[0]);

                if (cocina.agregarOrden(nuevaOrden)) {
                    idsActivos.add(contadorIds[0]);
                    salida.setText("Orden #" + contadorIds[0] + " creada exitosamente.");
                    contadorIds[0]++;
                }
            } catch (Exception ex) {
                salida.setText("Ingrese un numero en el campo de mesa.");
            }
        });

        btnQuitar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idEntrada.getText().trim());
                if (cocina.quitarOrden(id)) {
                    idsActivos.remove(Integer.valueOf(id));
                    salida.setText("Pedido #" + id + " eliminado correctamente.");
                } else {
                    salida.setText("No se encontro ninguna orden con el ID #" + id);
                }
            } catch (Exception ex) {
                salida.setText("Ingrese un ID valido en el campo ID.");
            }
        });

        btnEstado.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idEntrada.getText().trim());
                Orden o = cocina.buscarOrden(id);
                if (o != null) {
                    if (o.isPendiente()) {
                        Pizza p = new Pizza(o);
                        p.marcarComoLista();
                        salida.setText("ESTADO DE LA ORDEN:\n" + o.toString() + "\n\nDetalles de la Pizza:\n- Masa: " + o.getMasa() + "\n- Topping: " + o.getToppings() + "\n- Salsa: " + o.getSalsa() + "\n\nEl pedido se ha marcado como listo para servir.");
                    } else {
                        salida.setText("ESTADO DE LA ORDEN:\n" + o.toString() + "\n\nDetalles de la Pizza:\n- Masa: " + o.getMasa() + "\n- Topping: " + o.getToppings() + "\n- Salsa: " + o.getSalsa());
                    }
                } else {
                    salida.setText("No se encontro ninguna orden con el ID #" + id);
                }
            } catch (Exception ex) {
                salida.setText("Ingrese un ID valido en el campo ID.");
            }
        });

        btnVerTodos.addActionListener(e -> {
            String texto = "Pedidos Actuales en Cocina (" + idsActivos.size() + "/5)\n\n";
            if (idsActivos.isEmpty()) {
                texto += "No hay pedidos activos.";
            } else {
                for (Integer id : idsActivos) {
                    Orden o = cocina.buscarOrden(id);
                    texto += o.toString() + "\n";
                }
            }
            salida.setText(texto);
        });

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(new JScrollPane(salida), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}