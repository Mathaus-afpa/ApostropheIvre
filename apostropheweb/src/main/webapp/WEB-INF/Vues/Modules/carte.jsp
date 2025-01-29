<div class="w-80 bg-white shadow-md rounded-xl duration-500 hover:scale-105 hover:shadow-xl">

    <a href="${pageContext.request.contextPath}/livre?id=${param.livreId}">


        <img src="${pageContext.request.contextPath}/Images/livres/${param.livreImage}" alt="photo livre"
             class="h-80 object-cover mx-auto pt-2"/>

        <div class="px-3 py-3 w-full">

            <span class="text-gray-400 mr-3 uppercase text-xs">${param.livreCategorie}</span>

            <p class="text-lg font-bold text-black truncate block capitalize">${param.livreNom}</p>
            <p class="text-sm text-gray-600 cursor-auto ml-2">ISBN: ${param.livreIsbn}</p>
            <div class="flex items-center">

                <p class="text-lg font-semibold text-black cursor-auto my-3">${param.livreAuteur}</p>

            </div>

            <div class="mt-4 text-sm font-medium">

                <a
                        class=" w-full group flex justify-center rounded-lg border border-current px-5 py-3 text-gray-700 ease-out duration-300 hover:border-none hover:bg-gray-600 active:bg-gray-800"
                        href="#">
                    <span class="font-medium group-hover:text-green-400 group-hover:font-normal">Emprunter</span>

                </a>

            </div>

        </div>

    </a>

</div>