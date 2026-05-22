package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.Branch;
import com.ty.HospitalManagementSystem.Entity.Encounter;
import com.ty.HospitalManagementSystem.Entity.Person;
import com.ty.HospitalManagementSystem.dao.Branchdao;
import com.ty.HospitalManagementSystem.dao.Encounterdao;
import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EncounterServiceTest {

    @Mock
    private Encounterdao encounterdao;

    @Mock
    private Persondao persondao;

    @Mock
    private Branchdao branchdao;

    @InjectMocks
    private EncounterService encounterService;

    private Encounter encounter;
    private Person person;
    private Branch branch;

    @BeforeEach
    void setUp() {

        person = new Person();
        person.setId(1);

        branch = new Branch();
        branch.setId(1);
        branch.setName("Main Branch");

        encounter = new Encounter();
        encounter.setId(1);
    }

    @Test
    void testSaveEncounter() {

        when(persondao.getPersonById(1))
                .thenReturn(person);

        when(branchdao.getbranchbyid(1))
                .thenReturn(branch);

        when(encounterdao.saveEncounter(encounter))
                .thenReturn(encounter);

        Encounter savedEncounter =
                encounterService.saveEncounter(encounter, 1, 1);

        assertNotNull(savedEncounter);
        assertEquals(1, savedEncounter.getId());
        assertEquals(person, savedEncounter.getPerson());
        assertEquals(branch, savedEncounter.getBranch());

        verify(persondao, times(1))
                .getPersonById(1);

        verify(branchdao, times(1))
                .getbranchbyid(1);

        verify(encounterdao, times(1))
                .saveEncounter(encounter);
    }

    @Test
    void testSaveEncounterThrowsExceptionWhenPersonNotFound() {

        when(persondao.getPersonById(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    encounterService.saveEncounter(encounter, 1, 1);
                });

        assertEquals(
                "Person not found for id 1",
                exception.getMessage());

        verify(persondao, times(1))
                .getPersonById(1);

        verify(encounterdao, never())
                .saveEncounter(any());
    }

    @Test
    void testSaveEncounterThrowsExceptionWhenBranchNotFound() {

        when(persondao.getPersonById(1))
                .thenReturn(person);

        when(branchdao.getbranchbyid(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    encounterService.saveEncounter(encounter, 1, 1);
                });

        assertEquals(
                "Branch not found for id 1",
                exception.getMessage());

        verify(branchdao, times(1))
                .getbranchbyid(1);

        verify(encounterdao, never())
                .saveEncounter(any());
    }

    @Test
    void testUpdateEncounter() {

        Encounter dbEncounter = new Encounter();
        dbEncounter.setId(1);
        dbEncounter.setPerson(person);

        when(encounterdao.getEncounterById(1))
                .thenReturn(dbEncounter);

        when(branchdao.getbranchbyid(1))
                .thenReturn(branch);

        when(encounterdao.updateEncounter(1, encounter))
                .thenReturn(encounter);

        Encounter updatedEncounter =
                encounterService.updateEncounter(1, encounter, 1);

        assertNotNull(updatedEncounter);
        assertEquals(1, updatedEncounter.getId());
        assertEquals(person, updatedEncounter.getPerson());
        assertEquals(branch, updatedEncounter.getBranch());

        verify(encounterdao, times(1))
                .getEncounterById(1);

        verify(branchdao, times(1))
                .getbranchbyid(1);

        verify(encounterdao, times(1))
                .updateEncounter(1, encounter);
    }

    @Test
    void testUpdateEncounterThrowsExceptionWhenEncounterNotFound() {

        when(encounterdao.getEncounterById(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    encounterService.updateEncounter(1, encounter, 1);
                });

        assertEquals(
                "Encounter not found for id 1",
                exception.getMessage());

        verify(encounterdao, times(1))
                .getEncounterById(1);

        verify(encounterdao, never())
                .updateEncounter(anyInt(), any());
    }

    @Test
    void testUpdateEncounterThrowsExceptionWhenBranchNotFound() {

        Encounter dbEncounter = new Encounter();
        dbEncounter.setPerson(person);

        when(encounterdao.getEncounterById(1))
                .thenReturn(dbEncounter);

        when(branchdao.getbranchbyid(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    encounterService.updateEncounter(1, encounter, 1);
                });

        assertEquals(
                "Branch not found for id 1",
                exception.getMessage());

        verify(branchdao, times(1))
                .getbranchbyid(1);

        verify(encounterdao, never())
                .updateEncounter(anyInt(), any());
    }

    @Test
    void testDeleteEncounter() {

        when(encounterdao.deleteEncounter(1))
                .thenReturn(encounter);

        Encounter deletedEncounter =
                encounterService.deleteEncounter(1);

        assertNotNull(deletedEncounter);
        assertEquals(1, deletedEncounter.getId());

        verify(encounterdao, times(1))
                .deleteEncounter(1);
    }

    @Test
    void testDeleteEncounterThrowsException() {

        when(encounterdao.deleteEncounter(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    encounterService.deleteEncounter(1);
                });

        assertEquals(
                "Encounter not found for id 1",
                exception.getMessage());

        verify(encounterdao, times(1))
                .deleteEncounter(1);
    }

    @Test
    void testGetEncounterById() {

        when(encounterdao.getEncounterById(1))
                .thenReturn(encounter);

        Encounter fetchedEncounter =
                encounterService.getEncounterById(1);

        assertNotNull(fetchedEncounter);
        assertEquals(1, fetchedEncounter.getId());

        verify(encounterdao, times(1))
                .getEncounterById(1);
    }

    @Test
    void testGetEncounterByIdThrowsException() {

        when(encounterdao.getEncounterById(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    encounterService.getEncounterById(1);
                });

        assertEquals(
                "Encounter not found for id 1",
                exception.getMessage());

        verify(encounterdao, times(1))
                .getEncounterById(1);
    }
}