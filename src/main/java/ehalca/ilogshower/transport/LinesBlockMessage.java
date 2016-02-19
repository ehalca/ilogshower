/**
 * 
 */
package ehalca.ilogshower.transport;

import java.util.List;

import ehalca.ilogshower.reader.LinesBlock;

/**
 * @author Hulk
 *
 */
public class LinesBlockMessage {
	
	private LinesBlock currentBlock;
	private LinesBlock nextBlock;
	
	private List<String> lines;
	
	
	
	/**
	 * @return the currentBlock
	 */
	public LinesBlock getCurrentBlock() {
		return currentBlock;
	}

	/**
	 * @param currentBlock the currentBlock to set
	 */
	public void setCurrentBlock(LinesBlock currentBlock) {
		this.currentBlock = currentBlock;
	}

	/**
	 * @return the nextBlock
	 */
	public LinesBlock getNextBlock() {
		return nextBlock;
	}

	/**
	 * @param nextBlock the nextBlock to set
	 */
	public void setNextBlock(LinesBlock nextBlock) {
		this.nextBlock = nextBlock;
	}

	/**
	 * @return the lines
	 */
	public List<String> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	
	
	
}
