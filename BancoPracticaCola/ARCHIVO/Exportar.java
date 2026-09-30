package BancoPracticaCola.ARCHIVO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

import BancoPracticaCola.OBJETO.ObjCliente;

public class Exportar {
    public void exportarArchivo(Queue<ObjCliente> cola) {
        if (cola.isEmpty()) {
            System.out.println("La cola está vacía, no se puede exportar el archivo.");
            return;
        }

        try (FileWriter writer = new FileWriter("Clientes.txt")) {
            for (ObjCliente cliente : cola) {
                writer.write("Identificación: " + cliente.getIdentificación() + "\n");
                writer.write("Nombre: " + cliente.getNombre() + "\n");
                writer.write("TipoTramite: " + cliente.getTipoTramite() + "\n");
                writer.write("Edad: " + cliente.getEdad() + "\n");
                writer.write("CondicionEspecial: " + cliente.getCondicionEspecial() + "\n");
                writer.write("NumeroTurno: " + cliente.getNumeroTurno() + "\n");
                writer.write("Estado: " + cliente.getEstado() + "\n");
                writer.write("------------------------------------------------------\n");
            }
            System.out.println("Archivo 'Clientes.txt' exportado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al exportar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
