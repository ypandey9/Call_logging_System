package com.ypandey.calllogsystem.service;

import com.ypandey.calllogsystem.dto.CallLogRequest;
import com.ypandey.calllogsystem.model.CallLog;
import com.ypandey.calllogsystem.model.User;
import com.ypandey.calllogsystem.repository.CallLogRepository;
import org.springframework.stereotype.Service;
import com.ypandey.calllogsystem.repository.UserRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

@Service
public class CallLogService {

    private final CallLogRepository repository;
    private final UserRepository userRepository;

    public CallLogService(CallLogRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // public List<CallLog> getAll() {
    //     return repository.findAll();
    // }

    public List<CallLog> getCallsForUser(Authentication auth) {
    
        String username=auth.getName();
        boolean isAdmin=auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin){
            return repository.findAll();
        }else{
            return repository.findByUserUsername(username); 
    }

    }


    public CallLog create(CallLogRequest request, Authentication auth) {
        String username=auth.getName();        
        User user = userRepository.findByUsername(auth.getName());
        CallLog callLog = new CallLog();
        callLog.setCallerName(request.getCallerName());
        callLog.setPhoneNumber(request.getPhoneNumber());
        callLog.setDirection(request.getDirection());
        callLog.setDurationSeconds(request.getDurationSeconds());
        callLog.setStatus(request.getStatus());
        callLog.setNotes(request.getNotes());
        callLog.setCallTime(request.getCallTime());
        callLog.setUser(user);
        return repository.save(callLog);
    }

    // public void delete(Long id) {
    //     repository.deleteById(id);
    // }

    public boolean delete(Long id, Authentication auth) {

    CallLog call = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Call not found"));

    boolean isAdmin = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

    if (!isAdmin &&
            !call.getUser().getUsername().equals(auth.getName())) {

        throw new RuntimeException("Unauthorized");
    }

    repository.delete(call);

    return true;
}

}
