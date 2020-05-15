package ru.enovikow.resume_database.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String message, String uuid) {
        super(message, uuid);
    }
}
