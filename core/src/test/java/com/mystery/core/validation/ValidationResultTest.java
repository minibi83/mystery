package com.mystery.core.validation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {

    @Test
    public void isValid_true() {
        ValidationResult sut = ValidationResult.ok();
        assertTrue(sut.isValid());
    }

    @Test
    public void isInValid_true() {
        ValidationResult sut = ValidationResult.error("test1");
        assertTrue(sut.isInvalid());
    }

    @Test
    public void error_single() {
        ValidationResult sut = ValidationResult.error("test1");
        assertTrue(sut.isInvalid());
        assertEquals(sut.getErrors().size(), 1);
        assertEquals(sut.getErrors().get(0), "test1");
    }

    @Test
    public void error_collection() {
        Collection<String> testValue = Arrays.asList("test1", "test2");
        ValidationResult sut = ValidationResult.error(testValue);
        assertTrue(sut.isInvalid());
        assertEquals(sut.getErrors().size(), 2);
        assertEquals(sut.getErrors().get(0), "test1");
        assertEquals(sut.getErrors().get(1), "test2");
    }

    @Test
    public void addError() {
        ValidationResult sut = ValidationResult.error("test1");
        sut.addError("test2");
        assertTrue(sut.isInvalid());
        assertEquals(sut.getErrors().size(), 2);
        assertEquals(sut.getErrors().get(0), "test1");
        assertEquals(sut.getErrors().get(1), "test2");
    }

    @Test
    public void checkAndCollect_OK() {
        ValidationResult sut = ValidationResult.ok();
        sut = sut.checkAndCollect(ValidationResult.ok());
        assertTrue(sut.isValid());
    }

    @Test
    public void checkAndCollect_Error1() {
        ValidationResult sut = ValidationResult.ok();
        sut = sut.checkAndCollect(ValidationResult.error("test1"));
        assertTrue(sut.isInvalid());
        assertEquals(sut.getErrors().size(), 1);
        assertEquals(sut.getErrors().get(0), "test1");
    }

    @Test
    public void checkAndCollect_Error2() {
        ValidationResult sut = ValidationResult.error("test1");
        sut = sut.checkAndCollect(ValidationResult.ok());
        assertTrue(sut.isInvalid());
        assertEquals(sut.getErrors().size(), 1);
        assertEquals(sut.getErrors().get(0), "test1");
    }

    @Test
    public void collect() {
        ValidationResult value1 = ValidationResult.ok();
        ValidationResult value2 = ValidationResult.error("test2");
        ValidationResult value3 = ValidationResult.error("test1");
        ValidationResult sut = ValidationResult.collect(value1, value2, value3);
        assertTrue(sut.isInvalid());
        assertEquals(sut.getErrors().size(), 2);
        assertEquals(sut.getErrors().get(0), "test2");
        assertEquals(sut.getErrors().get(1), "test1");
    }


}