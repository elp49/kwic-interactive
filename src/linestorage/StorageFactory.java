package linestorage;

import configuration.StoragePreferences;
import util.FileReader;
import util.FileWriter;

public class StorageFactory {

	public StorageFactory() {
	}

	public LineStorageProcessor getLineStorageProcessor(StoragePreferences preferences) {
		LineStorageProcessor storage = null;
		
		// Test storage IO preferences.
		String method = preferences.getMethod();
		if (method.equalsIgnoreCase(StoragePreferences.FILE)) {
			String path = preferences.getFilePath();
			FileReader reader = new FileReader(path);
			FileWriter writer = new FileWriter(path);
			storage = new FileLineStorageProcessor(reader, writer);
		}
		else if (method.equalsIgnoreCase(StoragePreferences.CORE)) {
			storage = new CoreLineStorageProcessor();
		}
		
		return storage;
	}
}
