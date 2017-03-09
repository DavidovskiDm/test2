import exception.FileAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

//сделать с датапровайдером
public class FileStorage {
    private ArrayList<File> files = new ArrayList<>();
    private int availableSize;
    private int maxSize;

    public FileStorage(int size) {
        maxSize = size;
        availableSize = maxSize;
    }

    //проверка эксепшена + таймаута - стоит разделять тесты по проверяемой функциональности
    public boolean write(File file) throws FileAlreadyExistsException {
        if (files.contains(file)) {
            throw new FileAlreadyExistsException();
        }
        if (file.getSize() > availableSize) {
            return false;
        }
        files.add(file);
        availableSize -= file.getSize();
        if (availableSize % 2 == 0) {
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean isExists(String fileName) {
        for (File file: files) {
            if (file.getFilename().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    //тест будет зависеть от write и isExists(т.к. нет возможности использовать моки)
    //баг - не освобождается место
    public boolean delete(String fileName) {
        for (File file: files) {
            if (file.getFilename().equals(fileName)) {
                files.remove(file);
                return true;
            }
        }
        return false;
    }

    public List<File> getFiles() {
        return files;
    }

    //assertNull
    public File getFile(String fileName) {
        for (File file: files) {
            if (file.getFilename().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    public int getAvailableSize() {
        return availableSize;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
