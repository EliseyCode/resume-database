package ru.enovikow.resume_database.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String message, String uuid) {
        super(message, uuid);
    }
}
