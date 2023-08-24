package com.manpower.backendProject.services;

import com.manpower.backendProject.models.leave.LeaveRequestDao;
import com.manpower.backendProject.models.leave.LeaveRequestNotFoundException;
import com.manpower.backendProject.models.leave.LeaveRequestSTATUS;
import com.manpower.backendProject.models.team.TeamDao;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.models.user.UserDao;
import com.manpower.backendProject.models.user.UserNotFoundException;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.TeamRepository;
import com.manpower.backendProject.util.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final TeamRepository teamRepository;
    private final LeaveRequestRepository requestRepository;

//    public ResponseEntity<Object> getTeamMembers(Long team_id) {
//        var manager = repository.findByTeamManagerId(team_id);
//        var users = repository.findUsersByTeam_Id(team_id);
//        UserDao managerDao = null;
//        if (manager.isPresent()) {
//            managerDao = EntityToDaoHelper.userToUserDao(manager.get());
//        }
//        List<UserDao> usersDao = users.stream().map(EntityToDaoHelper::userToUserDao).toList();
//        TeamDao teamDao = TeamDao.builder()
//                .id(team_id)
//                .manager(managerDao)
//                .members(usersDao)
//                .build();
//        return ResponseEntity.ok(teamDao);
//    }


    //Dont need it?
    public ResponseEntity<TeamDao> getManagerTeam() {
        User manager = LoggedUser.get();
        var team = teamRepository.findByManager(manager);
        return ResponseEntity.ok(TeamDao.teamDaoConverter(team));
    }

    public ResponseEntity<List<UserDao>> getManagerTeamMembers() {
        User manager = LoggedUser.get();
        var team = teamRepository.findByManager(manager);
        return ResponseEntity.ok(team.getMembers().stream().map(UserDao::userDaoConverter).toList());
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

    public ResponseEntity<String> evaluateRequest(long userId, long requestId, boolean flag) {
        List<LeaveRequestDao> requests = getMemberRequests(userId).getBody();
        assert requests != null;
        var request = requests.stream().filter(req -> req.getId() == requestId).findFirst();
        if (request.isPresent()) {
            var leaveRequest = requestRepository.findById(requestId).get();
            if (flag) {
                leaveRequest.setStatus(LeaveRequestSTATUS.APPROVED);
            } else {
                leaveRequest.setStatus(LeaveRequestSTATUS.DENIED);
            }
            requestRepository.save(leaveRequest);
            return ResponseEntity.ok("Request has been " + (flag ? "Approved" : "Denied"));
        }
        throw new LeaveRequestNotFoundException(String.format("Request with id=%s was not found from this member",requestId));
    }
}
