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
	private String id;
	
	public InitLogFileResponse() {
		this.fileFound = false;
		this.linesNumber = 0;
		this.id = "";
	}
	
	public InitLogFileResponse(boolean found, int linesNumber, String id){
		this.fileFound = found;
		this.linesNumber = linesNumber;
		this.id = id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
