/**
 * 
 */
package ehalca.ilogshower.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ehalca.ilogshower.logfile.LogFile;

/**
 * @author ehalc
 *
 */
public class LogFileUtilities {

	public static int getNumberOfLines(LogFile file){
		BufferedReader reader;
		int lines = 0;
		try {
			reader = new BufferedReader(new FileReader(file.getFile()));
		if (file.getFilter() != null){
			String line = reader.readLine();
			while (line != null){
				if (file.getFilter().filerLine(line)){
					lines++;
				}
				line = reader.readLine();
			}
		}else{
			while (reader.readLine() != null) lines++;
		}
		reader.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return lines;
	}
	
	
}
