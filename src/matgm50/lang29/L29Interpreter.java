package matgm50.lang29;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Lang29Interpreter or L29Interpreter for short is the Object responsible for interpreting
 * Lang29 scripts and storing their values and operations.
 *
 * Lang29 scripts consist of a very simple syntax inspired by Basic and MySQL.
 *
 * @author MasterAbdoTGM50
 */
public class L29Interpreter {

    /** The line the reader is currently at */
    public String currentLine;

    /** The BufferedReader object responsible for reading the script */
    private BufferedReader reader;
    /** The Map object responsible for storing values of variables in the script */
    private Map<String, String> values = new HashMap<String, String>();

    /**
     * Constructs an empty L29Interpreter object
     */
    public L29Interpreter() {}

    /**
     * Constructs an empty L29Interpreter object.
     *
     * @param fileName the directory of the script to be opened
     */
    public L29Interpreter(String fileName) { openFile(fileName); }

    /** Executes the currently opened script. */
    public void execute() {

        String args[];

        try {

            while ((currentLine = reader.readLine()) != null) {

                args = currentLine.split(" ", 4);
                if(L29Util.isKeyword(args[0])) {

                    L29Util.getKeyword(args[0]).execute(this, Arrays.copyOfRange(args, 1, args.length));

                }

            }

        } catch (IOException e) { e.printStackTrace(); }

    }

    public boolean hasValue(String key) { return values.containsKey(key); }
    public String setValue(String key, String value) { return values.put(key, value); }
    public String getValue(String key) { return values.get(key); }

    /**
     * Opens the script at the specified directory
     *
     * @param fileName the directory of the script to open
     */
    public void openFile(String fileName) {

        //closeCurrentFile();
        try { reader = new BufferedReader(new FileReader(fileName)); } catch (IOException e) { e.printStackTrace(); }

    }

    public void closeCurrentFile() { try { reader.close(); } catch (IOException e) { e.printStackTrace(); } }

    public BufferedReader getReader() { return reader; }

}
