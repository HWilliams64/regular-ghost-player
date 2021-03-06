package myCode;

import java.io.IOException;
import java.util.Set;
import professorCode.AbstractDictionary;
import professorCode.FileTextReader;

public class Dictionary extends AbstractDictionary {

    public Dictionary(String path, FileManager dictionaryFileReader) throws IOException {
        super(path, dictionaryFileReader);
    }

    @Override
    public int countWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public boolean containsWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
        return false;
    }

    @Override
    public Set<String> getWordsThatStartWith(String prefix, int size, boolean ignoreCase) throws IllegalArgumentException {
        return null;
    }
}
