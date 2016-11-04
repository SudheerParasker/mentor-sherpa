/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snapit.solutions.mentor.sherpa.dao.impl;

import com.snapit.solutions.mentor.sherpa.dao.MentorDAO;
import com.snapit.solutions.mentor.sherpa.dao.utils.DaoUtils;
import com.snapit.solutions.mentor.sherpa.entity.AssignedUserInfo;
import com.snapit.solutions.mentor.sherpa.entity.Mentor;
import com.snapit.solutions.mentor.sherpa.entity.Student;
import com.snapit.solutions.mentor.sherpa.service.utils.CommonServiceUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ram
 */
@Repository("mentorDAO")
public class MentorDAOImpl extends BasicDAO<Mentor, ObjectId> implements MentorDAO {

    @Autowired
    public MentorDAOImpl(Datastore ds) {
        super(ds);
        ds.ensureIndexes(); //creates all defined with @Indexed
        ds.ensureCaps(); //creates all collections for @Entity(cap=@CappedAt(...))

    }

    @Override
    public List<Mentor> findAll() {
        return getDatastore().find(Mentor.class).asList();
    }

    @Override
    public Mentor findById(String mentorId) {
        return getDatastore().find(Mentor.class).field("_id").
                equal(DaoUtils.createObjectId(mentorId)).get();
    }

    @Override
    public Mentor findByMentorName(String mentorName) {
        return getDatastore().find(Mentor.class).field("name").equal(mentorName).get();
    }

    @Override
    public Mentor findMentorByUserName(String userObjectId) {
        return getDatastore().find(Mentor.class).field("user_id").
                equal(DaoUtils.createObjectId(userObjectId)).get();
    }

    @Override
    public void updateMentorById(Mentor mentor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveMentor(Mentor mentor) {
        getDatastore().save(mentor);
    }

    @Override
    public void deleteMentorById(Mentor mentor) {
        Datastore dataStore = getDatastore();
        Query<Mentor> deleteQuery
                = dataStore.createQuery(Mentor.class).field("_id").equal(mentor.getId());
        dataStore.delete(deleteQuery);
    }

    @Override
    public List<Mentor> findMentorsByIds(Set<String> mentorIds) {
        Query<Mentor> query = getDatastore().createQuery(Mentor.class);
        query.field("id").hasAnyOf(DaoUtils.createSetOfObjectIds(mentorIds));
        return query.asList();
    }

    @Override
    public List<Mentor> findMentorsByUserObjectIds(Set<String> mentorUserObjectIds) {
        Query<Mentor> query = getDatastore().createQuery(Mentor.class);
        query.field("userObjectId").hasAnyOf(DaoUtils.createSetOfObjectIds(mentorUserObjectIds));
        return query.asList();
    }
    
    @Override
    public Mentor findMentorByUserObjectId(String mentorUserObjectId) {
        List<Mentor> mentors = findMentorsByUserObjectIds(CommonServiceUtils.createHashSet(mentorUserObjectId));
        if(!mentors.isEmpty()){
        return mentors.get(0);
        }
       else
       return new Mentor();
    }
    
    @Override
    public void assignStudentToMentor(String mentorUserObjectId, String orgId, String studentUserObjectId, String programName) {

        List<AssignedUserInfo> assignedUsersInfo = new ArrayList();
        AssignedUserInfo assignedUserInfo = new AssignedUserInfo();
        assignedUserInfo.setProgramName(programName);
        assignedUserInfo.setOrgId(DaoUtils.createObjectId(orgId));
        assignedUserInfo.setUserObjectId(DaoUtils.createObjectId(studentUserObjectId));
        assignedUsersInfo.add(assignedUserInfo);

        UpdateOperations<Mentor> ops
                = getDatastore().createUpdateOperations(Mentor.class).addAll("assignedUsersInfo", assignedUsersInfo, false);
        getDatastore().update(getDatastore().createQuery(Mentor.class).field("userObjectId").
                equal(DaoUtils.createObjectId(mentorUserObjectId)), ops);

    }
    
    @Override
    public void removeAssignedStudent(AssignedUserInfo assignedUserInfo,String mentorUserObjectId) {    
        UpdateOperations<Mentor> ops = 
                getDatastore().createUpdateOperations(Mentor.class).removeAll("assignedUsersInfo", assignedUserInfo);
	getDatastore().update(getDatastore().createQuery(Mentor.class).field("userObjectId").
                equal(DaoUtils.createObjectId(mentorUserObjectId)), ops);
    }
    
      
}
