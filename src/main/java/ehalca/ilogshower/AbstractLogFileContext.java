/**
 * 
 */
package ehalca.ilogshower;

import ehalca.ilogshower.reader.ReadDirection;

/**
 * @author ehalc
 *
 */
public class AbstractLogFileContext {
	
	private String sesssionId;
	private String fileName;
	private ReadDirection direction = ReadDirection.DOWN;
	private int priorityPos = 25;
	private int count = 6;
	private int maxRead = -1;
	
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
	/**
	 * @return the direction
	 */
	public ReadDirection getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(ReadDirection direction) {
		this.direction = direction;
	}
	/**
	 * @return the priorityPos
	 */
	public int getPriorityPos() {
		return priorityPos;
	}
	/**
	 * @param priorityPos the priorityPos to set
	 */
	public void setPriorityPos(int priorityPos) {
		this.priorityPos = priorityPos;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the maxRead
	 */
	public int getMaxRead() {
		return maxRead;
	}
	/**
	 * @param maxRead the maxRead to set
	 */
	public void setMaxRead(int maxRead) {
		this.maxRead = maxRead;
	}
	
	
	
}
