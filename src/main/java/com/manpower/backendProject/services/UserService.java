package com.manpower.backendProject.services;

import com.manpower.backendProject.exception.UserNotFoundException;
import com.manpower.backendProject.models.dao.LeaveRequestDao;
import com.manpower.backendProject.models.leave.LeaveRequest;
import com.manpower.backendProject.models.leave.LeaveRequestSTATUS;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;

    public ResponseEntity<Object> getUsersLeaveRequests(int id) {
        findUserByIdOrElseThrow(id);
        var leaveRequests = leaveRequestRepository.findAllByRequestsUserId(id);
        return ResponseEntity.ok(leaveRequests);
    }

    public ResponseEntity<String> createLeaveRequest(int id, LeaveRequestDao request) {
        var user = findUserByIdOrElseThrow(id);
        var leaveRequest = LeaveRequest.builder()
                .type(request.getType())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(LeaveRequestSTATUS.PENDING)
                .requestsUser(user)
                .build();
        leaveRequestRepository.save(leaveRequest);
        return ResponseEntity.ok("Leave Request was submitted.");
    }

    public ResponseEntity<LeaveRequest> getLeaveRequestById(int user_id, int requestId) {
        var user = findUserByIdOrElseThrow(user_id);
        var leaveRequest = leaveRequestRepository.findOneByIdAndRequestsUser_Id(requestId, user_id);
        if (leaveRequest == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(leaveRequest);
    }

    private User findUserByIdOrElseThrow(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
