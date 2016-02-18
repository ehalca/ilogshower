/**
 * 
 */
package ehalca.ilogshower.logfile;

import java.io.File;

/**
 * @author Hulk
 *
 */
public class LogFile {
	
	private File file;
	
	private LogLineFilter filter;

	public LogFile() {
	}
	
	public LogFile(File file) {
		this.file = file;
	}
	
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the filter
	 */
	public LogLineFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(LogLineFilter filter) {
		this.filter = filter;
	}
	
	
	
}
