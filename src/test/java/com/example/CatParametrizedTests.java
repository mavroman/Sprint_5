package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@ExtendWith(MockitoExtension.class)
public class CatParametrizedTests {

    @Mock
    private Feline feline;
    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat(feline);
    }

    // Параметризированные тесты для метода getSound()

    @ParameterizedTest
    @ValueSource(strings = {"Мяя", "Му", "Гав", "", "Рррр", "Хрю"})
    @DisplayName("getSound не возвращает звуки других животных, кроме звука кота")
    void getSoundReturnOnlyMay(String wrongSound) {
        String actualSound = cat.getSound();
        assertNotEquals(wrongSound, actualSound);
    }



}
