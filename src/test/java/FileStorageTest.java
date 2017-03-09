import exception.FileAlreadyExistsException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by nikikuzi on 3/7/17.
 */
public class FileStorageTest {

    @Test(expectedExceptions = FileAlreadyExistsException.class, timeOut = 2000, groups = "writeTest")
    public void writeExistedFileTest() throws Exception {
        FileStorage fileStorage = new FileStorage(40);
        File file = new File("test.exe", "123123123");
        fileStorage.write(file);
        fileStorage.write(file);
    }

    @Test(timeOut = 2000, groups = "writeTest")
    public void writeSingleFileTest() throws Exception {
        FileStorage fileStorage = new FileStorage(39);
        File file = new File("test.exe", "12312312");
        assertTrue(fileStorage.write(file));
    }

    @Test(timeOut = 2000, groups = "writeTest")
    public void writeSingleFileNotEnoughSpaceTest() throws Exception {
        FileStorage fileStorage = new FileStorage(1);
        File file = new File("test.exe", "12312312");
        assertFalse(fileStorage.write(file));
    }

    @Test(dependsOnGroups = "writeTest")
    public void isExistsTest() throws FileAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(100);
        File file = new File("test.exe", "12312312");
        fileStorage.write(file);
        assertTrue(fileStorage.isExists("test.exe"));
    }
}
