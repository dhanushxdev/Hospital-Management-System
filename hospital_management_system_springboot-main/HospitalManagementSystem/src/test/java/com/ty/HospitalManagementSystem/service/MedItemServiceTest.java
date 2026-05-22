package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.MedItems;
import com.ty.HospitalManagementSystem.Entity.Medorder;
import com.ty.HospitalManagementSystem.dao.MedItemsdao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedItemServiceTest {

    @Mock
    private MedItemsdao medItemsdao;

    @InjectMocks
    private MedItemService medItemService;

    private MedItems medItems;
    private Medorder medorder;

    @BeforeEach
    void setUp() {

        medorder = new Medorder();
        medorder.setId(1);

        medItems = new MedItems();

        medItems.setId(1);
        medItems.setName("Paracetamol");
        medItems.setCost(50.0);
        medItems.setMedorder(medorder);
    }

    @Test
    void testSaveMedItems() {

        when(medItemsdao.saveMedItems(medItems, 1))
                .thenReturn(medItems);

        MedItems savedMedItems =
                medItemService.saveMedItems(1, medItems);

        assertNotNull(savedMedItems);
        assertEquals(1, savedMedItems.getId());
        assertEquals("Paracetamol",
                savedMedItems.getName());
        assertEquals(50.0,
                savedMedItems.getCost());

        verify(medItemsdao, times(1))
                .saveMedItems(medItems, 1);
    }

    @Test
    void testUpdateMedItems() {

        when(medItemsdao.getMedItemsbyid(1))
                .thenReturn(medItems);

        when(medItemsdao.updateMedItems(1, medItems))
                .thenReturn(medItems);

        MedItems updatedMedItems =
                medItemService.updateMedItems(1, medItems);

        assertNotNull(updatedMedItems);
        assertEquals(1, updatedMedItems.getId());
        assertEquals(medorder,
                updatedMedItems.getMedorder());

        verify(medItemsdao, times(1))
                .getMedItemsbyid(1);

        verify(medItemsdao, times(1))
                .updateMedItems(1, medItems);
    }

    @Test
    void testUpdateMedItemsReturnsNull() {

        when(medItemsdao.getMedItemsbyid(1))
                .thenReturn(medItems);

        when(medItemsdao.updateMedItems(1, medItems))
                .thenReturn(null);

        MedItems updatedMedItems =
                medItemService.updateMedItems(1, medItems);

        assertNull(updatedMedItems);

        verify(medItemsdao, times(1))
                .updateMedItems(1, medItems);
    }

    @Test
    void testDeleteMedItems() {

        when(medItemsdao.deletemedItems(1))
                .thenReturn(medItems);

        MedItems deletedMedItems =
                medItemService.deleteMedItems(1);

        assertNotNull(deletedMedItems);
        assertEquals(1, deletedMedItems.getId());

        verify(medItemsdao, times(1))
                .deletemedItems(1);
    }

    @Test
    void testDeleteMedItemsReturnsNull() {

        when(medItemsdao.deletemedItems(1))
                .thenReturn(null);

        MedItems deletedMedItems =
                medItemService.deleteMedItems(1);

        assertNull(deletedMedItems);

        verify(medItemsdao, times(1))
                .deletemedItems(1);
    }

    @Test
    void testGetMedItemsById() {

        when(medItemsdao.getMedItemsbyid(1))
                .thenReturn(medItems);

        MedItems fetchedMedItems =
                medItemService.getmeditemsbyid(1);

        assertNotNull(fetchedMedItems);
        assertEquals(1, fetchedMedItems.getId());

        verify(medItemsdao, times(1))
                .getMedItemsbyid(1);
    }

    @Test
    void testGetMedItemsByIdReturnsNull() {

        when(medItemsdao.getMedItemsbyid(1))
                .thenReturn(null);

        MedItems fetchedMedItems =
                medItemService.getmeditemsbyid(1);

        assertNull(fetchedMedItems);

        verify(medItemsdao, times(1))
                .getMedItemsbyid(1);
    }
}