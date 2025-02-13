<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.html" %>


<div class="sidebar fixed top-0 bottom-0 left-0 lg:left-0 w-64 bg-slate-50 shadow-lg shadow-gray-500 rounded-r-xl overflow-y-auto p-2 mt-3 mb-3 transition-transform duration-300 transform -translate-x-full lg:translate-x-0">



  <div class="flex flex-col items-center p-2 mb-4">

    <img src="${pageContext.request.contextPath}/Images/logos/logoApo.png" alt="logo apostropheivre">

  </div>

  <a class="group" href="${pageContext.request.contextPath}/accueil">

    <div
            class="p-2 pl-16 flex justify-start items-center bg-slate-50 rounded gap-3 hover:bg-gray-700 ease-in-out duration-300">
      <svg class="w-8 text-gray-700 group-hover:text-lime-300" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor" stroke="currentColor"><path
              d="M240-200h120v-240h240v240h120v-360L480-740 240-560v360Zm-80 80v-480l320-240 320 240v480H520v-240h-80v240H160Zm320-350Z"/></svg>
      <span class="pt-1 text-gray-700 font-medium group-hover:text-lime-300 group-hover:font-normal">Accueil</span>

    </div>

  </a>

  <a class="group" href="${pageContext.request.contextPath}/livres">

    <div class="p-2 pl-16 flex justify-start items-center bg-slate-50 rounded gap-3 hover:bg-gray-700 ease-in-out duration-300 ">

  <svg class="w-8 text-gray-700 group-hover:text-lime-300" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor" stroke="currentColor"><path
          d="M480-160q-48-38-104-59t-116-21q-42 0-82.5 11T100-198q-21 11-40.5-1T40-234v-482q0-11 5.5-21T62-752q46-24 96-36t102-12q58 0 113.5 15T480-740v484q51-32 107-48t113-16q36 0 70.5 6t69.5 18v-480q15 5 29.5 10.5T898-752q11 5 16.5 15t5.5 21v482q0 23-19.5 35t-40.5 1q-37-20-77.5-31T700-240q-60 0-116 21t-104 59Zm80-200v-380l200-200v400L560-360Zm-160 65v-396q-33-14-68.5-21.5T260-720q-37 0-72 7t-68 21v397q35-13 69.5-19t70.5-6q36 0 70.5 6t69.5 19Zm0 0v-396 396Z"/></svg>
      <span class="text-gray-700 font-medium group-hover:text-lime-300 group-hover:font-normal">Livres</span>
    </div>

  </a>

  <a class="group" href="${pageContext.request.contextPath}/connexion">

    <div
            class="p-2 pl-16 flex justify-start items-center bg-slate-50 rounded gap-3 hover:bg-gray-700 ease-in-out duration-300">
      <svg class="w-8 text-gray-700 group-hover:text-lime-300" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor" stroke="currentColor"><path
              d="M480-440q-58 0-99-41t-41-99q0-58 41-99t99-41q58 0 99 41t41 99q0 58-41 99t-99 41Zm0-80q25 0 42.5-17.5T540-580q0-25-17.5-42.5T480-640q-25 0-42.5 17.5T420-580q0 25 17.5 42.5T480-520Zm0 460L120-280v-400l360-220 360 220v400L480-60Zm0-93 147-91q-34-18-71.5-27t-75.5-9q-38 0-75.5 9T333-244l147 91ZM256-291q50-34 107-51.5T480-360q60 0 117 17.5T704-291l56-33v-311L480-806 200-635v311l56 33Zm224-189Z"/></svg>
            <span class="text-gray-700 font-medium group-hover:text-lime-300 group-hover:font-normal">S'identifier</span>
    </div>

  </a>

  <a class="group" href="${pageContext.request.contextPath}/cgu">

    <div
            class="p-2 pl-16 flex justify-start items-center bg-slate-50 rounded gap-3 hover:bg-gray-700 ease-in-out duration-300">
      <svg class="w-8 text-gray-700 group-hover:text-lime-300" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="currentColor" stroke="currentColor"><path
              d="M480-80q-139-35-229.5-159.5T160-516v-244l320-120 320 120v244q0 85-29 163.5T688-214L560-342q-18 11-38.5 16.5T480-320q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 22-5.5 42.5T618-398l60 60q20-41 31-86t11-92v-189l-240-90-240 90v189q0 121 68 220t172 132q26-8 49.5-20.5T576-214l56 56q-33 27-71.5 47T480-80Zm0-320q33 0 56.5-23.5T560-480q0-33-23.5-56.5T480-560q-33 0-56.5 23.5T400-480q0 33 23.5 56.5T480-400Zm8-77Z"/></svg>
            <span class="text-gray-700 font-medium group-hover:text-lime-300 group-hover:font-normal">CGU</span>
    </div>

  </a>

  <div class="libraire mt-8">

    <div class="">

    </div>

  </div>


</div>


<div class="content flex-col justify-items-center md:pl-60">
  <jsp:include page="${page}" />
</div>

<%@ include file="close.html" %>

