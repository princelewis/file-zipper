package com;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Zipper {

    private static Scanner scanner = new Scanner(System.in) ;
    public static void main(String[] args) {


        String name = "data.zip";
        FileHandlers fh = new FileHandlers();

        Path newPath = Paths.get(name);

        System.out.println("Enter the file names you want to zip. ");
        System.out.println("Note: you must enter the fileName in this format, fileName.ext");
        System.out.println("When you are done with the number of files you wish to zip, enter number 0 to exit");

        ArrayList<String> arrayOfFiles = new ArrayList<>();
        int exitCode = 1;
        while(exitCode != 0){
            String filename = scanner.nextLine();

            if(filename.equals("0")){
                exitCode = 0;
            } else if (filename.matches("/([a-zA-Z]).([a-z])/")){

                arrayOfFiles.add(filename);
            } else{
                System.out.println("you need to enter a correct filename");
            }
        }


        if(arrayOfFiles.size() != 0){

            try(FileSystem fileSystem = fh.createZip(newPath)) {
                for (String files : arrayOfFiles) {
                    FileHandlers.copyToZip(fileSystem, files);
                    System.out.println("added "+ files + " to the archive");
                    Path p = Paths.get(fileSystem.toString());
                }

            } catch(Exception e){
                System.out.println(e.getMessage());
            }

        } else{
            System.out.println("you need to enter a valid file");
        }

    }
}
