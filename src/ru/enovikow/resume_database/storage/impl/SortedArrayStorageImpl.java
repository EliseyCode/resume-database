package ru.enovikow.resume_database.storage.impl;

import ru.enovikow.resume_database.model.Resume;

import static java.lang.Math.abs;
import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

public class SortedArrayStorageImpl extends AbstractArrayStorage {

    @Override
    protected int getElementIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);

        return binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertionIndex = abs(index + 1);

        if (insertionIndex == 0) {
            storage[0] = resume;
        } else {
            arraycopy(storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex);
            storage[insertionIndex] = resume;
        }
        size++;
    }

    @Override
    protected void deleteElementFromStorage(int elementIndex) {
        arraycopy(storage, elementIndex + 1, storage, elementIndex, (size - 1 - elementIndex));
        --size;
    }
}
