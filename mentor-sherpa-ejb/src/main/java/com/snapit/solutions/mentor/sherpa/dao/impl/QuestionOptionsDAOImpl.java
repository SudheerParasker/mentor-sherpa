/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snapit.solutions.mentor.sherpa.dao.impl;

import com.snapit.solutions.mentor.sherpa.dao.QuestionOptionsDAO;
import com.snapit.solutions.mentor.sherpa.dao.utils.DaoUtils;
import com.snapit.solutions.mentor.sherpa.entity.QuestionOptions;
import java.util.List;
import java.util.Set;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ram
 */
@Repository("questionOptionsDAO")
public class QuestionOptionsDAOImpl extends BasicDAO<QuestionOptions, ObjectId> implements QuestionOptionsDAO {
    
    @Autowired
    public QuestionOptionsDAOImpl(Datastore datastore) {
        super(datastore);
        datastore.ensureIndexes(); //creates all defined with @Indexed
        datastore.ensureCaps(); //creates all collections for @Entity(cap=@CappedAt(...))
    }

    @Override
    public List<QuestionOptions> retrievebyObjectIds(Set<String> questionIdList) {
        Query<QuestionOptions> query = getDatastore().createQuery(QuestionOptions.class);
        query.field("id").hasAnyOf(DaoUtils.createSetOfObjectIds(questionIdList));
        return query.asList();
    }
    
    @Override
    public List<QuestionOptions> retrievebyQuestionFor(Set<String> questionIdList, Set<String> questionFor) {        
        Query<QuestionOptions> query = getDatastore().createQuery(QuestionOptions.class);
        query.field("id").hasAnyOf(DaoUtils.createSetOfObjectIds(questionIdList)).
                and(query.criteria("questionFor").hasAnyOf(questionFor));
        return query.asList();
    }
    
   @Override
    public List<QuestionOptions> retrieveCommonQuestions() {        
        Query<QuestionOptions> query = getDatastore().createQuery(QuestionOptions.class);
        query.field("questionFor").equal("common");
        return query.asList();
    } 

    @Override
    public List<QuestionOptions> retrieveMentorQuestions(String id, String programName) {
 Query<QuestionOptions> query = getDatastore().createQuery(QuestionOptions.class);
        query.field("questionFor").equal("mentor").
                and(query.criteria("orgId").equal(new ObjectId(id))).
                and(query.criteria("programName").endsWithIgnoreCase(programName));
        return query.asList();
    }

    @Override
    public List<QuestionOptions> retrieveStudentQuestions(String id, String programName) {
        Query<QuestionOptions> query = getDatastore().createQuery(QuestionOptions.class);
        query.field("questionFor").equal("student").
                and(query.criteria("orgId").equal(new ObjectId(id))).
                and(query.criteria("programName").endsWithIgnoreCase(programName));
        return query.asList();
    }
    
    @Override
    public List<QuestionOptions> retrieveRequiredQuestions(String orgId, String programName,String questionFor,String questionCategory) {
        Query<QuestionOptions> query = getDatastore().createQuery(QuestionOptions.class);
        query.field("questionFor").equal("student").
                and(query.criteria("orgId").equal(new ObjectId(orgId)).
                and(query.criteria("programName").endsWithIgnoreCase(programName)).
                and(query.criteria("questionCategory").endsWithIgnoreCase(questionCategory)));
        return query.asList();
    }
    
}
