package Entidades;

import java.util.HashSet;
/**
 * Clase que especifica a trabajadores como responsables de atracción.
 */
public class Responsable extends Trabajador
{
    /**
     * Conjunto de ayudantes de atracción que tiene a cargo el responsable de atracción
     */
    private HashSet<Ayudante> ayudantes;
    /**
     * Constructor para objetos de la clase Responsables que contempla su sueldo 
     * como sueldo base más un incremento del 15%.
     */
    public Responsable() {
        super(15.0,Perfil.RESPONSABLE);
        ayudantes = new HashSet<>();
    }
    
     /**
     * Constructor para ayudantes ascendidos a responsables de atracción.
     */
    public Responsable(int numTrab) {
        super(15.0, Perfil.RESPONSABLE,numTrab);
        ayudantes = new HashSet<>();
    }
    
    /**
     * Constructor que asigna además el número de nuevos ayudantes que corresponda según el parámetro.
     * @param num carácter que indica el número de ayudantes que se asignaran al nuevo responsable.
     */
    public Responsable(Integer num) {
        super(15.0,Perfil.RESPONSABLE);
        ayudantes = new HashSet<>();
        for (int i =1;i<=num;i++) {
            addAyudante(new Ayudante());
        }
    }
    
    /**
     * Método que asigna ayudantes a un responsable de atracción.
     */
    public void addAyudante(Ayudante chaval) {
        this.ayudantes.add(chaval); 
    }
    
    /**
     * Método que devuelve el conjunto de los ayudantes asignados al responsable de atracción.
     * @return HashSet con los ayudantes asignados
     */
    public HashSet<Ayudante> getAyudantes() {
        return this.ayudantes;
    }    
}
