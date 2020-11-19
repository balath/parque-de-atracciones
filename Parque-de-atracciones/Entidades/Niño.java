package Entidades;

import java.util.HashSet;
/**
 * Subclase de Entidades.Visitante que especifica un visitante Entidades.Niño
 */
public class Niño extends Visitante
{
    /**
     * Constructor de subclase Entidades.Niño
     */
    public Niño(HashSet<Descuentos> descuentos) {
        super(GrupoEdad.NIÑO, descuentos);
    }
}
