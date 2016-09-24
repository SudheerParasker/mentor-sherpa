/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snapit.solutions.mentor.sherpa.dao.impl;

import com.snapit.solutions.mentor.sherpa.entity.Student;
import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.snapit.solutions.mentor.sherpa.dao.StudentDAO;
import com.snapit.solutions.mentor.sherpa.dao.utils.DaoUtils;
import com.snapit.solutions.mentor.sherpa.entity.AssignedMentor;
import java.util.ArrayList;
import java.util.Set;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 *
 * @author Ram
 */
@Repository("studentDAO")
public class StudentDAOImpl extends BasicDAO<Student, ObjectId> implements StudentDAO {

    @Autowired
    public StudentDAOImpl(Datastore ds) {
        super(ds);
        ds.ensureIndexes(); //creates all defined with @Indexed
        ds.ensureCaps(); //creates all collections for @Entity(cap=@CappedAt(...))

    }

    @Override
    public Student findById(String studentId) {
        return getDatastore().find(Student.class).field("_id").equal(DaoUtils.createObjectId(studentId)).get();
    }

    @Override
    public List<Student> findAll() {
        return getDatastore().find(Student.class).asList();
    }

    @Override
    public void assignNewMentorToStudent(String studentUserObjectId, String orgId, String mentorUserObjectId, String programName) {

        List<AssignedMentor> assignedMentors = new ArrayList();
        AssignedMentor assignedMentor = new AssignedMentor();
        assignedMentor.setProgramName(programName);
        assignedMentor.setOrgId(DaoUtils.createObjectId(orgId));
        assignedMentor.setMentorUserObjectId(DaoUtils.createObjectId(mentorUserObjectId));
        assignedMentors.add(assignedMentor);

        UpdateOperations<Student> ops
                = getDatastore().createUpdateOperations(Student.class).addAll("assignedMentors", assignedMentors, false);
        getDatastore().update(getDatastore().createQuery(Student.class).field("userObjectId").
                equal(DaoUtils.createObjectId(studentUserObjectId)), ops);

    }

    @Override
    public List<Student> findStudentByUserObjectIds(Set<String> studentUserObjectIds) {
        Query<Student> query = getDatastore().createQuery(Student.class);
        query.field("userObjectId").hasAnyOf(DaoUtils.createSetOfObjectIds(studentUserObjectIds));
        return query.asList();
    }

    @Override
    public Student findStudentByUserObjectId(String studentUserObjectId) {
        Query<Student> query = getDatastore().createQuery(Student.class);
        query.field("userObjectId").equal(DaoUtils.createObjectId(studentUserObjectId));
        return query.get();
    }

    @Override
    public void removeAssignedMentor(AssignedMentor assignedMentor,String studentUserObjectId) {    
        UpdateOperations<Student> ops = 
                getDatastore().createUpdateOperations(Student.class).removeAll("assignedMentors", assignedMentor);
	getDatastore().update(getDatastore().createQuery(Student.class).field("userObjectId").
                equal(DaoUtils.createObjectId(studentUserObjectId)), ops);
    }
    
    @Override
    public Student retrieveStudentsWithAssignedMentor(String mentorObjectIdSet)
    {   
       Query<Student> query = getDatastore().createQuery(Student.class);
       query.field("assignedMentors.mentorUserObjectId").equal(DaoUtils.createObjectId(mentorObjectIdSet));
       return query.get();
    }
}
