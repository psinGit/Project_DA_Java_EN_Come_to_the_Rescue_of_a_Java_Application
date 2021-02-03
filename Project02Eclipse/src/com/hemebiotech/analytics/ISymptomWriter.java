package com.hemebiotech.analytics;

import java.util.List;

/**
 * This interface is used to write result list of compiled symptoms, out of the
 * program.
 */
public interface ISymptomWriter {
	/**
	 * 
	 * @param symptoms	the list of symptoms to be compiled.
	 * 					will start with header followed by ordered list of symptoms.
	 * 					Each line will describe the name of the symptom and its count number.
	 *
	 */
	void writeSymptomDataToFile(List<String> symptoms);
}
