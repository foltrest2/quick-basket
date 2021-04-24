package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickBasketManager {

	private final static String SEPARATOR = ",";
	public final static String SAVE_PATH_FILE = "data/FBAdata.csv";
	

	public void importData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(SAVE_PATH_FILE));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String nit = parts[1];
			addRestaurant(name,nit,manager);
			line = br.readLine();
		}
		br.close();
	}
}
