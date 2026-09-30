package BancoPracticaCola.COLA;

import java.util.Queue;
import java.util.Scanner;

import BancoPracticaCola.OBJETO.ObjCliente;

public class Metodos {
    
    public Queue<ObjCliente> LlenarCola(Queue<ObjCliente> cola, Metodos m, Scanner sc) {
        boolean continuar = true;

        while (continuar) {
            ObjCliente o = new ObjCliente();
            o.setNumeroTurno(m.ValidarTurno(cola));
            System.out.println("Ingrese número de identificación: ");
            o.setIdentificación(sc.nextInt());
            sc.nextLine();
            System.out.println("Ingrese nombre completo:  ");
            o.setNombre(sc.nextLine());
            System.out.print("Ingrese Edad:  ");
            o.setEdad(sc.nextInt());
            sc.nextLine();
            System.out.println("--------------------------------");
            System.out.println("Ingrese el tramite que desea realizar: ");
            o.setTipoTramite(m.MenuTramites(sc));
            System.out.println("Tiene una condición especial? 1) si/ 2) no:  ");
            int condicion = sc.nextInt();
            o.setCondicionEspecial(condicion);
            o.setEstado(1);

            if (condicion == 1 && !cola.isEmpty()) {
            int tamanioInicial = cola.size();
            // Insertamos el cliente preferencial de primero
            cola.offer(o); 
            // Rotamos los clientes anteriores hacia el final para que el nuevo quede al principio
            for (int i = 0; i < tamanioInicial; i++) {
                cola.offer(cola.poll());
            }
            } else {
            // Si es normal (2) o la cola estaba vacía, simplemente va al final
                cola.offer(o);
            }

            System.out.println("Desea Agregar mas clientes? 1 si , 2 no ");
            int opt = sc.nextInt();
            if (opt == 2) {
                System.out.println("Vuelve Pronto");
                continuar = false;
            }
        }
        return cola;

    }

    public int ValidarTurno(Queue<ObjCliente> cola) {
        int turno = 0;
        if (cola.isEmpty()) {
            turno = 1;
        } else {
            turno = cola.size() + 1;
        }
        return turno;
    }

    public int TienecondicionEspecial(Queue<ObjCliente> cola, int numeroTurno){
        int condicionEspecial=0;
        if(condicionEspecial==1){
            System.out.println("El cliente tiene una condición especial");
            numeroTurno=1;
        }
        return condicionEspecial;
    }

    public String mostrarTurnosPendientes(Queue<ObjCliente> cola) {
    if (cola.isEmpty()) {
        System.out.println("No hay turnos pendientes.");
        return "Sin clientes en cola.";

    }

    System.out.println("CLIENTES PENDIENTES:");
    for (ObjCliente cliente : cola) {
        if (cliente.getEstado() == 1) {
            imprimirCliente(cliente);
        }
    }
    return "Datos mostrados correctamente";

    }

    private void imprimirCliente(ObjCliente cliente) {
        System.out.println("Turno: " + cliente.getNumeroTurno());
        System.out.println("Identificación: " + cliente.getIdentificación());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Tipo de trámite: " + cliente.getTipoTramite());
        System.out.println("Edad: " + cliente.getEdad());
        System.out.println("Condición especial: " + cliente.getCondicionEspecial());
        System.out.println("--------------------------------");
    }

    public Queue<ObjCliente> llamarSiguienteCliente(Queue<ObjCliente> cola) {
        for (ObjCliente o : cola) {
            if (o.getEstado() == 1) {
                System.out.println("El siguiente turno es " + o.getNumeroTurno() + " con el nombre de : " + o.getNombre());
                o.setEstado(2);
                break;
            }
        }
        System.out.println("Turno atendido correctamente ");
        System.out.println("--------------------------------");
        return cola;
    }

    public Queue<ObjCliente> marcarClienteAtendido(Queue<ObjCliente> cola) {
        for (ObjCliente o : cola) {
            if (o.getEstado() == 2) {
                o.setEstado(3);
                System.out.println("Cliente marcado como atendido.");
                break;
            }
        }
        return cola;
    }

    public Queue<ObjCliente> cambiarClientePreferencial(Queue<ObjCliente> cola, Scanner sc) {
        System.out.println("Ingrese el número de turno del cliente que desea cambiar a preferencial: ");
        int turno = sc.nextInt();
        for (ObjCliente o : cola) {
            if (o.getNumeroTurno() == turno) {
                o.setCondicionEspecial(1);
                System.out.println("Cliente cambiado a preferencial correctamente.");
                System.out.println("--------------------------------");
                break;
            }
        }
        return cola;
    }

