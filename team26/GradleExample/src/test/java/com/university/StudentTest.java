package com.university;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void testStudentCreation() {
        Student student = new Student("Иванов", 20);
        assertEquals("Иванов", student.getName());
        assertEquals(20, student.getAge());
    }
}
