package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation class to read/write on files : 
 * - Read raw data from input file into a list.
 * - Write the formated list of symptoms into output file
 * 
 * @author psin
 * @version 1.0
 * 
 */
public class ProcessSymptomsList implements ISymptomsFileAccess {

	private String inputFilePath;
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
	 * Read data from file, line by line.
	 *  if file is empty, return empty list
	 *  if file is null, return null Object
	 *  if file doesn't exist or any error occurs, a message is printed out.
	 * 
	 * @return a list of string corresponding to the raw list of symptoms.
	 * @see ISymptomReader
	 */
	@Override
	public List<String> readSymptomDataFromFile() {
		List<String> result = new ArrayList<String>();
		if (inputFilePath != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
				String line = reader.readLine();
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
			return result;
		} else {
			System.out.println("Le fichier d'entrée n'est pas spécifié.");
			return null;
		}
	}

	/**
	 * 
	 * From the raw list of symptoms, write the compiled list in the output file.
	 * A header will be included in the output file.
	 * The output file is ordered and grouping by symptom's name.
	 * Each line shows name and count number of the symptom.

	 * @param symptoms raw list of symptoms.
	 * @see ISymptomWriter
	 * 
	 */
	@Override
	public void writeSymptomDataToFile(List<String> symptoms) {
		// sort and print result in file
		try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFilePath))) {
			// fill the map with symptoms and occurrence number
			Map<String, Long> sympResult = symptoms.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			// Start writing output file with header
			out.write("Liste of Symptoms :");
			out.newLine();
			out.write("-------------------");
			out.newLine();
			// write next the ordered result list of symptoms
			sympResult.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(symp -> {
				try {
					out.write(symp.getKey() + " : " + symp.getValue());
					out.newLine();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			});
		} catch (NullPointerException e) {
			System.out.println("Traitement arrêté");
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}