package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.Entity.Branch;
import com.ty.HospitalManagementSystem.dao.Branchdao;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;
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
class BranchServiceTest {

    @Mock
    private Branchdao branchdao;

    @InjectMocks
    private BranchService branchService;

    private Branch branch;

    @BeforeEach
    void setUp() {

        branch = new Branch();

        // Set values according to your Branch entity
        branch.setId(1);
        branch.setName("Main Branch");
        branch.setPhone(9876543210L);
    }

    @Test
    void testSaveBranch() {

        when(branchdao.saveBranch(1, 1, branch))
                .thenReturn(branch);

        Branch savedBranch =
                branchService.saveBranch(1, 1, branch);

        assertNotNull(savedBranch);
        assertEquals(1, savedBranch.getId());
        assertEquals("Main Branch",
                savedBranch.getName());

        verify(branchdao, times(1))
                .saveBranch(1, 1, branch);
    }

    @Test
    void testUpdateBranch() {

        when(branchdao.updateBranch(1, branch))
                .thenReturn(branch);

        Branch updatedBranch =
                branchService.updateBranch(1, branch);

        assertNotNull(updatedBranch);
        assertEquals("Main Branch",
                updatedBranch.getName());

        verify(branchdao, times(1))
                .updateBranch(1, branch);
    }

    @Test
    void testDeleteBranch() {

        when(branchdao.deleteBranch(1))
                .thenReturn(branch);

        Branch deletedBranch =
                branchService.deleteBranch(1);

        assertNotNull(deletedBranch);
        assertEquals(1, deletedBranch.getId());

        verify(branchdao, times(1))
                .deleteBranch(1);
    }

    @Test
    void testGetBranchById() {

        when(branchdao.getbranchbyid(1))
                .thenReturn(branch);

        Branch fetchedBranch =
                branchService.getbranchbyid(1);

        assertNotNull(fetchedBranch);
        assertEquals(1, fetchedBranch.getId());

        verify(branchdao, times(1))
                .getbranchbyid(1);
    }

    @Test
    void testGetBranchByHospitalId() {

        List<Branch> branchList = new ArrayList<>();
        branchList.add(branch);

        when(branchdao.getbranchbyhospitalid(1))
                .thenReturn(branchList);

        List<Branch> result =
                branchService.getbranchbyhospitalid(1);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(branchdao, times(1))
                .getbranchbyhospitalid(1);
    }

    @Test
    void testGetBranchByHospitalIdThrowsException() {

        when(branchdao.getbranchbyhospitalid(1))
                .thenReturn(new ArrayList<>());

        IdNotFoundException exception =
                assertThrows(IdNotFoundException.class, () -> {
                    branchService.getbranchbyhospitalid(1);
                });

        assertEquals(
                "No branches found for Hospital ID 1",
                exception.getMessage());

        verify(branchdao, times(1))
                .getbranchbyhospitalid(1);
    }
}