package directory;

import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***************************************************************

 Created by william.russell@viasat.com  on 12/6/18
 ***************************************************************/
public class FileSearch {
    public List<String> findFiles(String fileName) {
        List<String> files = new ArrayList<>();
        if (fileName != null) {

            File directory = new File("./src/main/java/directory");
            File[] contents = directory.listFiles();
            for (File file : contents) {
                if (file.getName().equals("dir1") || file.getName().equals("dir2")) {
                    try {
                        files.addAll(Files.walk(Paths.get(file.getPath()))
                                .filter(potentialFile -> potentialFile.toString().contains(fileName))
                                .map(potentialFile -> potentialFile.toString())
                                .collect(Collectors.toList()));
                    } catch (Exception e) {
                        // log the exception here, keep searching
                    }
                }
            }
        }
        return files;
    }
}
