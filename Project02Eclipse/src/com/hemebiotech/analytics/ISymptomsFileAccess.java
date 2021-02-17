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
 * .Write Symptoms list : write computed list of symptoms ordered by name.
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
	 * @param symptoms the list of symptoms to be compiled will start with header
	 *                 followed by ordered list of symptoms. Each line will describe
	 *                 the name of the symptom and its count number.
	 *
	 */
	void writeSymptomDataToFile(List<String> symptoms);
}