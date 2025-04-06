/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logging;

import java.io.*; 
import java.time.LocalDateTime;
/**
         *
         * @author acetil
         */

public class DefaultLogger {

    private File loggingFile;
    private FileWriter fw;
    private StringBuilder buffer;
    private int numberOfLines = 1;
    private int linesBeforeWrite;
    private boolean logToFile;

    public DefaultLogger(String url, int linesBeforeWrite, boolean logToFile) throws IllegalArgumentException {
        
        if (linesBeforeWrite < 1) {
            throw new IllegalArgumentException("Lines before write must be superior to 1");
        }
        
        this.logToFile = logToFile;
        
        buffer = new StringBuilder();
        loggingFile = new File(url);
        this.linesBeforeWrite = linesBeforeWrite;

        try {
            if (loggingFile.createNewFile()) {
                String line = "File created in " + url + "\n";
                fw = new FileWriter(loggingFile);
                log("\n\nNuevo log desde " + LocalDateTime.now());
                log(line);
                System.out.println(line);
            } else {
                String line = "File already exists! in " + url + "\n";
                System.out.println(line);
                
                FileReader fr = new FileReader(loggingFile);
                char[] charBuf = new char[10];
                int offset = 0;
                
                while (true) {
                    int result = fr.read(charBuf, offset, offset + 10);
                    
                    buffer.append(charBuf);
                    
                    if (result == -1) {
                        break;
                    }
                }
                
                System.out.println("Read contents: " + buffer.toString());
                
                fw = new FileWriter(loggingFile);
                log("\n\nNuevo log desde " + LocalDateTime.now());
                log(line);
            }
            
            
        } catch (IOException e) {
            System.out.println("ERROR creating log file");
            System.out.println(e.getMessage());
        }
    }
    
    public void log(String message) {
        receiveLog("MESSAGE", message);
    }
    
    public void logError(String message) {
        receiveLog("ERROR", message);
    }
    
    public void logWarning(String message) {
        receiveLog("WARNING", message);
    }
    
    public void close() {
        try {
            fw.append(buffer);
            fw.close();
        } catch (Exception e) {
            throw new UnsupportedOperationException("not supported exception");
        }
    }
    
    private void receiveLog(String pre, String message) {
        
        buffer.append("[" + pre + "]: " + message + "\n");
        numberOfLines++;
        
        // if reached the limit, write
        if (numberOfLines >= linesBeforeWrite) {
            System.out.print(buffer.toString());
            
            if (logToFile)
                writeTofile();
            
            // Restart resources
            numberOfLines = 0;
            buffer = new StringBuilder();
        }
    }

    private void writeTofile() {
        try {
            System.out.println("Writing " + buffer.length() + " to the file");
            fw.append(buffer);
        } catch (IOException e) {
            System.out.println("ERROR writing to file");
            System.out.println(e.getMessage());
        }
    }
}
