package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LionTests {

    @Mock
    private Feline feline;

    private Lion maleLion;
    private Lion femaleLion;

    @BeforeEach
    void setUp() throws Exception {
        maleLion = new Lion("Самец", feline);
        femaleLion = new Lion("Самка", feline);
    }

    @Test
    @DisplayName("Конструктор создает льва c гривой при передаче 'Самец'")
    void constructorMaleWithMane() throws Exception{
        assertTrue(maleLion.doesHaveMane());
    }

    @Test
    @DisplayName("Конструктор создает льва без гривы при передаче 'Самка'")
    void constructorFemaleNoMane() throws Exception{
        assertFalse(femaleLion.doesHaveMane());
    }

    @Test
    @DisplayName("Конструктор выкидывает исключение")
    void constructorInvalidGenderTrowException() {
        String invalidGender = "Неизвестно";
        Exception exception = assertThrows(Exception.class, () -> new Lion(invalidGender, feline));

        String expectMessage = "Используйте допустимые значения пола животного - самец или самка";
        assertEquals(expectMessage, exception.getMessage());
    }


    // Тесты для метода doesHaveMane()
    @Test
    @DisplayName("doesHaveMane возвращает true для самца льва")
    void doesHaveManeMaleLionReturnTrue() {
        boolean hasMane = maleLion.doesHaveMane();
        assertTrue(hasMane);
    }

    @Test
    @DisplayName("doesHaveMane возвращает false для самки, гривы нет")
    void doesHaveManeFemaleLionReturnFalse() {
        boolean hasMane = femaleLion.doesHaveMane();
        assertFalse(hasMane);
    }

    // Тесты для метода getKittens()
    @Test
    @DisplayName("getKittens работает идентично для самца и для самки льва")
    void getKittensForMaleFemaleMustIdenticalBehavior() {
        int expectKittens = 3;
        Mockito.when(feline.getKittens()).thenReturn(expectKittens);

        int maleKittens = maleLion.getKittens();
        assertEquals(expectKittens, maleKittens);

        int femaleKittens = femaleLion.getKittens();
        assertEquals(expectKittens, femaleKittens);
        Mockito.verify(feline, Mockito.times(2)).getKittens();

    }

    // Тесты для метода getFood()
    @Test
    void getFoodDelegatesCallPredator() throws Exception {
        List<String> expectFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(expectFood);

        List<String> actualFood = maleLion.getFood();

        assertEquals(expectFood, actualFood);
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }
}