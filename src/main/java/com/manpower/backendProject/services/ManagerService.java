package com.manpower.backendProject.services;

import com.manpower.backendProject.models.leave.*;
import com.manpower.backendProject.models.team.TeamDao;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.models.user.UserDao;
import com.manpower.backendProject.models.user.UserNotFoundException;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.repositories.UserRepository;
import com.manpower.backendProject.util.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final TeamRepository teamRepository;
    private final LeaveRequestRepository requestRepository;
    private final UserRepository userRepository;

    //Don't need it?
    public ResponseEntity<TeamDao> getManagerTeam() {
        User manager = LoggedUser.get();
        var team = teamRepository.findByManager(manager);
        return ResponseEntity.ok(TeamDao.teamDaoConverter(team));
    }

    public ResponseEntity<Object> getManagerTeamMembers(int pageNo, int pageSize, String sortBy) {
        if(pageNo < 0) pageNo = 0;
        if(pageSize < 1) pageSize = 10;

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        User manager = LoggedUser.get();
        Page<User> page = userRepository.findUsersByTeam(manager.getTeamManager(), paging);

        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(UserDao::userDaoConverter).toList());
        response.put("currentPage", page.getNumber() + 1);
        response.put("pages", page.getTotalPages());
        response.put("count", page.getTotalElements());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<LeaveRequestDao>> getMemberRequests(long userId) {
        User manager = LoggedUser.get();
        var team = teamRepository.findByManager(manager);
        var member = team.getMembers().stream().filter(user -> user.getId() == userId).findFirst();
        if (member.isPresent()) {
            return ResponseEntity.ok(member.get().getRequests().stream().map(LeaveRequestDao::leaveRequestDaoConverter).toList());
        }
        throw new UserNotFoundException(String.format("User with id=%s is not member of your team", userId));
    }

    public ResponseEntity<String> evaluateRequest(long userId, long requestId, LeaveRequestSTATUS status) {
        List<LeaveRequestDao> requests = getMemberRequests(userId).getBody();
        assert requests != null;
        var request = requests.stream().filter(req -> req.getId() == requestId).findFirst();
        if (request.isPresent()) {
            var leaveRequest = requestRepository.findById(requestId).get();
            leaveRequest.setStatus(status);
            requestRepository.save(leaveRequest);
            return ResponseEntity.ok("Request has been " + status.name());
        }
        throw new LeaveRequestNotFoundException(String.format("Request with id=%s was not found from this member",requestId));
    }

    public ResponseEntity<Object> getAllTeamMembersRequests(int pageNo, int pageSize, String sortBy) {
        if(pageNo < 0) pageNo = 0;
        if(pageSize < 1) pageSize = 10;

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        User manager = LoggedUser.get();
        Page<LeaveRequest> page = requestRepository.findByTeam(manager.getTeamManager(), paging);

        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(LeaveRequestWithUserDao::leaveRequestDaoConverter).toList());
        response.put("currentPage", page.getNumber() + 1);
        response.put("pages", page.getTotalPages());
        response.put("count", page.getTotalElements());

        return ResponseEntity.ok(response);
    }
}
