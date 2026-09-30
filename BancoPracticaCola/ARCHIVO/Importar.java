package BancoPracticaCola.ARCHIVO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

import BancoPracticaCola.OBJETO.ObjCliente;

public class Importar {
    public Queue<ObjCliente> ImportarArchivo() {

        String rutaArchivo = "Clientes.txt";
        Queue<ObjCliente> cola = new LinkedList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            // Intenta buscarlo dentro del directorio de trabajo actual
            archivo = new File(System.getProperty("user.dir") + File.separator + rutaArchivo);
        }

        if (!archivo.exists()) {
            System.out.println("El archivo " + rutaArchivo + " no existe en la raíz del proyecto.");
            return cola;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            ObjCliente obj = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.toLowerCase().startsWith("identificación:") || linea.toLowerCase().startsWith("identificacion:")) {
                    if (obj != null) {
                        cola.add(obj);
                    }
                    obj = new ObjCliente();
                    obj.setIdentificación(Integer.parseInt(linea.substring(linea.indexOf(":") + 1).trim()));
                    obj.setNumeroTurno(cola.size() + 1);
                    obj.setEstado(1);

                } else if (linea.toLowerCase().startsWith("nombre:")) {
                    if (obj != null) {
                        obj.setNombre(linea.substring(linea.indexOf(":") + 1).trim());
                    }
                } else if (linea.toLowerCase().startsWith("tipo") && linea.contains(":")) {
                    if (obj != null) {
                        obj.setTipoTramite(Integer.parseInt(linea.substring(linea.indexOf(":") + 1).trim()));
                    }
                } else if (linea.toLowerCase().startsWith("edad:")) {
                    if (obj != null) {
                        obj.setEdad(Integer.parseInt(linea.substring(linea.indexOf(":") + 1).trim()));
                    }
                } else if (linea.toLowerCase().startsWith("condición") || linea.toLowerCase().startsWith("condicion")) {
                    if (obj != null) {
                        obj.setCondicionEspecial(Integer.parseInt(linea.substring(linea.indexOf(":") + 1).trim()));
                    }
                }
            }

            if (obj != null) {
                cola.add(obj);
            }

            System.out.println("Archivo importado correctamente. Clientes cargados: " + cola.size());

        } catch (Exception e) {
            System.out.println("Error al importar el archivo: " + e.getMessage());
        }

        return cola;
    }
}