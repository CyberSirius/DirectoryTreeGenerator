package bg.rashev.traversal;

import java.io.File;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        String word = "sonder";
        int randomParameter = 100;
        File file = new File(Constants.ROOT_DIRECTORY);
        int lowerWidthBound = 1;
        int upperWidthBound = 10;
        int ratioDirectories = 5;
        int ratioFiles = 5;
        int depth = 8;
        DirectoryTreeGenerator directoryTreeGenerator = new DirectoryTreeGenerator(word, randomParameter, ratioFiles, ratioDirectories, upperWidthBound, lowerWidthBound, depth);
        directoryTreeGenerator.createRecursiveDirectoryTree(directoryTreeGenerator.makeFilesAndDirectories(file), 0);
        System.out.println("There are " + directoryTreeGenerator.getOccurrences() + " occurrences of the word " + word);
        System.out.println("Directory tree size is: " + Constants.sizeOfTree);
    }
}
