<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.html" %>


<%-- 00000000000000000000000000000000000000000000000000000000000000 Top Navbar small screen 00000000000000000000000000000000000000000000000000000000000000 --%>

<div
        class="z-20 mt-2 transition-all duration-[450ms] ease-in-out w-75 md:pl-60 lg:hidden block fixed"
>
  <article
          class="border border-solid border-gray-700 w-full left-0 rounded-2xl flex shadow-lg shadow-black/15 bg-white"
  >
    <a href="${pageContext.request.contextPath}/accueil"
            class="group relative w-full h-16 p-4 border-solid border-black/10 group flex flex-row gap-3 items-center justify-center text-black rounded-xl active:fill-green-400"

    >
      <svg
              class="fill-black text-2xl ease-in-out duration-100 group-active:fill-green-400"
           xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#d6e2f4"><path d="M240-200h120v-240h240v240h120v-360L480-740 240-560v360Zm-80 80v-480l320-240 320 240v480H520v-240h-80v240H160Zm320-350Z"/></svg>

    </a>

    <a href="${pageContext.request.contextPath}/connexion" class="group relative w-full h-16 p-4 ease-in-out duration-300 border-solid border-black/10 group flex flex-row gap-3 items-center justify-center text-black rounded-xl"
    >
      <svg
              viewBox="0 0 24 24"
              height="24"
              width="24"
              xmlns="http://www.w3.org/2000/svg"
              class="fill-black text-2xl ease-in-out duration-100 group-active:fill-green-400"
      >
        <path
                d="M12 2a5 5 0 1 0 5 5 5 5 0 0 0-5-5zm0 8a3 3 0 1 1 3-3 3 3 0 0 1-3 3zm9 11v-1a7 7 0 0 0-7-7h-4a7 7 0 0 0-7 7v1h2v-1a5 5 0 0 1 5-5h4a5 5 0 0 1 5 5v1z"
        ></path>
      </svg>
    </a>
    <a href="${pageContext.request.contextPath}/livres"
            class="group relative w-full h-16 p-4 border-solid border-black/10 group flex flex-row gap-3 items-center justify-center text-black rounded-xl"

    >
      <svg
              class="duration-100 group-active:fill-green-400 fill-black text-2xl" xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#d6e2f4"><path
              d="M560-564v-68q33-14 67.5-21t72.5-7q26 0 51 4t49 10v64q-24-9-48.5-13.5T700-600q-38 0-73 9.5T560-564Zm0 220v-68q33-14 67.5-21t72.5-7q26 0 51 4t49 10v64q-24-9-48.5-13.5T700-380q-38 0-73 9t-67 27Zm0-110v-68q33-14 67.5-21t72.5-7q26 0 51 4t49 10v64q-24-9-48.5-13.5T700-490q-38 0-73 9.5T560-454ZM260-320q47 0 91.5 10.5T440-278v-394q-41-24-87-36t-93-12q-36 0-71.5 7T120-692v396q35-12 69.5-18t70.5-6Zm260 42q44-21 88.5-31.5T700-320q36 0 70.5 6t69.5 18v-396q-33-14-68.5-21t-71.5-7q-47 0-93 12t-87 36v394Zm-40 118q-48-38-104-59t-116-21q-42 0-82.5 11T100-198q-21 11-40.5-1T40-234v-482q0-11 5.5-21T62-752q46-24 96-36t102-12q58 0 113.5 15T480-740q51-30 106.5-45T700-800q52 0 102 12t96 36q11 5 16.5 15t5.5 21v482q0 23-19.5 35t-40.5 1q-37-20-77.5-31T700-240q-60 0-116 21t-104 59ZM280-494Z"/></svg>
    </a>
    <a href="${pageContext.request.contextPath}/cgu"
            class="group relative w-full h-16 p-4 border-solid border-black/10 group flex flex-row gap-3 items-center justify-center text-black rounded-xl"
    >
      <svg
              viewBox="0 0 24 24"
              height="24"
              width="24"
              xmlns="http://www.w3.org/2000/svg"
              class="group-active:fill-green-400 text-2xl ease-in-out duration-100"
      >
        <path
                d="M11.953 2C6.465 2 2 6.486 2 12s4.486 10 10 10 10-4.486 10-10S17.493 2 11.953 2zM12 20c-4.411 0-8-3.589-8-8s3.567-8 7.953-8C16.391 4 20 7.589 20 12s-3.589 8-8 8z"
        ></path>
        <path d="M11 7h2v7h-2zm0 8h2v2h-2z"></path>
      </svg>
    </a>
  </article>
</div>


<%-- 00000000000000000000000000000000000000000000000000000000000000 Top Navbar small screen 00000000000000000000000000000000000000000000000000000000000000 --%>



<div class="sidebar hidden lg:block fixed top-0 bottom-0 left-0 lg:left-0 w-64 bg-slate-50 shadow-lg shadow-gray-500 rounded-r-xl overflow-y-auto p-2 mt-3 mb-3 transition-transform duration-300 transform -translate-x-full lg:translate-x-0">

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

