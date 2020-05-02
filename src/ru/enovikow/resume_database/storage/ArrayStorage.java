package ru.enovikow.resume_database.storage;

import ru.enovikow.resume_database.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size = 0;
    private static final String UUID_NOT_EXIST_ERROR_MESSAGE = "Resume with current uuid does't exist in storage";
    private static final String UUID_EXIST_ERROR_MESSAGE = "Resume with current uuid already exist in storage";

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume r) {
        int elementIndex = getElementIndex(r.getUuid());

        if (isResumeExistInStorage(elementIndex)) {
            storage[elementIndex].setUuid(r.getUuid());
        } else {
            System.out.println(UUID_NOT_EXIST_ERROR_MESSAGE);
        }
    }

    public void save(Resume r) {
        int elementIndex = getElementIndex(r.getUuid());
        if (!isResumeExistInStorage(elementIndex)) {
            storage[size++] = r;
        } else {
            System.out.println(UUID_EXIST_ERROR_MESSAGE);
        }
    }

    public Resume get(String uuid) {
        int elementIndex = getElementIndex(uuid);

        if (isResumeExistInStorage(elementIndex)) {
            return storage[elementIndex];
        } else {
            System.out.println(UUID_NOT_EXIST_ERROR_MESSAGE);
            return null;
        }
    }

    public void delete(String uuid) {
        int elementIndex = getElementIndex(uuid);

        if (isResumeExistInStorage(elementIndex)) {
            storage[elementIndex] = storage[size - 1];
            storage[size--] = null;
        } else {
            System.out.println(UUID_NOT_EXIST_ERROR_MESSAGE);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isResumeExistInStorage(int elementIndex) {
        return elementIndex >= 0;
    }
}
