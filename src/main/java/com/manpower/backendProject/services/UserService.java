package com.manpower.backendProject.services;

import com.manpower.backendProject.models.leave.*;
import com.manpower.backendProject.models.leave_availability.LeaveRequestAvailableDaysDao;
import com.manpower.backendProject.models.user.ResetPassword;
import com.manpower.backendProject.models.user.User;
import com.manpower.backendProject.models.user.UserNotFoundException;
import com.manpower.backendProject.repositories.LeaveRequestAvailableDaysRepository;
import com.manpower.backendProject.repositories.LeaveRequestRepository;
import com.manpower.backendProject.repositories.UserRepository;
import com.manpower.backendProject.util.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;
    private final LeaveRequestAvailableDaysRepository availableDaysRepository;
    private final PasswordEncoder encoder;

    /**
     * Get all users request
     *
     * @return List - LeaveRequestDao
     */
    public ResponseEntity<Object> getUserRequests(int pageNo, int pageSize, String sortBy) {
        if(pageNo < 0) pageNo = 0;
        if(pageSize < 1) pageSize = 10;

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<LeaveRequest> page = leaveRequestRepository.findByUser(findLoggedUser(), paging);

        Map<String, Object> response = new HashMap<>();
        response.put("content", page.getContent().stream().map(LeaveRequestDao::leaveRequestDaoConverter).toList());
        response.put("currentPage", page.getNumber() + 1);
        response.put("pages", page.getTotalPages());
        response.put("count", page.getTotalElements());
//        List<LeaveRequestDao> response = findLoggedUser()
//                .getRequests()
//                .stream()
//                .map(LeaveRequestDao::LeaveRequestDaoConverter)
//                .toList();
        return ResponseEntity.ok(response);
    }

    /**
     * Change previous password
     *
     * @param requestResetPassword old and new Password
     * @return if old password matches return HttpStatus 200
     */
    public ResponseEntity<String> changePassword(ResetPassword requestResetPassword) {
        String oldPassword = requestResetPassword.getOldPassword();
        String hashOldPassword = findLoggedUser().getPassword();
        if (encoder.matches(oldPassword, hashOldPassword)) {
            return resetPassword(findLoggedUser(), requestResetPassword.getNewPassword());
        }
        return ResponseEntity.badRequest().body("Not the same password!");
    }

    /**
     * Reset password with the new password
     *
     * @param user        User entity that you want to reset the password
     * @param newPassword String new Password
     * @return HttpStatus 200
     */
    public ResponseEntity<String> resetPassword(User user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok(String.format("%s's password was change!", user.getFirstname()));
    }

    public ResponseEntity<String> resetPassword(String newPassword) {
        return resetPassword(findLoggedUser(), newPassword);
    }

    /**
     * User creates leaveRequest,
     * leaveRequest gets created by leaveRequestDao
     *
     * @param request LeaveRequest Dao
     * @return Status 200 and String to confirm
     */
    public ResponseEntity<String> createRequest(LeaveRequestDao request) {
        LeaveRequest leaveRequest = LeaveRequest
                .builder()
                .user(findLoggedUser())
                .type(request.getType())
                .status(LeaveRequestSTATUS.PENDING)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
        leaveRequestRepository.save(leaveRequest);
        return ResponseEntity.ok("The request was created!");
    }

    /**
     * Update previous existing LeaveRequest
     *
     * @param leaveRequestId LeaveRequest's id to update
     * @param request        LeaveRequest Dao
     * @return String and HttpStatus 200
     */
    public ResponseEntity<String> updateRequest(long leaveRequestId, LeaveRequestDao request) {
        if (request.getStatus() != LeaveRequestSTATUS.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can only update pending requests.");
        }
        LeaveRequest existingLeaveRequest = findLeaveRequestById(leaveRequestId);
        //Maybe check if null? Need to be NonNull by React
        existingLeaveRequest.setStartDate(request.getStartDate());
        existingLeaveRequest.setEndDate(request.getEndDate());
        existingLeaveRequest.setType(request.getType());
        leaveRequestRepository.save(existingLeaveRequest);
        return ResponseEntity.ok("The request was updated successfully.");
    }

    public ResponseEntity<String> deleteRequest(long leaveRequestId) {
        LeaveRequest existingLeaveRequest = findLeaveRequestById(leaveRequestId);
        leaveRequestRepository.delete(existingLeaveRequest);
        return ResponseEntity.ok("Request deleted successfully.");

    }


    /**
     * @return A list of all the available leave request days
     */
    public ResponseEntity<List<LeaveRequestAvailableDaysDao>> getRemainingLeaveDays() {
        return ResponseEntity.ok(availableDaysRepository
                .findByUser(findLoggedUser())
                .stream()
                .map(LeaveRequestAvailableDaysDao::leaveRequestAvailableDaysDaoConverter)
                .toList());
    }

    /**
     * @param type LeaveRequestType Enum
     * @return A List of leaveRequest available days by type
     */
    public ResponseEntity<List<LeaveRequestAvailableDaysDao>> getRemainingLeaveDaysByType(LeaveRequestTYPE type) {
        return ResponseEntity.ok(availableDaysRepository
                .findByUserAndType(findLoggedUser(), type)
                .stream()
                .map(LeaveRequestAvailableDaysDao::leaveRequestAvailableDaysDaoConverter)
                .toList());
    }

    private User findLoggedUser() {
        return userRepository.findById(LoggedUser.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public LeaveRequest findLeaveRequestById(long leaveRequestId) {
        return leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new LeaveRequestNotFoundException("Request not found."));
    }
}
