<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<c:forEach var="key" items='${["common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus","common.confirm"]}'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
</c:forEach>