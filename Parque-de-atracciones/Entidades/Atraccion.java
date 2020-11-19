package Entidades;

import java.util.HashSet;
/**
 * Describe una clase que engloba a todos los tipos de atracciones
 */
public class Atraccion
{
    /**
     * Tipo enumerado que define las claves para los tipos de atracción
     */
    public enum Tipo {
        A, B, C, D, E
    }
    
    private Tipo tipo;
    private String nombre;
    private HashSet<GrupoEdad> publico;
    private double minAlt;
    private double maxAlt;
    private boolean vip;
    private Responsable responsable;
    private int ayudantes;
    private int visitantesMontados;
    private boolean estado;
    
    /**
     * Constructor para objetos de la clase Atracción en estado inactivo y sin trabajadores asignados, especificando
     * parámetros el resto de sus atributos. Se utiliza para crear los modelos de referencia de cada tipo de atracción.
     * @param tipo Tipo de atracción.
     * @param publico Grupos de edad a los que va dirigida la atracción. 
     * @param minAlt Restricción de altura mínima (0.0 si no hay restricción).
     * @param maxAlt Restricción de altura máxima (0.0 si no hay restricción).
     * @param Vip Boolean que será true si la atracción acepta suplemento Vip.
     * @param ayudantes Número de ayudantes de atracción que necesita la atracción para funcionar.
     */
    public Atraccion(Tipo tipo,HashSet<GrupoEdad> publico, double minAlt, double maxAlt, boolean vip, int ayudantes) {
       this.tipo = tipo;
       nombre = null;
       this.publico = publico;
       this.minAlt = minAlt;
       this.maxAlt = maxAlt;
       this.vip = vip;
       this.responsable = null;
       this.ayudantes = ayudantes;
       visitantesMontados = 0;
       estado = false;
    }
    
    /**
     * Constructor que crea objetos de la clase Entidades.Atraccion según los ajustes especificados en la práctica para cada tipo de atracción.
     * @param tipo Tipo de atracción.
     * @param nombre Nombre que se asigna a la atracción.
     */
    public Atraccion(Tipo tipo, String nombre) {
       this.tipo = tipo;
       this.nombre = nombre;
       Atraccion aux = Ajustes.getAtracciones().get(tipo);
       publico = aux.getPublico();
       minAlt = aux.getMinAlt();
       maxAlt = aux.getMaxAlt();
       vip = aux.isVip();
       ayudantes = aux.getNumAyudantes();
       responsable = new Responsable((Integer)ayudantes);
       visitantesMontados = 0;
       estado = true;
    }
    
    /**
     * Getter para el atributo tipo.
     * @return Claves correspondiente según el tipo de la atracción.
     */
    public Tipo getTipo() {
        return tipo;
    }
    
    /**
     * Getter para el atributo nombre.
     * @return String con el nombre de la atracción.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Getter para el atributo publico.
     * @return HashSet con las claves del publico al que esta dirigida la atracción.
     */
    public HashSet<GrupoEdad> getPublico() {
        return publico;
    }

    /**
     * Getter para el atributo de la altura mínima.
     * @return Altura mínima para montar en la atracción.
     */
    public double getMinAlt() {
        return minAlt;
    }
    
    /**
     * Getter para el atributo de la altura máxima.
     * @return Altura máxima para montar en la atracción.
     */
    public double getMaxAlt() {
        return maxAlt;
    }    
    
    /**
     * Getter para el atributo vip.
     * @return true si la atraciión acepta pase Vip
     */
    public boolean isVip() {
        return vip;
    }    
    
    /**
     * Getter para el atributo ayudantes.
     * @return int con los ayudantes que trabajan en la atracción.
     */
    public int getNumAyudantes() {
        return ayudantes;
    }    
    
    /**
     * Getter para el atributo responsable.
     * @return int con los responsables de atracción necesarios para hacer funcionar la atracción.
     */
    public Responsable getResponsable() {
        return responsable;
    }    
    
    /**
     * Getter para el atributo visitantesMontados.
     * @return número de visitantes que han montado en la atracción.
     */
    public int getVisitantesMontados() {
        return visitantesMontados;
    }
    
    /**
     * Getter para el atributo estado.
     * @return true si la atracción esta activa este año
     */
    public boolean getEstado() {
        return estado;
    }
    
    /**
     * Método que comprueba si un visitante puede montar en la atracción de acuerdo al publico al que va dirigida y lo anota en el campo correspondiente.
     * @param Visitante que quiere montar en la atracción.
     */
    public boolean montar(Visitante visitante) {
        if (this.estado == false) {  //comprobar si la atracción está activa.
            return false;
        }
        if (publico.contains(visitante.getGrupoEdad())) {
            visitantesMontados++;
            return true;
        }
        return false;
    }
    
    /**
     * Setter que busca un responsable por su número de trabajador y lo asigna a la atracción con sus respectivos ayudantes.
     * @param número de trabajador a asignar a la atracción.
     */
    public boolean setResponsable(int numTrab) {        
        for (Trabajador trabajador: RegistroPersonal.getRegistro().get(Trabajador.Perfil.RESPONSABLE)) {
            if (trabajador.getNumTrab() == numTrab) {
                this.responsable = (Responsable) trabajador;
                return true;
            }
        }
        return false;
    }

    /**
     * Setter para el atributo estado.
     * @param true si la atracción estará activa este año
     */
    public void setEstado(boolean estado) {
        if (this.estado == estado) { 
            return;
        }
        this.estado = estado;
        if (estado) {
            ayudantes = Ajustes.getAtracciones().get(this.tipo).getNumAyudantes();
            responsable = new Responsable((Integer)ayudantes);
        }
        else {            
            ayudantes = 0;
            responsable = null;
        }
    }
    
    /**
     * Método que simula el torno de entrada Vip a la atracción, donde tras introducir la entrada se comprueba si 
     * el visitante ha adquirido el suplemento.
     * @param visita Entidades.Entrada impresa del visitante.
     * @return true si la entrada es válida para la cola Vip.
     */
    public static boolean comprobarVip(Visita visita) {
        return visita.getEntrada().isVip();
    }     
}
