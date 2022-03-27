package myCode;

import java.io.IOException;
import java.util.Set;

import professorCode.AbstractFileMonitor;
import professorCode.FileTextReader;
import professorCode.FileTextWriter;

public class FileManager extends AbstractFileMonitor implements FileTextReader, FileTextWriter {

    public FileManager(String path) {
        super(path);
    }

    @Override
    public void setFilePath(String path) {

    }

    @Override
    public String getFilePath() throws IllegalStateException {
        return null;
    }

    @Override
    public void writeToFile(String string, String path) throws IOException, IllegalArgumentException {

    }

    @Override
    public String readText(String path) throws IOException {
        return null;
    }

    @Override
    public Set<String> getAllLines(String path) throws IOException {
        return null;
    }

    @Override
    public String getLastLine(String path) throws IOException {
        return null;
    }
}
