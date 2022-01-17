package com.mockito.mockito.examples;

import com.mockito.mockito.domain.entity.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ExampleDontMockValueTypes {

    @Test
    void mockCity() {
        String cityName = "MockTown";
        City mockTown = Mockito.mock(City.class);
        Mockito.when(mockTown.getName()).thenReturn(cityName);
        Assertions.assertEquals(cityName, mockTown.getName());
    }

}
