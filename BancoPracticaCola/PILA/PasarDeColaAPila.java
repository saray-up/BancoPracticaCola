package BancoPracticaCola.PILA;

import java.util.Queue;
import java.util.Stack;
import BancoPracticaCola.OBJETO.ObjCliente;

public class PasarDeColaAPila {

    private Stack<ObjCliente> pila = new Stack<>();

    public Stack<ObjCliente> colaAPila(Queue<ObjCliente> cola) {
        if (cola == null || cola.isEmpty()) {
            System.out.println("La cola está vacía. No se puede transferir a la pila.");
            return pila;
        }

        pila.clear(); // Limpia la pila anterior si existe

        // Iterar la cola sin eliminar sus elementos
        for (ObjCliente cliente : cola) {
            pila.push(cliente);
        }

        System.out.println("Se transfirieron " + pila.size() + " clientes de la cola a la pila con éxito.");
        return pila;
    }

    public String MostrarPila(Queue<ObjCliente> cola) {
        if (pila == null || pila.isEmpty()) {
            System.out.println("La pila está vacía. Ejecute primero la opción 3 para transferir.");
            return "La pila está vacía.";
        }

        System.out.println("\n--- ELEMENTOS EN LA PILA ---");
        for (int i = pila.size() - 1; i >= 0; i--) {
            ObjCliente c = pila.get(i);
            System.out.println("ID: " + c.getIdentificación() + " | Nombre: " + c.getNombre() + " | Turno: " + c.getNumeroTurno());
        }
        return "Elementos mostrados correctamente.";
    }
    

    public Stack<ObjCliente> getPila() {
        return pila;
    }

}