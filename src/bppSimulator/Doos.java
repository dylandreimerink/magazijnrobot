package bppSimulator;

public class Doos {
	private double hoogte;
	private double breedte;
	private double lengte;
	
	public Doos(double hoogte, double breedte, double lengte) {
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.lengte = lengte;
	}
	
	public double getCapacity() {
		return this.breedte * this.hoogte * this.lengte;
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
	
}
