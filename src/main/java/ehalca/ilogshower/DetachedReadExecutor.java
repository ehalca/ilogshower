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

/**
 * @author Hulk
 *
 */
public class DetachedReadExecutor implements InitializingBean,ReadExecutor,Runnable {
	
	private MessageSendingOperations<String> messagingTemplate;
	
	private AbstractLogFileContext fileContext;
	
	public DetachedReadExecutor(AbstractLogFileContext context, MessageSendingOperations<String> messagingTemplate) {
		this.fileContext = context;
		this.messagingTemplate = messagingTemplate;
	}
	
	public void afterPropertiesSet() throws Exception {
	}


	public void readLogFile() {
		if (false){
			 Thread.currentThread().interrupt();
		}
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(this.fileContext.getFileName()));
			List<String> lines = new ArrayList<String>();
			String line = reader.readLine();
			int lineNumber = 0;
			while ( line  != null){
				System.out.println("reading lines:");
				lines.add(line);
				if (lines.size() == 10){
					this.messagingTemplate.convertAndSend("/data/"+this.fileContext.getSesssionId(), lines);
					lines.clear();
				}
				line = reader.readLine();
			}
			this.messagingTemplate.convertAndSend("/data/"+this.fileContext.getSesssionId(), lines);
			System.out.println("sent:  " + lineNumber);
		reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		System.out.println("done reading!!");
	}

	public void run() {
		this.readLogFile();
	}

}
