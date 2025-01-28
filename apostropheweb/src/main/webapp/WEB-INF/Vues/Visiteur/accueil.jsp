<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1 class="text text-7xl mt-3">Bienvenue !</h1>

<div class=" flex flex-col items-center">

    <svg class="w-48" xmlns="http://www.w3.org/2000/svg" version="1.0" width="190.000000pt" height="180.000000pt"
         viewBox="0 0 190.000000 180.000000" preserveAspectRatio="xMidYMid meet">
        <g transform="translate(0.000000,180.000000) scale(0.100000,-0.100000)" fill="#000000" stroke="none">
            <path d="M1305 1545 c-89 -39 -101 -42 -112 -28 -7 9 -14 21 -16 26 -5 17 -763 -307 -758 -324 2 -8 6 -21 8 -31 3 -10 -3 -19 -18 -23 -13 -4 -52 -20 -87 -36 l-63 -29 26 -34 26 -35 -43 -19 c-24 -10 -80 -34 -125 -52 -45 -18 -80 -37 -77 -41 2 -5 28 -21 56 -36 l51 -28 -47 -20 -47 -20 288 -119 c159 -65 293 -123 298 -129 7 -6 2 -7 -13 -2 -16 4 -49 -4 -103 -25 -43 -18 -79 -34 -79 -35 0 -2 15 -13 34 -25 32 -19 34 -24 29 -57 -5 -32 -4 -36 13 -31 10 3 255 105 543 227 288 121 529 221 537 221 7 0 17 -7 22 -16 8 -14 11 -14 26 7 25 37 20 76 -14 111 -27 27 -36 30 -74 26 l-42 -5 83 226 c46 124 92 250 103 278 l19 53 -37 -16 c-47 -20 -82 -34 -86 -34 -2 0 3 26 10 57 7 31 12 59 10 61 -2 1 -47 -15 -101 -37 -54 -23 -101 -41 -106 -41 -5 0 -9 18 -9 40 0 26 -4 40 -12 39 -7 -1 -58 -20 -113 -44z m97 -207 l15 -183 2 127 c0 70 5 137 10 150 6 17 29 32 77 51 37 15 70 25 72 23 2 -3 -5 -47 -17 -98 -40 -183 -90 -414 -96 -451 -6 -28 -13 -39 -32 -43 -15 -4 -22 -12 -19 -20 3 -8 4 -14 3 -14 -2 0 -138 56 -302 124 l-299 124 -80 -35 c-45 -19 -177 -75 -294 -124 -210 -88 -213 -89 -243 -73 -17 8 -28 18 -25 22 4 3 151 67 329 142 l322 137 165 -85 c91 -47 161 -80 155 -75 -13 13 -314 193 -323 193 -4 0 -108 -43 -231 -95 -207 -87 -225 -93 -237 -77 -7 9 -14 19 -14 22 0 3 147 68 328 144 l327 138 60 -68 c33 -38 77 -86 98 -109 36 -37 35 -36 -11 30 -27 39 -66 96 -87 128 -21 31 -43 57 -49 57 -6 0 -123 -47 -260 -105 -136 -58 -252 -105 -258 -105 -30 0 9 27 87 60 50 21 198 84 329 139 132 56 243 98 247 94 4 -4 33 -66 64 -136 32 -70 59 -126 61 -124 2 2 -13 54 -32 116 -19 62 -34 114 -32 115 11 8 155 65 164 66 7 0 16 -64 26 -182z"/>
        </g>

    </svg>

</div>

<a href="${pageContext.request.contextPath}/livres"> * Voir nos livres *</a>

<%--<div--%>
<%--        class="mt-16 flex items-center border w-80 focus-within:border-neutral-500 transition duration-300 pr-3 gap-2 bg-white border-gray-500/30 h-[46px] rounded-[5px] overflow-hidden">--%>

<%--    <input--%>
<%--            type="text"--%>
<%--            placeholder="Rechercher un livre"--%>
<%--            class="w-full h-full pl-4 outline-none placeholder-gray-500 text-sm"/>--%>

<%--    <svg--%>
<%--            xmlns="http://www.w3.org/2000/svg"--%>
<%--            x="0px"--%>
<%--            y="0px"--%>
<%--            width="22"--%>
<%--            height="22"--%>
<%--            viewBox="0 0 30 30"--%>
<%--            fill="#6B7280">--%>

<%--        <path d="M 13 3 C 7.4889971 3 3 7.4889971 3 13 C 3 18.511003 7.4889971 23 13 23 C 15.396508 23 17.597385 22.148986 19.322266 20.736328 L 25.292969 26.707031 A 1.0001 1.0001 0 1 0 26.707031 25.292969 L 20.736328 19.322266 C 22.148986 17.597385 23 15.396508 23 13 C 23 7.4889971 18.511003 3 13 3 z M 13 5 C 17.430123 5 21 8.5698774 21 13 C 21 17.430123 17.430123 21 13 21 C 8.5698774 21 5 17.430123 5 13 C 5 8.5698774 8.5698774 5 13 5 z"--%>
<%--        ></path>--%>
<%--    </svg>--%>

