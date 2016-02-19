/**
 * 
 */
package ehalca.ilogshower.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;

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
                        lines++;
		}
		reader.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return lines;
	}
	
	public static String getUserIdentifier(){
		try{
		  User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      return user.getUsername(); //get logged in username
		}catch(Exception exc){
			return RequestContextHolder.currentRequestAttributes().getSessionId();
		}
	}
	
	
}
