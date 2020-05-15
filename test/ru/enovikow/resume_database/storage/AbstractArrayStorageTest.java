package ru.enovikow.resume_database.storage;

import org.junit.Before;
import org.junit.Test;
import ru.enovikow.resume_database.exception.ExistStorageException;
import ru.enovikow.resume_database.exception.NotExistStorageException;
import ru.enovikow.resume_database.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private final static Resume RESUME_1 = new Resume(UUID_1);
    private final static Resume RESUME_2 = new Resume(UUID_2);
    private final static Resume RESUME_3 = new Resume(UUID_3);
    private final static Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(RESUME_2, storage.get(UUID_2));
        assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        Resume[] tempStorage = storage.getAll();

        assertEquals(storage.size(), tempStorage.length);

        for (int i = 0; i < storage.size(); i++) {
            Resume resume = tempStorage[i];
            assertEquals(resume, storage.get(resume.getUuid()));
        }
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertSame(RESUME_4, storage.get(RESUME_4.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(RESUME_3);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test
    public void delete() {
        assertEquals(3, storage.size());
        assertSame(RESUME_2, storage.get(RESUME_2.getUuid()));
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void clear() {
        assertEquals(3, storage.size());
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}