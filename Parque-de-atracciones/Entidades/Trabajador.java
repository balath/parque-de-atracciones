package Entidades;

/**
 * Clase que engloba a todo los trabajadores del parque.
 */
public class Trabajador
{
    /**
     * Tipo enumerado que define claves para cada subclase de la clase Entidades.Trabajador
     */
    public enum Perfil {
        AYUDANTE, RESPONSABLE, ATENCION, RRPP
    }
    
    /**
     * Sueldo base de los ayudantes de atracción, que se toma de referencia para el cálculo del resto de sueldos.
     */
    private static final double SUELDOBASE = 950.0;
    /**
     * Variable de clase que funciona como contador para dar a cada trabajador su número de trabajador(numTrab)
     */
    private static int CONT = 1;
    
    private double sueldo;
    private Perfil perfil;
    private int numTrab;
    
    
    /**
     * Constructor para objetos de la clase Entidades.Trabajador
     * @param porcentaje de incremento del sueldo con respecto al sueldo base.
     */
    public Trabajador(double incremento, Perfil perfil) {
        sueldo = SUELDOBASE + (SUELDOBASE * incremento / 100.0);
        this.perfil = perfil;
        numTrab = CONT;
        CONT++;
        RegistroPersonal.addTrabajador(this); 
    }
    
    /**
     * Constructor específico para ayudantes ascendidos a responsables de atracción.
     * @param incremento Porcentaje de incremento del sueldo con respecto al sueldo base.
     * @param numTrab Número del ayudante que va a promocionar.
     */
    public Trabajador(double incremento, Perfil perfil, int numTrab)
    {
        sueldo = SUELDOBASE + (SUELDOBASE * incremento / 100.0);
        this.perfil = perfil;
        this.numTrab = numTrab;
        RegistroPersonal.addTrabajador(this);
    }
           
    /**
     * Método que devuelve el sueldo de un trabajador.
     */
    public double getSueldo() {
        return this.sueldo;
    }
       
    /**
     * Getter que devuelve la clave del perfil del trabajador.
     */
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    /**
     * Getter que devuelve el número de trabajador.
     */
    public int getNumTrab() {
        return this.numTrab;
    }
        
    /**
     * Método para promocionar ayudantes de atracción a responsables.
     */
    public Responsable promocionar(Ayudante chaval) {
        return new Responsable(chaval.getNumTrab());        
    }
}
