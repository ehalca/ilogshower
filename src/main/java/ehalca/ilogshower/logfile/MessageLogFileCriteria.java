/**
 * 
 */
package ehalca.ilogshower.logfile;

import ehalca.ilogshower.AbstractLogFileContext;

/**
 * @author Hulk
 *
 */
public interface MessageLogFileCriteria extends SearchLogFileCriteria {
	
	public static final String FILENAME = "fileName";
	
	public static final String SESSIONID = "sessionId";
	
	public String getFileName();
	
	public String getSessionId();
	
	public AbstractLogFileContext toLogFileContext();
	
}
