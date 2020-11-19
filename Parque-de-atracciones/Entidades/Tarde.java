package Entidades;

/**
 * Clase que especifica una entrada de tipo Entidades.Tarde
 */
public class Tarde extends Entrada
{
    /**
     * Constructor de la clase Entidades.Tarde
     */
    public Tarde(Temporada temporada) {
        super(temporada,TipoEntrada.TARDE,false,0.0);
    }
}
