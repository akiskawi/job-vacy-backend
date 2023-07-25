package com.manpower.backendProject.models.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateTeamDao {
    private long managerId;
    private List<Long> membersId;
}
