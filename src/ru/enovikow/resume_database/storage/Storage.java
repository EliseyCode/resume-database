package ru.enovikow.resume_database.storage;

import ru.enovikow.resume_database.model.Resume;

public interface Storage {

    Resume get(String uuid);

    Resume[] getAll();

    void save(Resume r);

    void update(Resume r);

    void delete(String uuid);

    void clear();

    int size();
}
