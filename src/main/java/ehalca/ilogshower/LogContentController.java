/**
 * 
 */
package ehalca.ilogshower;

import java.util.Map;

import ehalca.ilogshower.logfile.LogFile;
import ehalca.ilogshower.logfile.RequestSearchLogFileCriteria;
import ehalca.ilogshower.service.FileLogService;
import ehalca.ilogshower.transport.InitLogFileResponse;
import ehalca.ilogshower.utils.LogFileUtilities;

/**
 * @author Hulk
 *
 */
public abstract class LogContentController {
	
	
	public InitLogFileResponse initLogFile(Map<String, String> params){
		RequestSearchLogFileCriteria criteria = new RequestSearchLogFileCriteria(params);
		LogFile file = getFileLogService().getLogFile(criteria);
		return new InitLogFileResponse(file.getFile().exists(), LogFileUtilities.getNumberOfLines(file));
	}
	
	public abstract FileLogService getFileLogService();
}
