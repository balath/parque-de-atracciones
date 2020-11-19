package Entidades;

/**
 * Clase que especifica una entrada de tipo Entidades.General
 */
public class General extends Entrada
{
    /**
     * Constructor de la clase Entidades.General
     */
    public General(Temporada temporada) {
        super(temporada,TipoEntrada.GENERAL,false,0.0);
    }    
}
