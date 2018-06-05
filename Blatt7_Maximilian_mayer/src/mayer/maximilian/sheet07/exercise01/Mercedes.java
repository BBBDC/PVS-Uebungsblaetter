package mayer.maximilian.sheet07.exercise01;

public class Mercedes extends Car{
	
	private static long serialVersionUID;
	
	private String model;
	private int capacity;
	
	public Mercedes(){
		super();
		this.model = null;
		this.capacity = 0;
	}

	public Mercedes(String licensePlate, String productionDate, int numberPassengers, int numberWheels, int numberDoors, String model, int capacity) {
		super(licensePlate, productionDate, numberPassengers, numberWheels, numberDoors);
		this.model = model;
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Mercedes [model=" + model + ", capacity=" + capacity + ", licensePlate=" + licensePlate
				+ ", productionDate=" + productionDate + ", numberPassengers=" + numberPassengers + ", numberWheels="
				+ numberWheels + ", numberDoors=" + numberDoors + "]";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
