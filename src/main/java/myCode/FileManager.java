package myCode;

public class FileManager extends AbstractFileMonitor{

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
}
