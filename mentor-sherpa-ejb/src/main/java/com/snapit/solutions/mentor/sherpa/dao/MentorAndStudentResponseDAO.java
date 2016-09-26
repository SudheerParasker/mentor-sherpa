/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snapit.solutions.mentor.sherpa.dao;

import com.snapit.solutions.mentor.sherpa.entity.MentorAndStudentResponse;
import java.util.List;
import java.util.Set;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

/**
 *
 * @author Ram
 */
public interface MentorAndStudentResponseDAO extends DAO<MentorAndStudentResponse, ObjectId> {

    public MentorAndStudentResponse retrieveByMentorStudentId(String mentorOrStudentId);

    public List<MentorAndStudentResponse> retrieveMentorsResponsebyOrgAndProgram(
                                                                String orgId,
                                                                String programName,
                                                                String childId);
    public List<MentorAndStudentResponse> findAll();
    
    public void saveMentor(MentorAndStudentResponse mentorAndStudentResponse);
    
    //TODO remove will always get one response per user for org and prog name
    public List<MentorAndStudentResponse> retrieveResponse(String orgId, String resourceId, String programName);
    
    public List<MentorAndStudentResponse> retrieveByProgram(String orgId, String programName);
    
    public List<MentorAndStudentResponse> retrieveByOrg(String orgId);
    
    public List<MentorAndStudentResponse> retrieveByMentorStudentIds(Set<String> mentorOrStudentIds);
    
    public MentorAndStudentResponse retrieveUserResponse(String orgId, String mentorOrStudentId, String programName);

}
