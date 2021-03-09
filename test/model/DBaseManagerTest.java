package model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DBaseManagerTest {

   private static Gepjarmu testcar = new Gepjarmu("Gipsz Jakab", "+0020000000", "TEST", "CAR", "ABC-123", "Nem jó!", false);

    @Test
    @Order(1)
    void DBaseCarAdd() {
        assertDoesNotThrow(() -> DBaseManager.DBaseCarAdd(testcar));
    }
    @Order(2)
    @Test
    void DBaseSearch() {
        ArrayList<Gepjarmu> TestList = DBaseManager.DBaseSearch(testcar.getRendszam());
        Assertions.assertFalse(TestList.isEmpty());
    }
    @Order(3)
    @Test
    void carDBUpdate() {
        Gepjarmu mod_car = new Gepjarmu("Gipsz Jakab", "+0020000000", "MOD_TEST", "CAR", "ABC-123", "Nem jó!", false);
        assertDoesNotThrow(() -> DBaseManager.CarDBUpdate(mod_car));
        ArrayList<Gepjarmu> TestList = DBaseManager.DBaseSearch("MOD_TEST");
        Assertions.assertFalse(TestList.isEmpty());
        TestList = DBaseManager.DBaseSearch(testcar.getGyarto());
        Assertions.assertTrue(TestList.isEmpty());
    }
    @AfterAll
    public static void DBaseDelete() {
        assertDoesNotThrow(() -> DBaseManager.DBaseDelete(testcar.getRendszam()));
    }
}
