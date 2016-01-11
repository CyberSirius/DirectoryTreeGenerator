package bg.rashev.traversal;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by CyberSirius on 10-Jan-16.
 */
class DirectoryTreeGenerator {
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final String word;
    private final int randomParameter;
    private final int lowerWidthBound;
    private final int upperWidthBound;
    private final int depth;
    private final int ratioDirectories;
    private final int ratioFiles;
    private int counterAllOccurrencesOfWord = 0;

    public DirectoryTreeGenerator(String word, int randomParameter, int ratioFiles, int ratioDirectories, int upperWidthBound, int lowerWidthBound, int depth) {
        this.ratioFiles = ratioFiles;
        this.ratioDirectories = ratioDirectories;
        this.upperWidthBound = upperWidthBound;
        this.lowerWidthBound = lowerWidthBound;
        this.word = word;
        this.randomParameter = randomParameter;
        this.depth = depth;
    }

    private void makeFile(File root) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(root.getAbsolutePath() + "\\" + randomGenerator.randomString(randomGenerator.generateRandomNumber(5, 15)) + ".txt"), "utf-8"))) {
            int randomFileSize = randomGenerator.generateRandomNumber(Constants.MIN_FILE_SIZE, Constants.MAX_FILE_SIZE);
            Constants.increaseTreeSize(randomFileSize);
            int numberOfLines = randomFileSize / Constants.CHARS_ON_LINE;
            for (int i = 0; i < numberOfLines; i++) {
                if (randomGenerator.generateBoolean(randomParameter, Constants.MAX_RATIO_NUMBER)) {
                    counterAllOccurrencesOfWord++;
                    int randomLinePosition = randomGenerator.generateRandomNumber(Constants.CHARS_ON_LINE - word.length());
                    writer.write(randomGenerator.randomString(randomLinePosition));
                    writer.write(word);
                    writer.write(randomGenerator.randomString(Constants.CHARS_ON_LINE - word.length() - randomLinePosition));
                } else {
                    writer.write(randomGenerator.randomString(randomGenerator.generateRandomNumber(Constants.CHARS_ON_LINE - Constants.LINE_TOLERANCE, Constants.CHARS_ON_LINE)));
                }
                writer.newLine();
            }
            writer.close();
        }
    }

    public ArrayList<File> makeFilesAndDirectories(File root) throws IOException {
        int numberOfFilesAndDirectories = randomGenerator.generateRandomNumber(this.lowerWidthBound, this.upperWidthBound);
        ArrayList<File> directoriesCreated = new ArrayList<>();
        if (Constants.sizeOfTree <= Constants.MAX_TREE_SIZE) {
            for (int i = 0; i < numberOfFilesAndDirectories; i++) {
                if (randomGenerator.generateBoolean(this.ratioDirectories, this.ratioDirectories + this.ratioFiles)) {
                    File dir = new File(root.getAbsolutePath() + "\\" + randomGenerator.randomString(randomGenerator.generateRandomNumber(5, 15)));
                    if (dir.mkdir()) {
                        System.out.println("Successfully created directory: " + dir.getName());
                        directoriesCreated.add(dir);
                    }
                } else {
                    makeFile(root);
                }
            }
        }
        return directoriesCreated;
    }

    public void createRecursiveDirectoryTree(ArrayList<File> directories, int subTreeDepth) throws IOException {
        subTreeDepth++;
        if (Constants.sizeOfTree <= Constants.MAX_TREE_SIZE) {
            if (this.depth == subTreeDepth || randomGenerator.generateRandomNumber(0, this.depth) < subTreeDepth) {
                for (File directory : directories) makeFilesAndDirectories(directory);
            } else {
                for (File directory : directories)
                    createRecursiveDirectoryTree(makeFilesAndDirectories(directory), subTreeDepth);
            }
        }
    }

    public int getOccurrences() {
        return counterAllOccurrencesOfWord;
    }
}