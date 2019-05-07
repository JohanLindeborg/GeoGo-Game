package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserHelper {
	public static void writeToFile(Object o, File file) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			oos.writeObject(o);
		} catch (Exception e) {
			System.out.println("Error creating file: [" + e.toString() + "]");
		}
	}

	public static Object readFile(File file) {
		Object o = null;
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			o = ois.readObject();
		} catch (Exception e) {
			System.out.println("Error reading file: [" + e.toString() + "]");
		}
		return o;
	}
}
