package com.manpower.backendProject.models.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateTeamDao {
    private int managerId;
    private List<Integer> membersId;
}
