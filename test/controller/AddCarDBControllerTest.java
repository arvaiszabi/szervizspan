package controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AddCarDBControllerTest {

    @ParameterizedTest
    @ValueSource(strings = {"ABC123","ABC 123","a1b-234","abc-1a3","ABC","ABC0123"})
    void plateFormatCheckShouldReturnFalseForStrings(String string) {
        assertFalse(AddCarDBController.PlateFormatCheck(string), "Rendszám: "+ string);
    }
}