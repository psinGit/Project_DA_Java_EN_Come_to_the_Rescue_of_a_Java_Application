package com.hemebiotech.analytics;

import java.util.List;

/**
 * Functions for reading from source and writing to source.
 * 
 * .Read Symptoms list : Anything that will read symptom data from a source The
 * important part is, the return value from the operation, which is a list of
 * strings, that may contain many duplications. The implementation does not need
 * to order the list.
 * 
 * .Write Symptoms list : write computed list of symptoms ordered by name. Each
 * line will define the name of the symptom and its number of occurrence.
 */

public interface ISymptomsFileAccess {

	/**
	 * If no data is available, return an empty List
	 * 
	 * @return a raw listing of all Symptoms obtained from a data source, duplicates
	 *         are possible/probable.
	 */
	List<String> readSymptomDataFromFile();

	/**
	 * 
	 * @param symptoms the raw list of symptoms to be compiled.
	 *
	 */
	void writeSymptomDataToFile(List<String> symptoms);
}