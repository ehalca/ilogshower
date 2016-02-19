/**
 * 
 */
package ehalca.ilogshower.logfile;

import java.util.Map;

import ehalca.ilogshower.AbstractLogFileContext;

/**
 * @author Hulk
 *
 */
public class MessageSearchLogFileCriteria extends RequestSearchLogFileCriteria implements MessageLogFileCriteria {
	
	public MessageSearchLogFileCriteria(Map<String, Object> params) {
		super(params);
	}
	
	public String getFileName(){
		return super.getParameter(MessageLogFileCriteria.FILENAME);
	}
	
	public String getSessionId(){
		return super.getParameter(MessageLogFileCriteria.SESSIONID);
	}

	public AbstractLogFileContext toLogFileContext() {
		AbstractLogFileContext context = new AbstractLogFileContext(getSessionId() , getFileName());
                return context;
	}
	
	
}
