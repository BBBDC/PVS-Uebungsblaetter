package mayer.maximilian.sheet07.exercise01;

public class Porsche extends Car{
	
	private static long serialVersionUID;
	
	private String model;
	private int ps;
	
	public Porsche(){
		super();
		this.model = null;
		this.ps = 0;
	}

	public Porsche(String licensePlate, String productionDate, int numberPassengers, int numberWheels, int numberDoors, String model, int ps) {
		super(licensePlate, productionDate, numberPassengers, numberWheels, numberDoors);
		this.model = model;
		this.ps = ps;
	}

	@Override
	public String toString() {
		return "Porsche [model=" + model + ", ps=" + ps + ", licensePlate=" + licensePlate + ", productionDate="
				+ productionDate + ", numberPassengers=" + numberPassengers + ", numberWheels=" + numberWheels
				+ ", numberDoors=" + numberDoors + "]";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	
	
}
