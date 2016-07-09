/*
 * Property of SnapIT Solutions.
 */
package com.snapit.solutions.mentor.sherpa.facade;

import com.snapit.solutions.mentor.sherpa.entity.Mentor;
import com.snapit.solutions.mentor.sherpa.entity.Student;
import com.snapit.solutions.mentor.sherpa.model.RegisterForm;
import com.snapit.solutions.mentor.sherpa.service.MentorService;
import com.snapit.solutions.mentor.sherpa.service.StudentService;
import com.snapit.solutions.securtiy.entity.User;
import com.snapit.solutions.securtiy.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sudheer.Parasker@SnapIT.Solutions
 */
@Service
public class MentorSherpaUserServiceFacade implements MentorSherpaUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private MentorService mentorService;
    @Autowired
    private StudentService studentService;
    static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder(11);

    @Override
    public void registerUser(RegisterForm registerForm, String role) {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        User user = new User();
        user.setEmail(registerForm.getEmailId());
        user.setPassword(PASSWORD_ENCODER.encode(registerForm.getPassword()));
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setUserRole(roles);
        user.setDateOfBirth(registerForm.getDateOfBirth());
        user.setPhoneNumber(registerForm.getPhoneNumber());
        userService.registerUser(user);
        createUserProfile(registerForm.getEmailId(), role);
    }

    @Override
    public User findUser(String emailId) {
        return userService.findByUserId(emailId);
    }

    private void createUserProfile(String emailId, String role) {
        User user = findUser(emailId);

        if (role.equals("MENTOR")) {
            Mentor mentor = new Mentor();
            mentor.setUserObjectId(user.getId());
            mentorService.createMentor(mentor);
        } else if (role.equals("STUDENT")) {
            Student student = new Student();
            student.setUserObjectId(user.getId());
            studentService.createMentor(student);
        }
    }

}