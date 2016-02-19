/**
 * 
 */
package ehalca.ilogshower;

/**
 * @author ehalc
 *
 */
public class AbstractLogFileContext {
	
	private String sesssionId;
	private String fileName;
	
	public AbstractLogFileContext(String sesssionId, String fileName) {
		super();
		this.sesssionId = sesssionId;
		this.fileName = fileName;
	}
	public String getSesssionId() {
		return sesssionId;
	}
	public void setSesssionId(String sesssionId) {
		this.sesssionId = sesssionId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
