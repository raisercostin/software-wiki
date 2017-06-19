package ro.dcsi.internship;

import java.util.List;

/**
 * Created by Catalin on 6/19/2017.
 */
public abstract class Translator {
    protected String inputFile;
    protected String outputFile;
    protected List<String> headers;

    public Translator() {
        inputFile=null;
        outputFile=null;
        headers=null;
    }

    public abstract List<List<String>> readBulk(int nMax);
    public abstract boolean writeBulk(List<List<String>> userList);

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public List<String> getHeaders() {
        return headers;
    }
}
