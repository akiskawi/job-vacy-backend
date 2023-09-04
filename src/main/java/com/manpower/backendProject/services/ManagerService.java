package com.manpower.backendProject.services;

import com.manpower.backendProject.models.leave.*;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.models.user.UserDao;
import com.manpower.backendProject.models.user.UserNotFoundException;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.repositories.UserRepository;
import com.manpower.backendProject.util.LoggedUser;
import com.manpower.backendProject.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final TeamRepository teamRepository;
    private final LeaveRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final PaginationUtil<User, UserDao> userPaginationUtil;
    private final PaginationUtil<LeaveRequest, LeaveRequestWithUserDao> leaveRequestPaginationUtil;
//    private final LoggedUser loggedUser;

//    public ResponseEntity<TeamDao> getTeam() {
//        User manager = LoggedUser.get();
//        var team = teamRepository.findByManager(manager);
//        return ResponseEntity.ok(TeamDao.teamDaoConverter(team));
//    }

    public ResponseEntity<Object> getTeamMembers(int pageNo, int pageSize, String sortBy) {
        Pageable paging = userPaginationUtil.getPageable(pageNo, pageSize, sortBy);
        User manager = LoggedUser.get();
        Page<User> page = userRepository.findUsersByTeam(manager.getManagedTeam(), paging);
        return userPaginationUtil.getPaginatedResponse(UserDao::userDaoConverter, page);
//
//
//        Page<User> page = userRepository.findUsersByTeam(manager.getTeamManager(), paging);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("content", page.getContent().stream().map(UserDao::userDaoConverter).toList());
//        response.put("currentPage", page.getNumber() + 1);
//        response.put("pages", page.getTotalPages());
//        response.put("count", page.getTotalElements());
//
//        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<LeaveRequestDao>> getMemberRequests(long userId) {
        User manager = LoggedUser.get(); //TODO: an xrhsimopoihthei na ginei paginated
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
        Pageable paging = leaveRequestPaginationUtil.getPageable(pageNo, pageSize, sortBy);
        Long teamId = LoggedUser.get().getManagedTeam().getId();
        Page<LeaveRequest> page = requestRepository.findAllByUser_Team_Id(teamId, paging);
        return leaveRequestPaginationUtil.getPaginatedResponse(LeaveRequestWithUserDao::leaveRequestDaoConverter, page);
    }
}
