package Entidades;

/**
 * Clase que especifica a trabajadores de Relaciones Públicas.
 */
public class RelacionesPub extends Trabajador
{    
    /**
     * Constructor para objetos de la clase Entidades.RelacionesPub que contempla su sueldo
     * como sueldo base más un incremento del 20%.
     */
    public RelacionesPub() {
        super(20.0,Perfil.RRPP);
    }
}
