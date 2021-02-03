package com.hemebiotech.analytics;

import java.util.ArrayList;
import java.util.List;

/**
 * Starting class of application.
 * Read file of symptoms and write the result in output file.
 * Shows ending message when output file is completed otherwise any error information. 
 * 
 * @author psin
 * @version 1.0
 *
 */
public class AnalyticsCounter {
	private static List<String> symptoms = new ArrayList<String>();
	public static final String PATH_INPUT_FILE = "symptoms.txt";
	public static final String PATH_OUTPUT_FILE = "results.out";

	public static void main(String args[]) throws Exception {

		// Init
		WorkSymptomOnFile worker = new WorkSymptomOnFile();
		worker.setInputFilePath(PATH_INPUT_FILE);
		worker.setOutputFilePath(PATH_OUTPUT_FILE);

		symptoms = worker.readSymptomDataFromFile();
		worker.writeSymptomDataToFile(symptoms);

		// End
		System.out.println("Fin de programme: fichier " + worker.getOutputFilePath() + " enregistré.");
	}
}
