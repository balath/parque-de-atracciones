package Entidades;

/**
 * Clase que especifica a trabajadores como ayudantes de atracción .
 */
public class Ayudante extends Trabajador
{    
    /**
     * Constructor para objetos de la clase Ayudantes que contempla su sueldo como sueldo base. 
     */
    public Ayudante() {
        super(0.0,Perfil.AYUDANTE);
    }    
}
