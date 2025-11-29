package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class FelineTests {


    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = new Feline();
    }

    // Тесты для метода getFamily()
    @Test
    @DisplayName("getFamily возвращает семейство Кошачьи")
    void getFamilyReturnFeline() {
        String family = feline.getFamily();
        assertEquals("Кошачьи", family);
    }

    @Test
    @DisplayName("getFamily не возвращает null")
    void getFamilyNotReturnNull() {
        String family = feline.getFamily();
        assertNotNull(family, "Семейство Кошачьих не должно быть null");
    }

    @Test
    @DisplayName("Повторные вызовы getFamily возвращают один и тот же результат")
    void getFamilyWhenRepeatCallReturnTheSameResult() {
        String firstCall = feline.getFamily();
        String secondCall = feline.getFamily();
        String thirdCall = feline.getFamily();

        assertEquals("Кошачьи", firstCall);
        assertEquals("Кошачьи", secondCall);
        assertEquals("Кошачьи", thirdCall);
        assertEquals(firstCall, secondCall);
        assertEquals(secondCall, thirdCall);

    }

    // Тесты для метода eatMeat()
    @Test
    @DisplayName("eatMeat вызывает Animal.getFood с семейством Хищник")
    void eatMeatChargeAnimalGetFood() throws Exception{
        try (MockedStatic<Animal> animalMock = mockStatic(Animal.class)) {
            List<String> expectFood = List.of("Животные", "Птицы", "Рыба");
            animalMock.when(() -> Animal.getFood("Хищник")).thenReturn(expectFood);

        List<String> actualFood = feline.eatMeat();
        assertEquals(expectFood, actualFood, "Список еды должен совпадать с тем, что возвращаетAnimal.getFood");
        animalMock.verify(() -> Animal.getFood("Хищник"), Mockito.times(1));

        }
    }


    // Тесты для метода getKittens()
    @Test
    @DisplayName("getKittens без параметров возвращает 1")
    void getKittensNoParametersReturnDefaultValue() {
        int kittensCount = feline.getKittens();
        assertEquals(1, kittensCount);
    }

    @Test
    @DisplayName("getKittens без параметров возвращает положительное число")
    void getKittensNoParametersReturnPositiveNumber() {
        int kittensCount = feline.getKittens();
        assertTrue(kittensCount > 0);
    }


    // Тесты для метода getKittens() с параметром
    @Test
    @DisplayName("getKittens с параметром возвращает переданное значение")
    void getKittensWithParameterReturnInputValue() {
        int expectCount = 3;
        int actualCount = feline.getKittens(expectCount);
        assertEquals(expectCount, actualCount);
    }

    @Test
    @DisplayName("getKittens с параметром 0 возвращает 0")
    void getKittensWithZeroParameterReturnZero() {
        int kittensCount = feline.getKittens(0);
        assertEquals(0, kittensCount);
    }

}