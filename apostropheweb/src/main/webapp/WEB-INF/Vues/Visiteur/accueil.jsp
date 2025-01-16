<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1 class="text text-7xl mt-3">Bienvenue !</h1>

<div
        class="mt-16 flex items-center border w-80 focus-within:border-indigo-500 transition duration-300 pr-3 gap-2 bg-white border-gray-500/30 h-[46px] rounded-[5px] overflow-hidden">

    <input
            type="text"
            placeholder="Rechercher un livre"
            class="w-full h-full pl-4 outline-none placeholder-gray-500 text-sm"/>

    <svg
            xmlns="http://www.w3.org/2000/svg"
            x="0px"
            y="0px"
            width="22"
            height="22"
            viewBox="0 0 30 30"
            fill="#6B7280">

        <path d="M 13 3 C 7.4889971 3 3 7.4889971 3 13 C 3 18.511003 7.4889971 23 13 23 C 15.396508 23 17.597385 22.148986 19.322266 20.736328 L 25.292969 26.707031 A 1.0001 1.0001 0 1 0 26.707031 25.292969 L 20.736328 19.322266 C 22.148986 17.597385 23 15.396508 23 13 C 23 7.4889971 18.511003 3 13 3 z M 13 5 C 17.430123 5 21 8.5698774 21 13 C 21 17.430123 17.430123 21 13 21 C 8.5698774 21 5 17.430123 5 13 C 5 8.5698774 8.5698774 5 13 5 z"
        ></path>
    </svg>

</div>

<h2 class="space-y-96 text-5xl mt-32">Nouveautés:</h2>

<div class="card-container flex justify-center mt-8 gap-20">

    <!--  Début Livres cards -->
    <div class="w-72 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">

        <a href="${pageContext.request.contextPath}/livres/details">

            <img src="./Images/livres/2757851357.jpg" alt="photo livre" class="h-80 w-72 object-cover rounded-t-xl" />

            <div class="px-4 py-3 w-72">

                <span class="text-gray-400 mr-3 uppercase text-xs">Catégorie</span>

                <p class="text-lg font-bold text-black truncate block capitalize">Titre Livre</p>

                <p class="text-sm text-gray-600 cursor-auto ml-2">Année</p>

                <div class="flex items-center">

                    <p class="text-lg font-semibold text-black cursor-auto my-3">Nom Auteur</p>

                </div>

                <div class="flex mb-4 text-sm font-medium justify-end">

                    <a class="group flex items-center justify-between gap-4 rounded-lg border border-current px-5 py-3 text-indigo-600 transition-colors hover:bg-indigo-600 focus:outline-none focus:ring active:bg-indigo-500"
                            href="#">
                        <span class="font-medium transition-colors group-hover:text-white">Emprunter</span>

                    </a>

                </div>

            </div>

        </a>

    </div>
    <!--  Fin Livres cards -->

    <div class="w-72 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">

        <a href="${pageContext.request.contextPath}/livres/details">

            <img src="./Images/livres/2757851357.jpg" alt="photo livre" class="h-80 w-72 object-cover rounded-t-xl" />

            <div class="px-4 py-3 w-72">

                <span class="text-gray-400 mr-3 uppercase text-xs">Catégorie</span>

                <p class="text-lg font-bold text-black truncate block capitalize">Titre Livre</p>

                <p class="text-sm text-gray-600 cursor-auto ml-2">Année</p>

                <div class="flex items-center">

                    <p class="text-lg font-semibold text-black cursor-auto my-3">Nom Auteur</p>

                </div>

                <div class="flex mb-4 text-sm font-medium justify-end">

                    <a class="group flex items-center justify-between gap-4 rounded-lg border border-current px-5 py-3 text-indigo-600 transition-colors hover:bg-indigo-600 focus:outline-none focus:ring active:bg-indigo-500"
                       href="#">
                        <span class="font-medium transition-colors group-hover:text-white">Emprunter</span>

                    </a>

                </div>

            </div>

        </a>

    </div>

    <div class="w-72 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">

        <a href="${pageContext.request.contextPath}/livres/details">

            <img src="./Images/livres/2757851357.jpg" alt="photo livre" class="h-80 w-72 object-cover rounded-t-xl" />

            <div class="px-4 py-3 w-72">

                <span class="text-gray-400 mr-3 uppercase text-xs">Catégorie</span>

                <p class="text-lg font-bold text-black truncate block capitalize">Titre Livre</p>

                <p class="text-sm text-gray-600 cursor-auto ml-2">Année</p>

                <div class="flex items-center">

                    <p class="text-lg font-semibold text-black cursor-auto my-3">Nom Auteur</p>

                </div>

                <div class="flex mb-4 text-sm font-medium justify-end">

                    <a class="group flex items-center justify-between gap-4 rounded-lg border border-current px-5 py-3 text-indigo-600 transition-colors hover:bg-indigo-600 focus:outline-none focus:ring active:bg-indigo-500"
                       href="#">
                        <span class="font-medium transition-colors group-hover:text-white">Emprunter</span>

                    </a>

                </div>

            </div>

        </a>

    </div>

</div>

