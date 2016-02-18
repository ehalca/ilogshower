/**
 * 
 */
package ehalca.ilogshower.transport;

/**
 * @author Hulk
 *
 */
public class InitLogFileResponse {
	
	
	private boolean fileFound;
	
	public InitLogFileResponse() {
		this.fileFound = false;
	}
	
	public InitLogFileResponse(boolean found){
		this.fileFound = found;
	}
	
	/**
	 * @return the fileFound
	 */
	public boolean isFileFound() {
		return fileFound;
	}

	/**
	 * @param fileFound the fileFound to set
	 */
	public void setFileFound(boolean fileFound) {
		this.fileFound = fileFound;
	}
	
	
	
	
}
