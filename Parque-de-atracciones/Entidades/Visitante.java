package Entidades;

import java.util.HashSet;
/**
 * Clase que engloba a todos los visitantes del parque y se encarga de recoger la
 * información relativa al grupo de edad del visitante y los descuentos a los que opta.
 */ 
public class Visitante
{
    /**
     * Atributo que guarda el grupo de edad al que pertenece el visitante.
     */
    private GrupoEdad grupo;
    /**
     * Atributo que guarda un conjunto con los descuentos aplicables al visitante.
     */
    private HashSet<Descuentos> descuentos;
    
    
    /**
     * Constructor de clase Entidades.Visitante, que recibe de cada subclase los parámetros necesarios
     * @param grupo indica el grupo de edad al que se adscribe el visitante.
     */
    public Visitante(GrupoEdad grupo,HashSet<Descuentos> descuentos) {
        this.grupo = grupo;  
        this.descuentos = descuentos;
    }
    
    /**
     * Setter para la información relativa a los descuentos que se le aplican al visitante.
     * @param HashSet que contiene los descuentos aplicables.
     */
    public void setDescuentos(HashSet<Descuentos> descuentos) {
        this.descuentos = descuentos;        
    }
    
    /**
     * Getter para la información del grupo de edad.
     * @return clave del tipo enumerado Entidades.GrupoEdad que contiene el grupo al que pertenece el visitante.
     */
    public GrupoEdad getGrupoEdad() {
        return this.grupo;        
    }
    
    /**
     * Getter para la información relativa a los descuentos que se le aplican al visitante.
     * @return HashSet que contiene los descuentos aplicables.
     */
    public HashSet<Descuentos> getDescuentos() {
        return this.descuentos;        
    }
}