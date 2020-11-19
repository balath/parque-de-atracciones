package Entidades;

/**
 * Clase que especifica una entrada de tipo Entidades.Familiar
 */
public class Familiar extends Entrada
{
    /**
     * Constructor de la clase Entidades.Familiar
     */
    public Familiar(Temporada temporada) {
        super(temporada,TipoEntrada.FAMILIAR,false,0.0);
    }
}
