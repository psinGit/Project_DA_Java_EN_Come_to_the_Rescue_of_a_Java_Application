package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Implementation class to read/write on files : - Read raw data from input file
 * into a list. - Write the formated list of symptoms into output file
 * 
 * @author psin
 * @version 1.0
 * 
 */
public class ProcessSymptomsList implements ISymptomsFileAccess {

	private String inputFilePath = null;
	private String outputFilePath;

	/**
	 * 
	 * @param inputFilePath Full or partial path of the input file.
	 * 
	 */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	/**
	 * 
	 * @param outputFilePath Full or partial path of the output file.
	 * 
	 */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	/**
	 * 
	 * @return outputFile outputfile path
	 * 
	 */

	public String getOutputFilePath() {
		return this.outputFilePath;
	}

	public ProcessSymptomsList() {
		super();
	}

	/**
	 * Read data from file, line by line. if file is empty, return empty list if
	 * file is null, return null Object if file doesn't exist or any error occurs, a
	 * message is printed out.
	 * 
	 * @return a list of string corresponding to the raw list of symptoms.
	 * @see ISymptomsFileAccess
	 */
	@Override
	public List<String> readSymptomDataFromFile() {
		if (inputFilePath == null) {
			System.out.println("Le fichier d'entrée n'est pas spécifié.");
			return null;
		} else {
			List<String> result = new ArrayList<String>();
			if (inputFilePath.length() == 0) {
				return result;
			} else {
				try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
					String line = reader.readLine();
					while (line != null && !"".equals(line)) {
						result.add(line);
						line = reader.readLine();
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}
				return result;
			}
		}
	}

	/**
	 * 
	 * From the raw list of symptoms, write the compiled list in the output file.
	 * The output file is ordered and grouping by symptom's name. Each line shows
	 * name and count number of the symptom.
	 * 
	 * @param symptoms raw list of symptoms.
	 * @see ISymptomsFileAccess
	 * 
	 */
	@Override
	public void writeSymptomDataToFile(List<String> symptoms) {
		// open output file to write out the symptoms list
		try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFilePath))) {
			if (symptoms.size() == 0) {
				return;
			} else {
				// feed TreeMap with symptoms list, ordered by key=name
				TreeMap<String, Long> symptomsMap = new TreeMap<String, Long>();
				for (String symptom : symptoms) {
					if (symptomsMap.containsKey(symptom)) {
						symptomsMap.put(symptom, symptomsMap.get(symptom) + 1);
					} else {
						symptomsMap.put(symptom, 1L);
					}
				}
				// write output file
				symptomsMap.forEach((name, nb) -> {
					try {
						out.write(name + " - " + nb);
						out.newLine();
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				});
			}
		} catch (NullPointerException e) {
			System.out.println("Traitement arrêté, problème d'écriture sur le fichier :" + e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}