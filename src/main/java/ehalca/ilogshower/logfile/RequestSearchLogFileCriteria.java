/**
 * 
 */
package ehalca.ilogshower.logfile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hulk
 *
 */
public class RequestSearchLogFileCriteria implements SearchLogFileCriteria {
	
	private Map<String, String> params;
	
	
	public RequestSearchLogFileCriteria() {
		this.params = new HashMap<String, String>();
	}

	public RequestSearchLogFileCriteria(Map<String, String> params){
		this.params = params;
	}
	
	/* (non-Javadoc)
	 * @see ehalca.ilogshower.logfile.SearchLogFileCriteria#getParameter(java.lang.String)
	 */
	public String getParameter(String paramName) {
		return this.params.get(paramName);
	}

}
