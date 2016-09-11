/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snapit.solutions.mentor.sherpa.service.utils;

import com.snapit.solutions.mentor.sherpa.entity.SignedupOrganization;
import com.snapit.solutions.mentor.sherpa.entity.Organization;
import com.snapit.solutions.mentor.sherpa.entity.Program;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.bson.types.ObjectId;

/**
 *
 * @author Ram
 */
public class CommonServiceUtils {
    
    public static Set<String> findrequiredQuestionIdList(Organization organization,SignedupOrganization interestedOrganizations){
        Set<ObjectId> questionIdList = new HashSet();
        for (Program program : organization.getPrograms()) 
        {
            for (String interestedProgramName : interestedOrganizations.getPrograms()) 
            {
                if (interestedProgramName.equals(program.getProgramName())) 
                {
                    questionIdList.addAll(program.getQuestionsIdList());
                }
            }
        }
        return createSetOfStringIds(questionIdList);
    }
    
    public static Set<String> createSetOfStringIds(Collection<ObjectId> objectIds) {
        Set<String> objectIdSet = new HashSet();
        if (!objectIds.isEmpty()) {
            for (ObjectId objectId : objectIds) {
                objectIdSet.add(createStringId(objectId));
            }

        }
        return objectIdSet;
    }

    public static String createStringId(Object objectId) {
        if (objectId == null) {
            return null;
        }
        return objectId.toString();
    }
    
    public static int calculateAge(String dateOfBirth){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate doB = LocalDate.parse(dateOfBirth, dateTimeFormatter);
        LocalDate today = LocalDate.now();
        Period period = Period.between(doB, today);
        return period.getYears();
    }
    
    public static <T> Set<T> createHashSet(T t){   
       Set<T> hashSet = new HashSet();
       hashSet.add(t);
       return hashSet;   
    }
}
