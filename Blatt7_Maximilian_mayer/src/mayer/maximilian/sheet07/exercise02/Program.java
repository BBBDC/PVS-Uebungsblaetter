package mayer.maximilian.sheet07.exercise02;

import java.awt.List;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import mayer.maximilian.sheet07.exercise01.Car;

public class Program {
	
	public static void main(String[] args) {
		
		Car[] car = new Car[10];
		
		for (int i = 0; i < car.length; i++) {
			
			car[i] = new Car("M-AB-10"+i, "08.04.199"+i, i*2, 4, 2);
			
		}
		
		exportToXML(car);
		
		LinkedList<Car> carsFromXML = getCarFromXML();
		System.out.println(carsFromXML.toString());
	
		//System.out.println(Arrays.toString(car));
		
	}
	
	private static LinkedList<Car> getCarFromXML() {
		
		LinkedList<Car> cars = new LinkedList<>();
		
		try {		
			SAXBuilder builder = new SAXBuilder();
			
			Document doc = builder.build("Car.xml");
			
			Element root = doc.getRootElement();
			java.util.List<Element> carsList = root.getChildren();
			
			Car tmpcar = null;
			
			for(Element tmp : carsList){
				
				tmpcar = new Car();
				
				tmpcar.setLicensePlate(tmp.getChildText("licensePlate"));
				//tmpcar.setNumberDoors(Integer.parseInt(tmp.getAttributeValue("numberDoors")));
				tmpcar.setNumberDoors(Integer.parseInt(tmp.getChildText("numberDoors")));
				tmpcar.setNumberPassengers(Integer.parseInt(tmp.getChildText("numberPassengers")));
				tmpcar.setNumberWheels(Integer.parseInt(tmp.getChildText("numberWheels")));
				tmpcar.setProductionDate(tmp.getChildText("productionDate"));
			
				cars.add(tmpcar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cars;
		
	}

	public static void exportToXML(Car[] car){
		
		//Create Doc with root element
		Element root = new Element("Car");
		Document document = new Document(root);
		
		//Add cars to XML
		for (int i = 0; i < car.length; i++) {
			
			Element carName = new Element("Car"+(i+1));
			
			Element licensePlate = new Element("licensePlate");
			licensePlate.addContent(car[i].getLicensePlate());
			
			Element productionDate = new Element("productionDate");
			productionDate.addContent(car[i].getProductionDate());
			
			Element numberPassengers = new Element("numberPassengers");
			numberPassengers.addContent(String.valueOf(car[i].getNumberPassengers()));
			
			Element numberWheels = new Element("numberWheels");
			numberWheels.addContent(String.valueOf(car[i].getNumberWheels()));
			
			Element numberDoors = new Element("numberDoors");
			numberDoors.addContent(String.valueOf(car[i].getNumberDoors()));
			
			//Add values to each carname
			carName.addContent(numberDoors);
			carName.addContent(licensePlate);
			carName.addContent(numberWheels);
			carName.addContent(numberPassengers);
			carName.addContent(productionDate);
			
			document.getRootElement().addContent(carName);
			
		}
		
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			xmlOutputter.output(document, new BufferedOutputStream(new FileOutputStream("Car.xml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File could not be written!");
		}
		
	}

}
