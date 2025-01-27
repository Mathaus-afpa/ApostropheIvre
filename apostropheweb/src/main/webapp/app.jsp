<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.html" %>


<div
        class="sidebar flex-col fixed top-0 bottom-0 lg:left-0 mr-3 mt-3 mb-3 p-2 w-64 overflow-y-auto bg-slate-50 rounded-r-xl shadow-lg shadow-gray-500">



  <div class=" flex flex-col items-center">

    <span class="text-center">Logo provisoire</span>

    <svg class="w-48" xmlns="http://www.w3.org/2000/svg" version="1.0" width="190.000000pt" height="180.000000pt"
         viewBox="0 0 190.000000 180.000000" preserveAspectRatio="xMidYMid meet">
      <g transform="translate(0.000000,180.000000) scale(0.100000,-0.100000)" fill="#000000" stroke="none">
        <path d="M1305 1545 c-89 -39 -101 -42 -112 -28 -7 9 -14 21 -16 26 -5 17 -763 -307 -758 -324 2 -8 6 -21 8 -31 3 -10 -3 -19 -18 -23 -13 -4 -52 -20 -87 -36 l-63 -29 26 -34 26 -35 -43 -19 c-24 -10 -80 -34 -125 -52 -45 -18 -80 -37 -77 -41 2 -5 28 -21 56 -36 l51 -28 -47 -20 -47 -20 288 -119 c159 -65 293 -123 298 -129 7 -6 2 -7 -13 -2 -16 4 -49 -4 -103 -25 -43 -18 -79 -34 -79 -35 0 -2 15 -13 34 -25 32 -19 34 -24 29 -57 -5 -32 -4 -36 13 -31 10 3 255 105 543 227 288 121 529 221 537 221 7 0 17 -7 22 -16 8 -14 11 -14 26 7 25 37 20 76 -14 111 -27 27 -36 30 -74 26 l-42 -5 83 226 c46 124 92 250 103 278 l19 53 -37 -16 c-47 -20 -82 -34 -86 -34 -2 0 3 26 10 57 7 31 12 59 10 61 -2 1 -47 -15 -101 -37 -54 -23 -101 -41 -106 -41 -5 0 -9 18 -9 40 0 26 -4 40 -12 39 -7 -1 -58 -20 -113 -44z m97 -207 l15 -183 2 127 c0 70 5 137 10 150 6 17 29 32 77 51 37 15 70 25 72 23 2 -3 -5 -47 -17 -98 -40 -183 -90 -414 -96 -451 -6 -28 -13 -39 -32 -43 -15 -4 -22 -12 -19 -20 3 -8 4 -14 3 -14 -2 0 -138 56 -302 124 l-299 124 -80 -35 c-45 -19 -177 -75 -294 -124 -210 -88 -213 -89 -243 -73 -17 8 -28 18 -25 22 4 3 151 67 329 142 l322 137 165 -85 c91 -47 161 -80 155 -75 -13 13 -314 193 -323 193 -4 0 -108 -43 -231 -95 -207 -87 -225 -93 -237 -77 -7 9 -14 19 -14 22 0 3 147 68 328 144 l327 138 60 -68 c33 -38 77 -86 98 -109 36 -37 35 -36 -11 30 -27 39 -66 96 -87 128 -21 31 -43 57 -49 57 -6 0 -123 -47 -260 -105 -136 -58 -252 -105 -258 -105 -30 0 9 27 87 60 50 21 198 84 329 139 132 56 243 98 247 94 4 -4 33 -66 64 -136 32 -70 59 -126 61 -124 2 2 -13 54 -32 116 -19 62 -34 114 -32 115 11 8 155 65 164 66 7 0 16 -64 26 -182z"/>
      </g>

    </svg>

  </div>

  <a class="group" href="${pageContext.request.contextPath}/accueil">

    <div
            class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">

      <svg class="w-5 text-gray-700 group-hover:text-white" xmlns="http://www.w3.org/2000/svg" fill="currentColor"
           viewBox="0 0 576 512" stroke="currentColor"><path
              d="M575.8 255.5c0 18-15 32.1-32 32.1l-32 0 .7 160.2c0 2.7-.2 5.4-.5 8.1l0 16.2c0 22.1-17.9 40-40 40l-16 0c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1L416 512l-24 0c-22.1 0-40-17.9-40-40l0-24 0-64c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32 14.3-32 32l0 64 0 24c0 22.1-17.9 40-40 40l-24 0-31.9 0c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2l-16 0c-22.1 0-40-17.9-40-40l0-112c0-.9 0-1.9 .1-2.8l0-69.7-32 0c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/></svg>

      <span class="text-gray-700 group-hover:text-white">Accueil</span>

    </div>

  </a>

  <a href="${pageContext.request.contextPath}/livres">

    <div class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300 ">
      <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" fill="currentColor" stroke="currentColor"
           viewBox="0 0 448 512">
        <path d="M96 0C43 0 0 43 0 96L0 416c0 53 43 96 96 96l288 0 32 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l0-64c17.7 0 32-14.3 32-32l0-320c0-17.7-14.3-32-32-32L384 0 96 0zm0 384l256 0 0 64L96 448c-17.7 0-32-14.3-32-32s14.3-32 32-32zm32-240c0-8.8 7.2-16 16-16l192 0c8.8 0 16 7.2 16 16s-7.2 16-16 16l-192 0c-8.8 0-16-7.2-16-16zm16 48l192 0c8.8 0 16 7.2 16 16s-7.2 16-16 16l-192 0c-8.8 0-16-7.2-16-16s7.2-16 16-16z" />
      </svg>
      <span class="text-gray-700">Livres</span>
    </div>

  </a>

  <a href="${pageContext.request.contextPath}/connexion">

    <div
            class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">
      <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" fill="currentColor" stroke="currentColor"><path
              d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
      <span class="text-gray-700">Connexion</span>
    </div>

  </a>

