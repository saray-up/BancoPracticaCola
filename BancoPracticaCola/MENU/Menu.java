package BancoPracticaCola.MENU;
import java.util.Queue;
import java.util.Scanner;

import BancoPracticaCola.ARCHIVO.Exportar;
import BancoPracticaCola.ARCHIVO.Importar;
import BancoPracticaCola.COLA.Metodos;
import BancoPracticaCola.MATRIZ.PasarPilaAMatriz;
import BancoPracticaCola.OBJETO.ObjCliente;
import BancoPracticaCola.PILA.PasarDeColaAPila;
import BancoPracticaCola.VALIDACIONES.Validaciones;

public class Menu {
    
    public static void main(String[] args) {
        Metodos m = new Metodos();
        Scanner sc = new Scanner(System.in);
        Validaciones v = new Validaciones();
        Exportar e = new Exportar();
        PasarDeColaAPila pilaTransfer = new PasarDeColaAPila();
        Importar i = new Importar();
        PasarPilaAMatriz matrizTransfer = new PasarPilaAMatriz();

        // Se utiliza una sola cola de clientes para todo el sistema
        Queue<ObjCliente> cola = i.ImportarArchivo(); 

        boolean continuar = true;
        while (continuar) {
            int opcion = m.MenuBanco(sc);
            switch (opcion) {
                case 1:
                    cola = m.LlenarCola(cola, m, sc);
                    break;
                case 2:
                    m.mostrarTurnosPendientes(cola);
                    break;
                case 3:
                    m.llamarSiguienteCliente(cola);
                    break;
                case 4:
                    m.marcarClienteAtendido(cola);
                    break;
                case 5:
                    m.cambiarClientePreferencial(cola, sc);
                    break;
                case 6:
                    m.cancelarTurno(cola, sc);
                    break;
                case 7:
                    m.buscarClientePorIdentificacion(cola, sc);
                    break;
                case 8:
                    m.consultarCantidadEsperando(cola);
                    break;
                case 9:
                    m.mostrarCantidadClientesPendientes(cola);
                    break;
                case 10:
                    int opcionAdmin = MenuAdministrativo(sc);
                    switch (opcionAdmin) {
                        case 1:
                            e.exportarArchivo(cola);
                            break;
                        case 2:
                            cola = i.ImportarArchivo();
                            break;
                        case 3:
                            pilaTransfer.colaAPila(cola);
                            break;
                        case 4:
                            pilaTransfer.MostrarPila(cola);
                            break;
                        case 5:
                            String[][] matriz = matrizTransfer.PilaAMatriz(cola);
                            System.out.println("Registros transferidos exitosamente a la matriz.");
                            break;
                        case 6:
                            String[][] matriz2 = matrizTransfer.PilaAMatriz(cola);
                            matrizTransfer.MostrarMatriz(matriz2);
                            break;
                        case 7:
                            System.out.println("Saliendo del menú administrativo.");
                            break;
                        default:
                            System.out.println("Opción inválida en el menú administrativo. Intente nuevamente.");
                    }
                    break;
                case 11:
                    System.out.println("Saliendo del programa.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

    }

    public static int MenuAdministrativo(Scanner sc) {
        System.out.println("----- Menú Administrativo -----");
        System.out.println("1) Exportar clientes a archivo.");
        System.out.println("2) Importar clientes desde archivo.");
        System.out.println("3) Transferencia de clientes de la cola a la pila.");
        System.out.println("4) Mostrar clientes en la pila.");
        System.out.println("5) Transferir registros de pila a matriz bidimensional.");
        System.out.println("6) Mostrar matriz bidimensional de clientes.");
        System.out.println("7) Salir del menú administrativo.");
        System.out.println("--------------------------------");
        return sc.nextInt();
    }
}