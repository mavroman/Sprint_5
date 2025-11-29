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
class CatTests {

    @Mock
    private Feline feline;

    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat(feline);
    }

    // Тесты для метода getSound()
    @Test
    @DisplayName("getSound возвращает правильный звук")
    void getSoundReturnMay() {
        String sound = cat.getSound();
        assertEquals("Мяу", sound, "Кот должен говорить 'Мяу'");
    }

    @Test
    @DisplayName("getSound не возвращает null")
    void getSoundNotReturnNull() {
        String sound = cat.getSound();
        assertNotNull(sound, "Звук кота не должен быть null");
    }

    @Test
    @DisplayName("Повторные вызовы getSound возвращают один и тот же результат")
    void getSoundWhenRepeatCallReturnTheSameResult() {
        String firstCall = cat.getSound();
        String secondCall = cat.getSound();
        String thirdCall = cat.getSound();

        assertEquals("Мяу", firstCall);
        assertEquals("Мяу", secondCall);
        assertEquals("Мяу", thirdCall);
        assertEquals(firstCall, secondCall);
        assertEquals(secondCall, thirdCall);
    }


    // Тесты для метода getFood()

    @Test
    @DisplayName("getFood вызывает eatMeat у Feline один раз")
    void getFoodWhenCallToFelineEatMeat() throws Exception {
        List<String> expectFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.eatMeat()).thenReturn(expectFood);
        List<String> actualFood = cat.getFood();
        assertEquals(expectFood, actualFood, "Список еды совпадает с тем, что вернул feline");
        Mockito.verify(feline, Mockito.times(1)).eatMeat();
    }

    @Test
    @DisplayName("getFood возвращает null когда feline возвращает null")
    void getFoodNullReturnNull() throws Exception {
        Mockito.when(feline.eatMeat()).thenReturn(null);
        List<String> result = cat.getFood();
        assertNull(result);
        Mockito.verify(feline).eatMeat();
    }




}