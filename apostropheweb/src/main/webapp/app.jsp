<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.html" %>
<div class="sidebar">
  <a href="${pageContext.request.contextPath}/accueil"><i>🏠</i></a>
  <a href="${pageContext.request.contextPath}/livres"><i>📖</i></a>
  <a href="${pageContext.request.contextPath}/connexion"><i>🔓</i></a>
</div>
<div class="content">
  <jsp:include page="${page}" />
</div>
<%@ include file="close.html" %>