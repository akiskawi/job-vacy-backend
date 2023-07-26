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
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseEntity<List<LeaveRequestDao>> getUserRequests() {
        List<LeaveRequestDao> requests = findLoggedUserById()
                .getRequests()
                .stream()
                .map(LeaveRequestDao::LeaveRequestDaoConverter)
                .toList();
        return ResponseEntity.ok(requests);
    }

    /**
     * Change previous password
     *
     * @param requestResetPassword old and new Password
     * @return if old password matches return HttpStatus 200
     */
    public ResponseEntity<String> changePassword(ResetPassword requestResetPassword) {
        String oldPassword = requestResetPassword.getOldPassword();
        String hashOldPassword = findLoggedUserById().getPassword();
        if (encoder.matches(oldPassword, hashOldPassword)) {
            return resetPassword(findLoggedUserById(), requestResetPassword.getNewPassword());
        }
        return ResponseEntity.badRequest().body("Not the same password!");
    }

    /**
     * Reset password wHttpStatusith the new password
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
        return resetPassword(findLoggedUserById(), newPassword);
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
                .requestsUser(findLoggedUserById())
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
                .findByUsersLeaveRequestsRemainingDays(findLoggedUserById())
                .stream()
                .map(LeaveRequestAvailableDaysDao::leaveRequestAvailableDaysConverter)
                .toList());
    }

    /**
     * @param type LeaveRequestType Enum
     * @return A List of leaveRequest available days by type
     */
    public ResponseEntity<List<LeaveRequestAvailableDaysDao>> getRemainingLeaveDaysByType(LeaveRequestTYPE type) {
        return ResponseEntity.ok(availableDaysRepository
                .findByUsersLeaveRequestsRemainingDaysAndType(findLoggedUserById(), type)
                .stream()
                .map(LeaveRequestAvailableDaysDao::leaveRequestAvailableDaysConverter)
                .toList());
    }

    private User findLoggedUserById() {
        return userRepository.findById(LoggedUser.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public LeaveRequest findLeaveRequestById(long leaveRequestId) {
        return leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new LeaveRequestNotFoundException("Request not found."));
    }
}
