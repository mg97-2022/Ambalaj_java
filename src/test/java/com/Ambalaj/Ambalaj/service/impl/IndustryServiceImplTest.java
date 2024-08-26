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
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void addIndustry_WhenIndustryNotExists_ShouldAddIndustry() {
        // given
        when(industryRepository.save(mockIndustry)).thenReturn(mockIndustry);

        // when
        IndustryEntity result = underTest.addIndustry(mockIndustry);

        // then
        assertThat(result).isEqualTo(mockIndustry);
        verify(industryRepository, times(1)).save(mockIndustry);
    }

    @Test
    void addIndustry_WhenIndustryExists_ShouldThrownBadRequestException() {
        // given
        when(industryRepository.save(mockIndustry)).thenThrow(DataIntegrityViolationException.class);

        // when & then
        assertThrows(DataIntegrityViolationException.class, () -> underTest.addEntity(mockIndustry));
        verify(industryRepository, times(1)).save(mockIndustry);
    }

    @Test
    void getAllIndustries_ShouldReturnListOfIndustries() {
        // given
        when(industryRepository.findAll()).thenReturn(mockIndustries);

        // when
        List<IndustryEntity> result = underTest.getIndustries();

        // then
        assertThat(result.size()).isEqualTo(mockIndustries.size());
        assertThat(result).isEqualTo(mockIndustries);
        verify(industryRepository, times(1)).findAll();
    }

    @Test
    void getIndustryById_WhenIndustryExists_ShouldReturnIndustry() {
        // given
        when(industryRepository.findById(mockIndustry.getId())).thenReturn(Optional.of(mockIndustry));

        // when
        IndustryEntity result = underTest.getIndustryById(mockIndustry.getId());

        // then
        assertThat(result).isEqualTo(mockIndustry);
        verify(industryRepository, times(1)).findById(mockIndustry.getId());
    }

    @Test
    void getIndustryById_WhenIndustryNotExist_ShouldThrowNotFoundException() {
        // given
        Long notExistsId = 10L;
        when(industryRepository.findById(notExistsId)).thenThrow(
                new NotFoundException("Industry", notExistsId));

        // when & then
        NotFoundException exception =
                assertThrows(NotFoundException.class, () -> underTest.getIndustryById(notExistsId));
        assertThat("Industry not found with ID: " + notExistsId).isEqualTo(exception.getMessage());
        verify(industryRepository, times(1)).findById(notExistsId);
    }

    @Test
    void updateIndustry_WhenIndustryExist_ShouldUpdateIndustry() {
        // given
        IndustryEntity updatedIndustry = new IndustryEntity();
        updatedIndustry.setId(1L);
        updatedIndustry.setName("Updated Industry");

        when(industryRepository.findById(updatedIndustry.getId())).thenReturn(Optional.of(mockIndustry));
        when(industryRepository.save(mockIndustry)).thenReturn(mockIndustry);

        // when
        IndustryEntity result = underTest.updateIndustry(updatedIndustry, updatedIndustry.getId());

        // then
        assertThat("Updated Industry").isEqualTo(result.getName());
        verify(industryRepository, times(1)).findById(updatedIndustry.getId());
        verify(industryRepository, times(1)).save(mockIndustry);
    }

    @Test
    void updateIndustry_WhenIndustryNotExist_ShouldThrowNotFoundException() {
        // given
        Long notExistsId = 10L;
        when(industryRepository.findById(notExistsId)).thenThrow(
                new NotFoundException("Industry", notExistsId));

        // when & then
        NotFoundException exception =
                assertThrows(NotFoundException.class, () -> underTest.updateIndustry(mockIndustry, notExistsId));
        assertThat("Industry not found with ID: " + notExistsId).isEqualTo(exception.getMessage());
        verify(industryRepository, times(1)).findById(notExistsId);
        verify(industryRepository, never()).save(any());
    }

    @Test
    void deleteIndustry_WhenIndustryExists_ShouldDeleteIndustry() {
        // given
        when(industryRepository.existsById(mockIndustry.getId())).thenReturn(true);

        // when
        underTest.deleteIndustry(mockIndustry.getId());

        // then
        verify(industryRepository, times(1)).existsById(mockIndustry.getId());
        verify(industryRepository, times(1)).deleteById(mockIndustry.getId());
    }

    @Test
    void deleteIndustry_WhenIndustryNotExist_ShouldThrowNotFoundException() {
        // given
        Long notExistsId = 10L;
        when(industryRepository.existsById(notExistsId)).thenReturn(false);

        // when & then
        NotFoundException exception =
                assertThrows(NotFoundException.class, () -> underTest.deleteIndustry(notExistsId));
        assertThat("Industry not found with ID: " + notExistsId).isEqualTo(exception.getMessage());
        verify(industryRepository, times(1)).existsById(notExistsId);
        verify(industryRepository, never()).deleteById(any());
    }
}