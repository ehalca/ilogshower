/**
 * 
 */
package ehalca.ilogshower.reader;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Range;

import ehalca.ilogshower.AbstractLogFileContext;
import java.util.ArrayList;

/**
 * @author Hulk
 *
 */
public class SmartLogFileReader {
	
	private final int totalLines;
	
	private List<Range<Integer>> readLineRanges = new ArrayList();
	
	
	public SmartLogFileReader(int lines) {
		this.totalLines = lines;
	}
	
	public boolean isFileRead(){
		return this.readLineRanges.size() == 1 && this.readLineRanges.get(0).equals(Range.closed(0, this.totalLines));
	}
	
	public LinesBlock getNextLineBlock(AbstractLogFileContext context){
		LinesBlock result = new LinesBlock();
		int searchPosition = this.getStartPosition(context.getPriorityPos(), context.getDirection());
		if (searchPosition == this.totalLines){
                    searchPosition = this.getStartPosition(0, context.getDirection());
                }
                boolean endExclusive = false;
		int endPos = searchPosition + context.getCount();
                if (endPos > this.totalLines){
                    endPos = this.totalLines;
                }
		for (Range<Integer> range : this.readLineRanges){
			if (range.contains(endPos)){
				endPos = range.lowerEndpoint() - 1;
                                endExclusive = true;
				break;
			}
		}
		
		result.setPosition(searchPosition);
		result.setNumber(endPos - searchPosition + 2);
                result.setRange(Range.closed(searchPosition, endExclusive ? endPos + 1 : endPos));
		return result;
	}
        
        public int getStartPosition(int searched, ReadDirection direction){
            for (Range<Integer> range : this.readLineRanges){
			if (range.contains(searched)){
				if (direction.equals(ReadDirection.DOWN) 
						|| direction.equals(ReadDirection.UP)){//UP not supporting therefore we are going down :(
                                     return range.upperEndpoint();
				}
				break;
			}
		}
            return searched;
        }
	
	public void onBlockRead(LinesBlock block){
		Range range = block.getRange();
		Iterator<Range<Integer>> iterator = this.readLineRanges.iterator();
		while (iterator.hasNext()){
			Range<Integer> readRange = iterator.next();
			if (range.isConnected(readRange)){
				range = range.span(readRange);
				iterator.remove();
			}
		}
                this.readLineRanges.add(range);
	}
}
