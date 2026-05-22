package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.Address;
import com.ty.HospitalManagementSystem.Entity.Person;
import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private Persondao persondao;

    @Mock
    private Addressdao addressdao;

    @InjectMocks
    private PersonService personService;

    private Person person;
    private Address address;

    @BeforeEach
    void setUp() {

        address = new Address();
        address.setId(1);

        person = new Person();

        person.setId(1);
        person.setName("John");
        person.setEmail("john@gmail.com");
        person.setPhone(9876543210L);
        person.setAddress(address);
    }

    @Test
    void testSavePerson() {

        when(addressdao.getaddressbyid(1))
                .thenReturn(address);

        when(persondao.savePerson(person))
                .thenReturn(person);

        Person savedPerson =
                personService.savePerson(person);

        assertNotNull(savedPerson);
        assertEquals(1, savedPerson.getId());
        assertEquals("John", savedPerson.getName());

        verify(addressdao, times(1))
                .getaddressbyid(1);

        verify(persondao, times(1))
                .savePerson(person);
    }

    @Test
    void testSavePersonThrowsExceptionWhenAddressNotFound() {

        when(addressdao.getaddressbyid(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    personService.savePerson(person);
                });

        assertEquals(
                "Address Not found 1",
                exception.getMessage());

        verify(addressdao, times(1))
                .getaddressbyid(1);

        verify(persondao, never())
                .savePerson(any());
    }

    @Test
    void testUpdatePerson() {

        Person dbPerson = new Person();

        dbPerson.setId(1);
        dbPerson.setName("Old Name");
        dbPerson.setEmail("old@gmail.com");
        dbPerson.setPhone(9999999999L);

        when(persondao.getPersonById(1))
                .thenReturn(dbPerson);

        when(addressdao.getaddressbyid(1))
                .thenReturn(address);

        when(persondao.updatePerson(any(Person.class)))
                .thenReturn(person);

        Person updatedPerson =
                personService.updatePerson(1, person);

        assertNotNull(updatedPerson);
        assertEquals("John", updatedPerson.getName());

        verify(persondao, times(1))
                .getPersonById(1);

        verify(addressdao, times(1))
                .getaddressbyid(1);

        verify(persondao, times(1))
                .updatePerson(any(Person.class));
    }

    @Test
    void testUpdatePersonThrowsExceptionWhenPersonNotFound() {

        when(persondao.getPersonById(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    personService.updatePerson(1, person);
                });

        assertEquals(
                "Person not found with id 1",
                exception.getMessage());

        verify(persondao, times(1))
                .getPersonById(1);

        verify(persondao, never())
                .updatePerson(any());
    }

    @Test
    void testUpdatePersonThrowsExceptionWhenAddressNotFound() {

        Person dbPerson = new Person();
        dbPerson.setId(1);

        when(persondao.getPersonById(1))
                .thenReturn(dbPerson);

        when(addressdao.getaddressbyid(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    personService.updatePerson(1, person);
                });

        assertEquals(
                "Address not found with id 1",
                exception.getMessage());

        verify(addressdao, times(1))
                .getaddressbyid(1);

        verify(persondao, never())
                .updatePerson(any());
    }

    @Test
    void testDeletePerson() {

        when(persondao.getPersonById(1))
                .thenReturn(person);

        Person deletedPerson =
                personService.deletePerson(1);

        assertNotNull(deletedPerson);
        assertEquals(1, deletedPerson.getId());

        verify(persondao, times(1))
                .getPersonById(1);

        verify(persondao, times(1))
                .deletePerson(person);
    }

    @Test
    void testDeletePersonThrowsException() {

        when(persondao.getPersonById(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    personService.deletePerson(1);
                });

        assertEquals(
                "Person not found with id 1",
                exception.getMessage());

        verify(persondao, times(1))
                .getPersonById(1);

        verify(persondao, never())
                .deletePerson(any());
    }

    @Test
    void testGetPersonById() {

        when(persondao.getPersonById(1))
                .thenReturn(person);

        Person fetchedPerson =
                personService.getPersonById(1);

        assertNotNull(fetchedPerson);
        assertEquals(1, fetchedPerson.getId());

        verify(persondao, times(1))
                .getPersonById(1);
    }

    @Test
    void testGetPersonByIdThrowsException() {

        when(persondao.getPersonById(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    personService.getPersonById(1);
                });

        assertEquals(
                "Person not found with id 1",
                exception.getMessage());

        verify(persondao, times(1))
                .getPersonById(1);
    }

    @Test
    void testGetAllPersonsAscending() {

        List<Person> personList = List.of(person);

        Page<Person> personPage =
                new PageImpl<>(personList);

        when(persondao.getAllPerson(any(Pageable.class)))
                .thenReturn(personPage);

        List<Person> result =
                personService.getAllPersons(0, 5, "asc");

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(persondao, times(1))
                .getAllPerson(any(Pageable.class));
    }

    @Test
    void testGetAllPersonsDescending() {

        List<Person> personList = List.of(person);

        Page<Person> personPage =
                new PageImpl<>(personList);

        when(persondao.getAllPerson(any(Pageable.class)))
                .thenReturn(personPage);

        List<Person> result =
                personService.getAllPersons(0, 5, "desc");

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(persondao, times(1))
                .getAllPerson(any(Pageable.class));
    }

    @Test
    void testGetAllPersonsThrowsException() {

        Page<Person> emptyPage =
                new PageImpl<>(List.of());

        when(persondao.getAllPerson(any(Pageable.class)))
                .thenReturn(emptyPage);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    personService.getAllPersons(0, 5, "asc");
                });

        assertEquals(
                "No persons found",
                exception.getMessage());

        verify(persondao, times(1))
                .getAllPerson(any(Pageable.class));
    }
}