package ru.enovikow.resume_database.storage.impl;

import ru.enovikow.resume_database.model.Resume;

public class ArrayStorageImpl extends AbstractArrayStorage {

    protected int getElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        storage[size++] = resume;
    }

    @Override
    protected void deleteElementFromStorage(int elementIndex) {
        if (elementIndex < size - 1) {
            storage[elementIndex] = storage[size - 1];
        }
        storage[size--] = null;
    }
}
