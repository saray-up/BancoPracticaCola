package BancoPracticaCola.MATRIZ;

import java.util.Queue;

import BancoPracticaCola.OBJETO.ObjCliente;

public class PasarPilaAMatriz {
    
    // 1. Calcula la dimensión de filas en base a la pila
    private int Dimension(Queue<ObjCliente> p) {
        int cont = 0;
        for (ObjCliente o : p) {
            cont++;
        }
        return cont;
    }

    // 2. Transfiere la pila a la matriz de cadenas
    public String[][] PilaAMatriz(Queue<ObjCliente> p) {
        int totalFilas = Dimension(p);
        if (totalFilas == 0) {
            return new String[0][0];
        }

        // 7 columnas para almacenar cada propiedad de ObjCliente
        String[][] matriz = new String[totalFilas][7];
        int i = 0;

        for (ObjCliente o : p) {
            matriz[i][0] = String.valueOf(o.getNumeroTurno());
            matriz[i][1] = String.valueOf(o.getIdentificación());
            matriz[i][2] = o.getNombre();
            matriz[i][3] = String.valueOf(o.getTipoTramite());
            matriz[i][4] = String.valueOf(o.getEdad());
            matriz[i][5] = (o.getCondicionEspecial() == 1) ? "Preferencial" : "Normal";
            matriz[i][6] = (o.getEstado() == 1) ? "En Espera" : "Atendido";
            i++;
        }

        return matriz;
    }

    // 3. Imprime la matriz bidimensional en consola
    public String MostrarMatriz(String[][] matriz) {
        if (matriz.length == 0) {
            System.out.println("No hay datos en la matriz.");
            return "No hay datos en la matriz.";
        }

        for (int i = 0; i < matriz.length; i++) {
            System.out.println("---DATOS EN LA MATRIZ---");
            System.out.println("Turno: " + matriz[i][0]);
            System.out.println("Identificación: " + matriz[i][1]);
            System.out.println("Nombre: " + matriz[i][2]);
            System.out.println("Tipo Trámite: " + matriz[i][3]);
            System.out.println("Edad: " + matriz[i][4]);
            System.out.println("Condición: " + matriz[i][5]);
            System.out.println("Estado: " + matriz[i][6]);
            System.out.println("----------------------------------------");
        }
        return "Datos mostrados correctamente.";
    }
}
