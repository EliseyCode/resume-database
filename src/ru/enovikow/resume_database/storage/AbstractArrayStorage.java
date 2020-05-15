package ru.enovikow.resume_database.storage;

import ru.enovikow.resume_database.exception.ExistStorageException;
import ru.enovikow.resume_database.exception.NotExistStorageException;
import ru.enovikow.resume_database.exception.StorageException;
import ru.enovikow.resume_database.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    private static final String UUID_NOT_EXIST_ERROR_MESSAGE = "Resume with current uuid does't exist in storage";
    private static final String UUID_ALREADY_EXIST_ERROR_MESSAGE = "Resume with current uuid already exist in storage";
    private static final String STORAGE_OVERFLOW_ERROR_MESSAGE = "Storage overflow";

    private static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public Resume get(String uuid) {
        int elementIndex = getElementIndex(uuid);

        if (isResumeExistInStorage(elementIndex)) {
            return storage[elementIndex];
        } else {
            throw new NotExistStorageException(UUID_NOT_EXIST_ERROR_MESSAGE, uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume resume) {
        int elementIndex = getElementIndex(resume.getUuid());
        if (!isResumeExistInStorage(elementIndex)) {
            insertElement(resume, elementIndex);
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException(STORAGE_OVERFLOW_ERROR_MESSAGE, resume.getUuid());
        } else {
            throw new ExistStorageException(UUID_ALREADY_EXIST_ERROR_MESSAGE, resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int elementIndex = getElementIndex(resume.getUuid());

        if (isResumeExistInStorage(elementIndex)) {
            storage[elementIndex] = resume;
        } else {
            throw new NotExistStorageException(UUID_NOT_EXIST_ERROR_MESSAGE, resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int elementIndex = getElementIndex(uuid);

        if (isResumeExistInStorage(elementIndex)) {
            deleteElementFromStorage(elementIndex);
        } else {
            throw new NotExistStorageException(UUID_NOT_EXIST_ERROR_MESSAGE, uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    protected boolean isResumeExistInStorage(int elementIndex) {
        return elementIndex >= 0;
    }

    public int size() {
        return size;
    }

    protected abstract int getElementIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElementFromStorage(int elementIndex);
}
