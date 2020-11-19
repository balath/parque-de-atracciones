package Entidades;

/**
 * Clase que especifica una entrada de tipo Entidades.Oferta
 */
public class Oferta extends Entrada
{
    /**
     * Constructor de la clase Entidades.Oferta
     */
    public Oferta(Temporada temporada) {
        super(temporada,TipoEntrada.OFERTA,false,0.0);
    }
}