<%--  ----------------------------------------------------------------- LIENS DE MATHAUS ---------------------------------------------------------------- --%>

  <div class="lienMathaus mt-8">

    <h3 class="text-center">Les liens de Mathaus</h3>

    <a href="${pageContext.request.contextPath}/libraire">

      <div
              class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">
        <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" fill="currentColor" stroke="currentColor"><path
                d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
        <span class="text-gray-700">Libraire</span>
      </div>

    </a>

    <a href="${pageContext.request.contextPath}/auteurs">

      <div
              class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">
        <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" fill="currentColor" stroke="currentColor"><path
                d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
        <span class="text-gray-700">Auteur</span>
      </div>

    </a>

    <a href="${pageContext.request.contextPath}/administrateur">

      <div
              class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">
        <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" fill="currentColor" stroke="currentColor"><path
                d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
        <span class="text-gray-700">Admin</span>
      </div>

    </a>

    <a href="${pageContext.request.contextPath}/client">

      <div
              class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">
        <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" fill="currentColor" stroke="currentColor"><path
                d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
        <span class="text-gray-700">Client</span>
      </div>

    </a>

  </div>

  <%--  ----------------------------------------------------------------- LIENS DE MATHAUS ---------------------------------------------------------------- --%>

  <%--  ----------------------------------------------------------------- LIENS DE LOUIS ---------------------------------------------------------------- --%>

  <div class="lienLouis mt-8">

    <h3 class="text-center">Les liens de Louis</h3>

    <a href="${pageContext.request.contextPath}/gestion/clients">

      <div
              class="p-2 pl-20 flex justify-start items-center bg-slate-50 rounded-md gap-3 hover:bg-neutral-400 ease-in-out duration-300">
        <svg class="w-5 text-gray-700" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" fill="currentColor" stroke="currentColor"><path
                d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
        <span class="text-gray-700">Gestion clients</span>
      </div>

    </a>
  </div>
  <%--  ----------------------------------------------------------------- LIENS DE LOUIS ---------------------------------------------------------------- --%>

</div>


<div class="content flex-col justify-items-center pl-60">
  <jsp:include page="${page}" />
</div>

<%@ include file="close.html" %>