<%--</div>--%>

<%--<h2 class="space-y-96 text-5xl mt-20">Nouveautés :</h2>--%>

<%--<div class="card-container flex justify-center mt-8 gap-20">--%>

<%--    <!--  Début Livres cards -->--%>
<%--    <div class="w-80 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">--%>

<%--        <a href="${pageContext.request.contextPath}/livre/details">--%>

<%--            <img src="${pageContext.request.contextPath}/Images/livres/2757851357.jpg" alt="photo livre"--%>
<%--                 class="h-80 w-80 object-cover rounded-t-xl"/>--%>

<%--            <div class="px-3 py-3 w-full">--%>

<%--                <span class="text-gray-400 mr-3 uppercase text-xs">Catégorie</span>--%>

<%--                <p class="text-lg font-bold text-black truncate block capitalize">Titre Livre</p>--%>

<%--                <p class="text-sm text-gray-600 cursor-auto ml-2">Année</p>--%>

<%--                <div class="flex items-center">--%>

<%--                    <p class="text-lg font-semibold text-black cursor-auto my-3">Nom Auteur</p>--%>

<%--                </div>--%>

<%--                <div class="mt-4 text-sm font-medium">--%>

<%--                    <a--%>
<%--                            class=" w-full group flex justify-center rounded-lg border border-current px-5 py-3 text-zinc-500 ease-out duration-300 hover:border-none hover:bg-neutral-600 active:bg-neutral-500"--%>
<%--                            href="#">--%>
<%--                        <span class="font-medium group-hover:text-white">Emprunter</span>--%>

<%--                    </a>--%>

<%--                </div>--%>

<%--            </div>--%>

<%--        </a>--%>

<%--    </div>--%>
<%--    <!--  Fin Livres cards -->--%>

<%--    <div class="w-80 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">--%>

<%--        <a href="${pageContext.request.contextPath}/livres/details">--%>

<%--            <img src="./Images/livres/2757851357.jpg" alt="photo livre" class="h-80 w-80 object-cover rounded-t-xl" />--%>

<%--            <div class="px-3 py-3 w-full">--%>

<%--                <span class="text-gray-400 mr-3 uppercase text-xs">Catégorie</span>--%>

<%--                <p class="text-lg font-bold text-black truncate block capitalize">Titre Livre</p>--%>

<%--                <p class="text-sm text-gray-600 cursor-auto ml-2">Année</p>--%>

<%--                <div class="flex items-center">--%>

<%--                    <p class="text-lg font-semibold text-black cursor-auto my-3">Nom Auteur</p>--%>

<%--                </div>--%>

<%--                <div class="mt-4 text-sm font-medium">--%>

<%--                    <a--%>
<%--                            class=" w-full group flex justify-center rounded-lg border border-current px-5 py-3 text-zinc-500 ease-out duration-300 hover:border-none hover:bg-neutral-600 active:bg-neutral-500"--%>
<%--                            href="#">--%>
<%--                        <span class="font-medium group-hover:text-white">Emprunter</span>--%>

<%--                    </a>--%>

<%--                </div>--%>

<%--            </div>--%>

<%--        </a>--%>

<%--    </div>--%>

<%--    <div class="w-80 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">--%>

<%--        <a href="${pageContext.request.contextPath}/livres/details">--%>

<%--            <img src="./Images/livres/2757851357.jpg" alt="photo livre" class="h-80 w-80 object-cover rounded-t-xl" />--%>

<%--            <div class="px-3 py-3 w-full">--%>

<%--                <span class="text-gray-400 mr-3 uppercase text-xs">Catégorie</span>--%>

<%--                <p class="text-lg font-bold text-black truncate block capitalize">Titre Livre</p>--%>

<%--                <p class="text-sm text-gray-600 cursor-auto ml-2">Année</p>--%>

<%--                <div class="flex items-center">--%>

<%--                    <p class="text-lg font-semibold text-black cursor-auto my-3">Nom Auteur</p>--%>

<%--                </div>--%>

<%--                <div class="mt-4 text-sm font-medium">--%>

<%--                    <a--%>
<%--                            class=" w-full group flex justify-center rounded-lg border border-current px-5 py-3 text-zinc-500 ease-out duration-300 hover:border-none hover:bg-neutral-600 active:bg-neutral-500"--%>
<%--                            href="#">--%>
<%--                        <span class="font-medium group-hover:text-white">Emprunter</span>--%>

<%--                    </a>--%>

<%--                </div>--%>

<%--            </div>--%>

<%--        </a>--%>

<%--    </div>--%>

<%--</div>--%>

