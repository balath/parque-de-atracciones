package Entidades;

/**
 * Clase que especifica una entrada de tipo Entidades.Laborable
 */
public class Laborable extends Entrada
{
    /**
     * Constructor de la clase Entidades.Laborable
     */
    public Laborable(Temporada temporada) {
        super(temporada,TipoEntrada.LABORABLE,false,0.0);
    }
}
