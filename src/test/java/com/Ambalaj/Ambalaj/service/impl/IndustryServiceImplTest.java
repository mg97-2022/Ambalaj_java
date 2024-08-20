package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.repository.IndustryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndustryServiceImplTest {

    @Mock
    private IndustryRepository industryRepository;

    @InjectMocks
    private IndustryServiceImpl underTest;

    private List<IndustryEntity> mockIndustries;
    private IndustryEntity mockIndustry;

    @BeforeEach
    void setUp() {
        mockIndustries = new ArrayList<>();

        IndustryEntity industry1 = new IndustryEntity();
        industry1.setId(1L);
        industry1.setName("industry1");
        mockIndustries.add(industry1);
        IndustryEntity industry2 = new IndustryEntity();
        industry2.setId(2L);
        mockIndustries.add(industry2);
        IndustryEntity industry3 = new IndustryEntity();
        industry3.setId(3L);
        mockIndustries.add(industry3);

        mockIndustry = mockIndustries.getFirst();
    }

    @Test
    void getIndustriesByIds_WhenAllIdsExists_ShouldReturnsListOfIndustries() {
        // given
        List<Long> ids = List.of(1L, 2L, 3L);
        when(industryRepository.findAllById(ids)).thenReturn(mockIndustries);

        // when
        List<IndustryEntity> result = underTest.getIndustriesByIds(ids);

        // then
        assertThat(result).isEqualTo(mockIndustries);
        verify(industryRepository, times(1)).findAllById(ids);
    }

    @Test
    void getIndustriesByIds_WhenIdsNotExists_ShouldThrowNotFoundException() {
        // given
        List<Long> ids = List.of(1L, 2L, 3L, 4L);
        when(industryRepository.findAllById(ids)).thenThrow(
                new NotFoundException("Industries not found for IDs: " + new ArrayList<Long>().add(4L)));

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.getIndustriesByIds(ids));
        assertThat("Industries not found for IDs: " + new ArrayList<Long>().add(4L)).isEqualTo(exception.getMessage());
        verify(industryRepository, times(1)).findAllById(ids);
    }

    @Test
    void getId_ShouldReturnEntityId() {
        // when
        Long result = underTest.getId(mockIndustry);

        // then
        assertThat(result).isEqualTo(mockIndustry.getId());
    }
}