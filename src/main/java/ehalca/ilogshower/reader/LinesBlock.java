/**
 *
 */
package ehalca.ilogshower.reader;

import com.google.common.collect.Range;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Hulk
 *
 */
public class LinesBlock {

    private int position = 0;
    private int number = 1;

    private Range range;

    public LinesBlock() {
    }

    public LinesBlock(int position, int number) {
        this();
        this.position = position;
        this.number = number;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    @XmlTransient
    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

}
