/**
 * 
 */
package ehalca.ilogshower.reader;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Range;
import com.google.common.collect.Sets;

import ehalca.ilogshower.AbstractLogFileContext;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Hulk
 *
 */
public class SmartLogFileReader {
	
	private final int totalLines;
	
	private Set<Range<Integer>> readLineRanges = Sets.newHashSet();
	
	
	public SmartLogFileReader(int lines) {
		this.totalLines = lines;
	}
	
	public boolean isFileRead(){
		return this.readLineRanges.size() == 1 && this.readLineRanges.iterator().next().equals(Range.closed(0, this.totalLines));
	}
	
	public LinesBlock getNextLineBlock(AbstractLogFileContext context){
		int searchPosition = this.getStartPosition(context.getPriorityPos(), context.getDirection());
		if (searchPosition == this.totalLines){
                    searchPosition = this.getStartPosition(0, context.getDirection());
                }
		int endPos = searchPosition + context.getCount();
                if (endPos > this.totalLines){
                    endPos = this.totalLines;
                }
		for (Range<Integer> range : this.readLineRanges){
			if (range.contains(endPos)){
				endPos = range.lowerEndpoint();
				break;
			}
		}
		LinesBlock result = new LinesBlock();
		result.setPosition(searchPosition);
		result.setNumber(endPos - searchPosition);
                if (this.isFileRead() || endPos < searchPosition){
                    result.setRange(Range.closed(0, this.totalLines));
                }else{
                    result.setRange(Range.closed(searchPosition, endPos));
                }
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
                this.readLineRanges.add(range);
                Boolean changed = true;
                while (changed){
                    changed = false;
                    Iterator<Range<Integer>> iterator = this.readLineRanges.iterator();
                    Set<Range<Integer>> adding = Sets.newHashSet();
                    while (iterator.hasNext()){
                            Range<Integer> readRange = iterator.next();
                            if (!range.equals(readRange) && range.isConnected(readRange)){
                                    range = range.span(readRange);
                                    adding.add(range);
                                    iterator.remove();
                                    changed = true;
                            }
                    }
                    this.readLineRanges.addAll(adding);
                    
                }
	}
}
