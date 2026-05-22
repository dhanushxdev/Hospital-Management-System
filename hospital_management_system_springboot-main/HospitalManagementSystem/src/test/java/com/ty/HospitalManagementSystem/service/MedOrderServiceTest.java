package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.Encounter;
import com.ty.HospitalManagementSystem.Entity.Medorder;
import com.ty.HospitalManagementSystem.dao.Medorderdao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedOrderServiceTest {

    @Mock
    private Medorderdao medorderdao;

    @InjectMocks
    private MedOrderService medOrderService;

    private Medorder medorder;
    private Encounter encounter;

    @BeforeEach
    void setUp() {

        encounter = new Encounter();
        encounter.setId(1);

        medorder = new Medorder();

        medorder.setId(1);
        medorder.setDate(LocalDate.now());
        medorder.setDoctor("Dr. Smith");
        medorder.setEncounter(encounter);
    }

    @Test
    void testSaveMedorder() {

        when(medorderdao.saveMedorder(medorder, 1))
                .thenReturn(medorder);

        Medorder savedMedorder =
                medOrderService.saveMedorder(1, medorder);

        assertNotNull(savedMedorder);
        assertEquals(1, savedMedorder.getId());
        assertEquals("Dr. Smith",
                savedMedorder.getDoctor());

        verify(medorderdao, times(1))
                .saveMedorder(medorder, 1);
    }

    @Test
    void testUpdateMedorder() {

        when(medorderdao.getmedorderbyid(1))
                .thenReturn(medorder);

        when(medorderdao.updateMedorder(1, medorder))
                .thenReturn(medorder);

        Medorder updatedMedorder =
                medOrderService.updateMedorder(1, medorder);

        assertNotNull(updatedMedorder);
        assertEquals(1, updatedMedorder.getId());
        assertEquals(encounter,
                updatedMedorder.getEncounter());

        verify(medorderdao, times(1))
                .getmedorderbyid(1);

        verify(medorderdao, times(1))
                .updateMedorder(1, medorder);
    }

    @Test
    void testUpdateMedorderReturnsNull() {

        when(medorderdao.getmedorderbyid(1))
                .thenReturn(medorder);

        when(medorderdao.updateMedorder(1, medorder))
                .thenReturn(null);

        Medorder updatedMedorder =
                medOrderService.updateMedorder(1, medorder);

        assertNull(updatedMedorder);

        verify(medorderdao, times(1))
                .updateMedorder(1, medorder);
    }

    @Test
    void testDeleteMedorder() {

        when(medorderdao.deletemedorder(1))
                .thenReturn(medorder);

        Medorder deletedMedorder =
                medOrderService.deleteMedorder(1);

        assertNotNull(deletedMedorder);
        assertEquals(1, deletedMedorder.getId());

        verify(medorderdao, times(1))
                .deletemedorder(1);
    }

    @Test
    void testDeleteMedorderReturnsNull() {

        when(medorderdao.deletemedorder(1))
                .thenReturn(null);

        Medorder deletedMedorder =
                medOrderService.deleteMedorder(1);

        assertNull(deletedMedorder);

        verify(medorderdao, times(1))
                .deletemedorder(1);
    }

    @Test
    void testGetMedorderById() {

        when(medorderdao.getmedorderbyid(1))
                .thenReturn(medorder);

        Medorder fetchedMedorder =
                medOrderService.getMedorderbyid(1);

        assertNotNull(fetchedMedorder);
        assertEquals(1, fetchedMedorder.getId());

        verify(medorderdao, times(1))
                .getmedorderbyid(1);
    }

    @Test
    void testGetMedorderByIdReturnsNull() {

        when(medorderdao.getmedorderbyid(1))
                .thenReturn(null);

        Medorder fetchedMedorder =
                medOrderService.getMedorderbyid(1);

        assertNull(fetchedMedorder);

        verify(medorderdao, times(1))
                .getmedorderbyid(1);
    }
}