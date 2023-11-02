package com.nikozka.app;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertiesLoaderTest {

    @Test
    public void loadPropertiesTestCorrectLoad(){
        PropertiesLoader testObj = new PropertiesLoader();
        testObj.loadProperties("test.properties");
        assertEquals("user", testObj.getProperty("user"));
    }

    @Test
    public void loadPropertiesTestIncorrectLoad(){
        PropertiesLoader testObj = new PropertiesLoader();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> testObj.loadProperties("incorrect.properties"));
        assertEquals("Error while loading file ", illegalArgumentException.getLocalizedMessage());
    }
}
