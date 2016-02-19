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
	
	private Map<String, Object> params;
	
	
	public RequestSearchLogFileCriteria() {
		this.params = new HashMap<String, Object>();
	}

	public RequestSearchLogFileCriteria(Map<String, Object> params){
		this.params = params;
	}
	
	/* (non-Javadoc)
	 * @see ehalca.ilogshower.logfile.SearchLogFileCriteria#getParameter(java.lang.String)
	 */
	public String getParameter(String paramName) {
		return this.params.get(paramName).toString();
	}

}
