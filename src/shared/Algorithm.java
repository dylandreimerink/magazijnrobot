package shared;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public interface Algorithm {

	public Route calculateRoute();
	
	public Resultaat getResultaat();
	
	public String getAlgorithmName();
}
