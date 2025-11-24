package com.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class FelineParametrizedTests {

    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = new Feline();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10, 50, 100, 2000})
    @DisplayName("getKittens возвращает переданные значения")
    void getKittensWithParametersReturnPassedValues(int kittensCount) {
        int result = feline.getKittens(kittensCount);
        assertEquals(kittensCount, result);
    }



}
