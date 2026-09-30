package BancoPracticaCola.OBJETO;
/**
 * Identificación
Nombre
Tipo de trámite
Edad
Condición de atención especial
Número de turno
 */

public class ObjCliente {
    private int identificación;
    private String nombre; 
    private int TipoTramite;
    private int edad;
    private int condicionEspecial;
    private int numeroTurno;
    private int estado;

    

    public ObjCliente() {
    }

    public int getIdentificación() {
        return identificación;
    }

    public void setIdentificación(int identificación) {
        this.identificación = identificación;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoTramite() {
        return TipoTramite;
    }

    public void setTipoTramite(int tipoTramite) {
        TipoTramite = tipoTramite;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(int condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }

    public void setNumeroTurno(int numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    

}
