package bg.rashev.traversal;

/**
 * Created by CyberSirius on 11-Jan-16.
 */
class Constants {
    public static final String ROOT_DIRECTORY = "D:\\TestDirectoryTree";
    public static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";//// TODO: 11-Jan-16 use ASCII
    public static final int CHARS_ON_LINE = 150;//defaultExpectedLineSize in BufferedReader is 80
    public static final int MAX_RATIO_NUMBER = 1000000;
    public static final int MIN_FILE_SIZE = 300;
    public static final int MAX_FILE_SIZE = 5000000;
    public static final long MAX_TREE_SIZE = 1024 * 1024 * 1024 * 2L;
    public static final int LINE_TOLERANCE = 10;
    public static long sizeOfTree = 0L;

    public static void increaseTreeSize(int increase) {
        sizeOfTree += increase;
    }
}
