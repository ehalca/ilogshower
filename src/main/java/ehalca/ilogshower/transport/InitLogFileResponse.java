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
	private int linesNumber;
	
	public InitLogFileResponse() {
		this.fileFound = false;
		this.linesNumber = 0;
	}
	
	public InitLogFileResponse(boolean found, int linesNumber){
		this.fileFound = found;
		this.linesNumber = linesNumber;
	}
	
	/**
	 * @return the fileFound
	 */
	public boolean isFileFound() {
		return fileFound;
	}

	public int getLinesNumber() {
		return linesNumber;
	}

	public void setLinesNumber(int linesNumber) {
		this.linesNumber = linesNumber;
	}

	/**
	 * @param fileFound the fileFound to set
	 */
	public void setFileFound(boolean fileFound) {
		this.fileFound = fileFound;
	}
	
	
	
	
}
