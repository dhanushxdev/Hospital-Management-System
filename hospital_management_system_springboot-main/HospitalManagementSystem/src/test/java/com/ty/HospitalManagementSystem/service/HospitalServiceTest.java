package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.Hospital;
import com.ty.HospitalManagementSystem.dao.HospitalDao;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HospitalServiceTest {

    @Mock
    private HospitalDao dao;

    @InjectMocks
    private HospitalService hospitalService;

    private Hospital hospital;

    @BeforeEach
    void setUp() {

        hospital = new Hospital();

        hospital.setId(1);
        hospital.setName("Apollo Hospital");
        hospital.setEmail("apollo@gmail.com");
    }

    @Test
    void testSaveHospital() {

        when(dao.savehospital(hospital))
                .thenReturn(hospital);

        Hospital savedHospital =
                hospitalService.saveHospital(hospital);

        assertNotNull(savedHospital);
        assertEquals(1, savedHospital.getId());
        assertEquals("Apollo Hospital",
                savedHospital.getName());

        verify(dao, times(1))
                .savehospital(hospital);
    }

    @Test
    void testUpdateHospital() {

        when(dao.updatehospital(1, hospital))
                .thenReturn(hospital);

        Hospital updatedHospital =
                hospitalService.updateHospital(1, hospital);

        assertNotNull(updatedHospital);
        assertEquals("Apollo Hospital",
                updatedHospital.getName());

        verify(dao, times(1))
                .updatehospital(1, hospital);
    }

    @Test
    void testUpdateHospitalThrowsException() {

        when(dao.updatehospital(1, hospital))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    hospitalService.updateHospital(1, hospital);
                });

        assertEquals(
                "Hospital not found for id 1",
                exception.getMessage());

        verify(dao, times(1))
                .updatehospital(1, hospital);
    }

    @Test
    void testDeleteHospital() {

        when(dao.deletehospital(1))
                .thenReturn(hospital);

        Hospital deletedHospital =
                hospitalService.deleteHospital(1);

        assertNotNull(deletedHospital);
        assertEquals(1, deletedHospital.getId());

        verify(dao, times(1))
                .deletehospital(1);
    }

    @Test
    void testDeleteHospitalThrowsException() {

        when(dao.deletehospital(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    hospitalService.deleteHospital(1);
                });

        assertEquals(
                "Hospital not found for id 1",
                exception.getMessage());

        verify(dao, times(1))
                .deletehospital(1);
    }

    @Test
    void testGetHospitalById() {

        when(dao.gethospitalbyid(1))
                .thenReturn(hospital);

        Hospital fetchedHospital =
                hospitalService.getHospitalbyid(1);

        assertNotNull(fetchedHospital);
        assertEquals(1, fetchedHospital.getId());

        verify(dao, times(1))
                .gethospitalbyid(1);
    }

    @Test
    void testGetHospitalByIdThrowsException() {

        when(dao.gethospitalbyid(1))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    hospitalService.getHospitalbyid(1);
                });

        assertEquals(
                "Hospital not found for id 1",
                exception.getMessage());

        verify(dao, times(1))
                .gethospitalbyid(1);
    }

    @Test
    void testGetHospitalByEmail() {

        when(dao.gethospitalbyemail("apollo@gmail.com"))
                .thenReturn(hospital);

        Hospital fetchedHospital =
                hospitalService.gethospitalbyemail("apollo@gmail.com");

        assertNotNull(fetchedHospital);
        assertEquals("apollo@gmail.com",
                fetchedHospital.getEmail());

        verify(dao, times(1))
                .gethospitalbyemail("apollo@gmail.com");
    }

    @Test
    void testGetHospitalByEmailThrowsException() {

        when(dao.gethospitalbyemail("apollo@gmail.com"))
                .thenReturn(null);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    hospitalService
                            .gethospitalbyemail("apollo@gmail.com");
                });

        assertEquals(
                "Hospital not found for email apollo@gmail.com",
                exception.getMessage());

        verify(dao, times(1))
                .gethospitalbyemail("apollo@gmail.com");
    }

    @Test
    void testGetAllHospitalAscending() {

        List<Hospital> hospitalList = List.of(hospital);

        Page<Hospital> hospitalPage =
                new PageImpl<>(hospitalList);

        when(dao.getAllHospitals(any(Pageable.class)))
                .thenReturn(hospitalPage);

        List<Hospital> result =
                hospitalService.getAllHospital(0, 5, "asc");

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(dao, times(1))
                .getAllHospitals(any(Pageable.class));
    }

    @Test
    void testGetAllHospitalDescending() {

        List<Hospital> hospitalList = List.of(hospital);

        Page<Hospital> hospitalPage =
                new PageImpl<>(hospitalList);

        when(dao.getAllHospitals(any(Pageable.class)))
                .thenReturn(hospitalPage);

        List<Hospital> result =
                hospitalService.getAllHospital(0, 5, "desc");

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(dao, times(1))
                .getAllHospitals(any(Pageable.class));
    }

    @Test
    void testGetAllHospitalThrowsException() {

        Page<Hospital> emptyPage =
                new PageImpl<>(List.of());

        when(dao.getAllHospitals(any(Pageable.class)))
                .thenReturn(emptyPage);

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    hospitalService
                            .getAllHospital(0, 5, "asc");
                });

        assertEquals(
                "No hospitals found",
                exception.getMessage());

        verify(dao, times(1))
                .getAllHospitals(any(Pageable.class));
    }
}