package com.example;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LionParametrizedTests {
    @Mock
    private Feline feline;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 15})
    @DisplayName("getKittens возвращает различные количества котят")
    void getKittensWithDifferentQuantityReturnCorrectValues(int kittenCount) throws Exception {
        Mockito.when(feline.getKittens()).thenReturn(kittenCount);
        Lion lion = new Lion("Самец", feline);
        int result = lion.getKittens();
        assertEquals(kittenCount, result);
        Mockito.verify(feline).getKittens();
    }
}
