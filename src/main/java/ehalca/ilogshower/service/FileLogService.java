/**
 * 
 */
package ehalca.ilogshower.service;

import ehalca.ilogshower.logfile.LogFile;
import ehalca.ilogshower.logfile.SearchLogFileCriteria;

/**
 * @author Hulk
 *
 */
public interface FileLogService {
	
	public LogFile getLogFile(SearchLogFileCriteria criteria);
	
}
