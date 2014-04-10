package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Nami
 */
public class Main {
    private static LehoLoginHandler loginHandler;
    public static void main(String[] args) throws IOException, FileNotFoundException {
        loginHandler = LehoLoginHandler.getInstance();
    }
}
