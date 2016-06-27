/*
 * Property of SnapIT Solutions.
 */
package com.snapit.solutions.securtiy.service;

import com.snapit.solutions.mentor.sherpa.service.OrganizationSessionBean;
import com.snapit.solutions.security.dao.UserDAO;
import com.snapit.solutions.securtiy.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sudheer.Parasker@SnapIT.Solutions
 */
//@Stateless(name="UserService")//, mappedName = "com/snapit/solutions/security/service/userservice/ejb/UserService")
//@Interceptors(SpringBeanAutowiringInterceptor.class)
//@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LogManager.getLogger(OrganizationSessionBean.class);
    @Autowired
    private UserDAO userDAO;

    @Override
    public User findByUserId(String userId) {
        return new User();
    }
}