package bppSimulator;

public class Product {
	private double hoogte;
	private double breedte;
	private double lengte;
	private int aantal;
	private double ruimte;
	private double oppervlak;
	
	public Product(double hoogte, double breedte, double lengte, int aantal) {
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.lengte = lengte;
		this.aantal = aantal;
		this.ruimte = hoogte * breedte * lengte;
		this.oppervlak = breedte * lengte;
	}
	
	public double getRuimte(){
		return this.ruimte;
	}
	
	public double getOppervlak(){
		return this.oppervlak;
	}

	public double getHoogte() {
		return hoogte;
	}

	public void setHoogte(double hoogte) {
		this.hoogte = hoogte;
	}

	public double getBreedte() {
		return breedte;
	}

	public void setBreedte(double breedte) {
		this.breedte = breedte;
	}

	public double getLengte() {
		return lengte;
	}

	public void setLengte(double lengte) {
		this.lengte = lengte;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public void setRuimte(double ruimte) {
		this.ruimte = ruimte;
	}

	public void setOppervlak(double oppervlak) {
		this.oppervlak = oppervlak;
	}
	
}
