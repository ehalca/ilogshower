/**
 * 
 */
package ehalca.ilogshower;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;

import ehalca.ilogshower.logfile.LogFile;
import ehalca.ilogshower.reader.LinesBlock;
import ehalca.ilogshower.reader.SmartLogFileReader;
import ehalca.ilogshower.transport.LinesBlockMessage;
import ehalca.ilogshower.utils.LogFileUtilities;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hulk
 *
 */
public class DetachedReadExecutor extends Thread implements InitializingBean,ReadExecutor {
	
	private MessageSendingOperations<String> messagingTemplate;
	
	private AbstractLogFileContext fileContext;
	
	private SmartLogFileReader readerStatus;
	
	private LogFile file;
	
	private int currentPos = 0;
        
        private boolean isRunning = true;
	
	public DetachedReadExecutor(AbstractLogFileContext context, MessageSendingOperations<String> messagingTemplate, LogFile logFile) {
		this.fileContext = context;
		this.messagingTemplate = messagingTemplate;
		this.file = logFile;
		this.readerStatus = new SmartLogFileReader(context.getMaxRead() != -1 ? context.getMaxRead() : LogFileUtilities.getNumberOfLines(this.file));
	}
	
	public void afterPropertiesSet() throws Exception {
		
	}


	public void readLogFile() {
		if (false){
			 Thread.currentThread().interrupt();
		}
		BufferedReader reader = null;
		try {
			while (!this.readerStatus.isFileRead()){
				LinesBlock nextBlock = this.readerStatus.getNextLineBlock(this.fileContext);
				if (this.currentPos != nextBlock.getPosition() || reader == null){
					if (reader != null){
						reader.close();
					}
					this.currentPos = nextBlock.getPosition();
					reader = this.getReader(nextBlock.getPosition());
				}
				List<String> lines = new ArrayList<String>();
				String line = reader.readLine();
				while ( line  != null){
					lines.add(line);
					this.currentPos++;
					if (lines.size() == nextBlock.getNumber()){
						this.processLines(lines, nextBlock);
						lines.clear();
						break;
					}
					line = reader.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
                this.isRunning = false;
	}
	
	private void processLines(List<String> lines, LinesBlock readBlock){
            this.readerStatus.onBlockRead(readBlock);
            LinesBlock nextBlock = this.readerStatus.getNextLineBlock(this.fileContext);
            LinesBlockMessage message = new LinesBlockMessage();
            message.setCurrentBlock(readBlock);
            message.setLines(lines);
            message.setNextBlock(nextBlock);
            System.out.println("Sending lines :" + readBlock.getPosition());
            this.messagingTemplate.convertAndSend("/data/"+this.fileContext.getSesssionId(), message);
	}
	
	private BufferedReader getReader(int pos) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(this.fileContext.getFileName()));
		if (pos != 0){
			int cur = 0;
			while (cur < (pos) && reader.readLine() != null) cur++;
		}
		return reader;
	}

	public void run() {
		this.readLogFile();
	}
        
    public synchronized boolean isReading() {
        return this.isRunning;
    }

    public synchronized AbstractLogFileContext getFileContext() {
        return fileContext;
    }

    public synchronized void setFileContext(AbstractLogFileContext fileContext) {
        this.fileContext = fileContext;
    }
}
