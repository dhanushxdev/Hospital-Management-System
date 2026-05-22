package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.Address;
import com.ty.HospitalManagementSystem.dao.Addressdao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private Addressdao addressdao;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();

        // Set values according to your Address entity fields
        address.setId(1);
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setPincode(560001);
    }

    @Test
    void testSaveAddress() {

        when(addressdao.saveAddress(address)).thenReturn(address);

        Address savedAddress = addressService.saveAddress(address);

        assertNotNull(savedAddress);
        assertEquals(address.getId(), savedAddress.getId());
        assertEquals(address.getCity(), savedAddress.getCity());

        verify(addressdao, times(1)).saveAddress(address);
    }

    @Test
    void testUpdateAddress() {

        when(addressdao.updateAddress(1, address)).thenReturn(address);

        Address updatedAddress = addressService.updateAddress(1, address);

        assertNotNull(updatedAddress);
        assertEquals("Bangalore", updatedAddress.getCity());

        verify(addressdao, times(1)).updateAddress(1, address);
    }

    @Test
    void testDeleteAddress() {

        when(addressdao.deleteAddress(1)).thenReturn(address);

        Address deletedAddress = addressService.deleteAddress(1);

        assertNotNull(deletedAddress);
        assertEquals(1, deletedAddress.getId());

        verify(addressdao, times(1)).deleteAddress(1);
    }

    @Test
    void testGetAddressById() {

        when(addressdao.getaddressbyid(1)).thenReturn(address);

        Address fetchedAddress = addressService.getaddressbyid(1);

        assertNotNull(fetchedAddress);
        assertEquals(1, fetchedAddress.getId());

        verify(addressdao, times(1)).getaddressbyid(1);
    }

    @Test
    void testGetAllAddress() {

        List<Address> addressList = new ArrayList<>();
        addressList.add(address);

        when(addressdao.getAllAddress()).thenReturn(addressList);

        List<Address> result = addressService.getAllAddress();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(addressdao, times(1)).getAllAddress();
    }
}