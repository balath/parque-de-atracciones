package Entidades;

/**
 * Clase que especifica a trabajadores de Atención al Cliente.
 */
public class AtencionCliente extends Trabajador
{    
    /**
     * Constructor para objetos de la clase AtenciónCliente que contempla su sueldo 
     * como sueldo base más un incremento del 10%.
     */
    public AtencionCliente() {
        super(10.0,Perfil.ATENCION);
    }
}
