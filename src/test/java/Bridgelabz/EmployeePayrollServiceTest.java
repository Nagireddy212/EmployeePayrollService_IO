package Bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class EmployeePayrollServiceTest {
    private  static String HOME = System.getProperty("user.dir");
    private  static String PLAY_WITH_NIO = "TempPlayGround";


    @Test
    public void givenPathCheckedThenConfirm() throws IOException
    {
        //check file exists or not
        Path homePath = Paths.get(HOME);
        Assertions.assertTrue(Files.exists(homePath));

        //Checking of deleted File Not Exist
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) Files.delete(playPath);
        Assertions.assertTrue(Files.notExists(playPath));

        //Creating a Directory
        Files.createDirectories(playPath);
        Assertions.assertTrue(Files.exists(playPath));

        //creating a File
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/temp" + cntr);
            Assertions.assertTrue(Files.notExists(tempFile));
            try
            { Files.createFile(tempFile); }
            catch (IOException e) {}
            Assertions.assertTrue(Files.exists(tempFile));
        });

        //List Directories & Files. Files with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);
    }
}
