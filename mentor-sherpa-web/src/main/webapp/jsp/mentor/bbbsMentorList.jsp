<%-- 
    Document   : list
    Created on : Sep 10, 2016, 8:40:06 PM
    Authvor     : Sudheer.Parasker@SnapIT.Solutions
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<sec:authentication var="user" property="principal" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="row">
    <div class="col-xs-12">          
        <div class="box box-primary">
            <div class="box-header">
                <h3 class="box-title"><spring:message code="pageTitle.student" /></h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <table id="Mentor List" class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th><spring:message code="list.table.header.mentorName" /></th>
                            <th><spring:message code="list.table.header.age" /></th>
                            <th><spring:message code="list.table.header.gender" /></th>
                            <th><spring:message code="list.table.header.status" /></th>
                        </tr>
                    </thead>
                    <tbody><c:if test="${not empty assignedMentorInfoMap}">
                            <c:forEach items="${assignedMentorInfoMap}" var="entry">
                                <tr> 
                                    <td>
                                     <a href="${contextPath}/organization/viewProfile/${entry.key.userObjectId}">${entry.key.name}</a>
                                     &rarr; &nbsp;
                                     <sec:authorize access="hasRole('ORG_ADMIN') OR hasRole('ORG_USER')">
                                     <a href="${pageContext.request.contextPath}/organization/${entry.key.id}/assign">
                                         <c:choose>
                                             <c:when test="${not empty entry.value}">
                                               <span class="btn bg-blue btn-sm margin"><spring:message code="link.label.assignMoreStudent"/></span>  
                                             </c:when>
                                             <c:otherwise>
                                                <span class="btn bg-blue btn-sm margin"><spring:message code="link.label.assignStudent"/></span> 
                                             </c:otherwise>
                                         </c:choose>  
                                     </a>
                                     </sec:authorize>
                                    </td>
                                    <td>${entry.key.age}</td>
                                    <td>${entry.key.gender}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty entry.value}">
                                                <span class="label label-success"><spring:message code="label.assignedTo"/></span>
                                                <c:forEach items="${entry.value}" var="assignedStudent">
                                                    <ul style="padding-left: 10px;">
                                                      <a href="${contextPath}/organization/viewProfile/${assignedStudent.userObjectId}">${assignedStudent.name}</a>
                                                      &rarr; &nbsp;
                                                      <a href="${pageContext.request.contextPath}/organization/${entry.key.userObjectId}/remove/${assignedStudent.userObjectId}">
                                                          <span class="btn btn-xs btn-danger"><spring:message code="link.label.unassign"/></span>  
                                                      </a>
                                                    </ul>
                                                </c:forEach> 
                                            </c:when>
                                            <c:otherwise>
                                                <span class="label label-info"><spring:message code="label.available"/></span>
                                            </c:otherwise>
                                        </c:choose>
                                     </td>
                                </c:forEach>
                            </c:if>

                    </tbody>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>
