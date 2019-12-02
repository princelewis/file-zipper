package com;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FileHandlers {

    //create a method to create zip file
    public FileSystem createZip(Path fileAsPath) throws URISyntaxException, IOException {

        //create sort of a permission that would be needed for creating a zip file
        Map<String, String> providerProps = new HashMap<>() {{
           put("create", "true");
        }};

        //generate a URI for the zip file to be created. The URI is suppose to start from the root folder to this current directory
        URI zipURI = new URI("jar:file",fileAsPath.toUri().getPath(), null);

        //using the filesystem method, create the zip file
        FileSystem createdZip = FileSystems.newFileSystem(zipURI, providerProps);

        return createdZip;
    }


    public static void copyToZip (FileSystem createdZip , String filename) throws IOException {
        Path sourceFile = Paths.get(filename);
        Path destinationFile = createdZip.getPath('/' + filename);
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }
}