    public Queue<ObjCliente> cancelarTurno(Queue<ObjCliente> cola, Scanner sc) {
        System.out.println("Ingrese el número de turno del cliente que desea cancelar: ");
        int turno = sc.nextInt();
        boolean encontrado = false;

        for (ObjCliente o : cola) {
            if (o.getNumeroTurno() == turno) {
                encontrado = true;
                if (o.getEstado() == 3) { // 3: Atendido
                    System.out.println("Error: El cliente ya fue atendido y no se puede cancelar.");
                } else if (o.getEstado() == 2) { // 2: Siendo atendido
                    System.out.println("Error: El cliente está en ventanilla en este momento.");
                } else if (o.getEstado() == 4) { // 4: Ya cancelado
                    System.out.println("El turno ya se encontraba cancelado.");
                } else {
                    o.setEstado(4); // Se marca como cancelado solo si estaba en espera (1)
                    System.out.println("Turno cancelado correctamente.");
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún cliente con ese número de turno.");
        }
        return cola;
    }

    public void buscarClientePorIdentificacion(Queue<ObjCliente> cola, Scanner sc) {
        System.out.println("Ingrese el número de identificación del cliente que desea buscar: ");
        int identificacion = sc.nextInt();
        boolean encontrado = false;
        for (ObjCliente o : cola) {
            if (o.getIdentificación() == identificacion) {
                System.out.println("Cliente encontrado:");
                System.out.println("Turno: " + o.getNumeroTurno());
                System.out.println("Nombre: " + o.getNombre());
                System.out.println("Tipo de trámite: " + o.getTipoTramite());
                System.out.println("Edad: " + o.getEdad());
                System.out.println("Condición especial: " + o.getCondicionEspecial());
                System.out.println("--------------------------------");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void consultarCantidadEsperando(Queue<ObjCliente> cola) {
        int cantidad = 0;
        for (ObjCliente o : cola) {
            if (o.getEstado() == 1) {
                cantidad++;
            }
        }
        System.out.println("Cantidad de personas esperando: " + cantidad);
    }

    public void mostrarCantidadClientesPendientes(Queue<ObjCliente> cola) {
        int cantidadNormales = 0;
        int cantidadPreferenciales = 0;
        for (ObjCliente o : cola) {
            if (o.getEstado() == 1) {
                if (o.getCondicionEspecial() == 1) {
                    cantidadPreferenciales++;
                } else {
                    cantidadNormales++;
                }
            }
        }
        System.out.println("Cantidad de clientes normales pendientes: " + cantidadNormales);
        System.out.println("Cantidad de clientes preferenciales pendientes: " + cantidadPreferenciales);
    }

    


    public int MenuTramites(Scanner sc) {
        System.out.println("\n");
        System.out.println("1) Abrir una cuenta de ahorros.");
        System.out.println("2) Cerrar una cuenta de ahorros.");
        System.out.println("3) Consultar el saldo de una cuenta.");
        System.out.println("4) Realizar un depósito.");
        System.out.println("5) Realizar un retiro.");
        System.out.println("6) Transferir fondos entre cuentas.");
        System.out.println("7) Solicitar un préstamo.");
        System.out.println("8) Pagar un préstamo.");
        System.out.println("--------------------------------");
        return sc.nextInt();
    }

    public int MenuBanco(Scanner sc) {
        System.out.println("Bienvenido al banco de Saray");
        System.out.println("1) Registrar un cliente.");
        System.out.println("2) Consultar los clientes que están esperando.");
        System.out.println("3) Llamar al siguiente cliente.");
        System.out.println("4) Marcar un cliente como atendido.");
        System.out.println("5) Cambiar un cliente de atención normal a preferencial cuando presente la documentación correspondiente.");
        System.out.println("6) Cancelar un turno. ");
        System.out.println("7) Buscar un cliente por identificación. ");
        System.out.println("8) Consultar cuántas personas están esperando.");
        System.out.println("9) Mostrar cuántos clientes normales y preferenciales están pendientes.");
        System.out.println("10)Otra opción administrativa. ");
        System.out.println("11) Salir del programa.");
        System.out.println("--------------------------------");
        return sc.nextInt();
        }

    
}
