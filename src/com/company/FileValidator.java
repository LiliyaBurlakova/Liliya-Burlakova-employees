package com.company;

import com.company.exceptions.UnexceptedFileException;

import java.io.File;

import static com.company.constants.Constants.EMPTY_FILE_EXCEPTION;
import static com.company.constants.Constants.FILE_NOT_EXIST_EXCEPTION;

public class FileValidator {

    public static void checkIfFileIsEmpty(File file) throws UnexceptedFileException {
        if (file.length() == 0)
        {
            throw new UnexceptedFileException(EMPTY_FILE_EXCEPTION);
        }
    }

    public static void checkIfFileExist(File file) throws UnexceptedFileException {
        if (file.exists())
        {
            throw new UnexceptedFileException(FILE_NOT_EXIST_EXCEPTION);
        }
    }
}
