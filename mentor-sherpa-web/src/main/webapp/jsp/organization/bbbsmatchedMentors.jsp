<%-- 
    Document   : studentMentorAssoc
    Created on : Jun 28, 2016, 7:06:47 PM
    Author     : Sudheer.Parasker@SnapIT.Solutions
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authentication var="user" property="principal" />
<sec:authorize access="hasRole('MENTOR')">
<c:url var="mentorStudent" value="mentor"></c:url>    
</sec:authorize>
<sec:authorize access="hasRole('STUDENT')">
<c:url var="mentorStudent" value="student"></c:url>
</sec:authorize>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="row">
          <div class="col-md-3">&nbsp;</div>
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="/mentor-sherpa-web/themes/AdminLTE/dist/img/${mentor.imageName}" alt="User profile picture">
              <h3 class="profile-username text-center"> <a href="${contextPath}/organization/viewProfile/${mentor.userObjectId}">${mentor.name}</a> </h3>
            </div>
            <!-- /.box-body -->
          </div>
        </div>
      </div>


      <c:choose>
      <c:when test="${not empty matchResults}">
     <c:forEach  items="${matchResults}" var="entry">  
 <div class="row">
         <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="/mentor-sherpa-web/themes/AdminLTE/dist/img/${entry.key.imageName}" alt="User profile picture">
              <h3 class="profile-username text-center"><a href="${contextPath}/organization/viewProfile/${entry.key.userObjectId}">${entry.key.name}</a></h3>
            <!-- /.box-header -->
            <div class="box-body text-center">
                <span><b><spring:message code="label.matchScore" /></b></span>
            </div>    
            <div class="box-body text-center">
                <span class="fa-stack fa-4x">
                  <i class="fa fa-circle fa-stack-2x icon-background4"></i>
                  <i class="fa fa-circle-thin fa-stack-2x icon-background6"></i>
                  <strong class="fa-stack-1x text-primary">${entry.value}</strong>
                </span>
            </div>
            <a href="${pageContext.request.contextPath}/organization/${mentor.userObjectId}/save/${entry.key.userObjectId}" class="btn btn-primary btn-block"><b><spring:message code="label.assign" /></b></a>
          </div>
          </div>
        </div>
 </c:forEach>  
          </c:when>
         <c:otherwise>
              <h3 align="center" class="text-red"><spring:message code="label.header.noStudentsavailable" /></h3>
         </c:otherwise>
        </c:choose>
        
  
        
 
    
         

        