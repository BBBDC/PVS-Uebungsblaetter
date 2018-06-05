package mayer.maximilian.sheet07.exercise01;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Program {

	public static void main(String[] args) {
		
			Mercedes mercedes = new Mercedes("B-AM-111", "01.10.1956", 2, 4, 2, "Ponton", 50);
			Porsche porsche = new Porsche("M-JM-0601", "30.05.2015", 5, 4, 4, "Panamera", 476);
			
			//XML Encode
			encodeObjects(mercedes,porsche);
			decodeObjects(mercedes,porsche);
			
			//Binary
			encodeBinary(mercedes,porsche);
			decodeBinary(mercedes,porsche);
	}

	private static void decodeBinary(Mercedes mercedes, Porsche porsche) {
		try {
			ObjectInputStream objstream = new ObjectInputStream(new FileInputStream("Binary"));
			Mercedes tmp1 = (Mercedes) objstream.readObject();
			Porsche tmp2 = (Porsche)objstream.readObject();
			objstream.close();
			
			System.out.println(tmp1.toString());
			System.out.println(tmp2.toString());
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void encodeBinary(Mercedes mercedes, Porsche porsche) {
		
		try {
			ObjectOutputStream objstream = new ObjectOutputStream(new FileOutputStream("Binary"));
			objstream.writeObject(mercedes);
			objstream.writeObject(porsche);
			objstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void decodeObjects(Mercedes mercedes, Porsche porsche) {
		
		XMLDecoder e;
		
		try {
			e = new XMLDecoder(new BufferedInputStream(new FileInputStream("AutoXML.xml")));
			Mercedes tmp1 = (Mercedes) e.readObject();
			Porsche tmp2 = (Porsche) e.readObject();
			
			e.close();
			
			System.out.println(tmp1.toString());
			System.out.println(tmp2.toString());
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("File not found!");
		}
		
	}

	private static void encodeObjects(Mercedes mercedes, Porsche porsche) {
		XMLEncoder e;
		try {
			e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("AutoXML.xml")));
			e.writeObject(mercedes);
			e.writeObject(porsche);
			e.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Datei nicht gefunden");
			
		}
	}

}
