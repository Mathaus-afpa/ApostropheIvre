<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="flex mt-24 gap-x-6">

    <a
            class="flex w-2/4 items-center rounded-xl border border-black border-opacity-10 text-black duration-200 dark:text-white sm:flex-col">

        <img src="${pageContext.request.contextPath}/Images/livres/2757851357.jpg" alt="photo livre"
             class="h-full w-full object-cover rounded">

    </a>

    <div class="accordion-container w-[30rem]">

        <div class="flex justify-center gap-3">

            <div class="button-container mt-5 content-center">

                <%--            <button class="w-2/4 bg-white shadow-lg p-3 rounded-lg h-fit flex items-center gap-2">--%>

                <%--                <svg xmlns="http://www.w3.org/2000/svg" height="30px" viewBox="0 -960 960 960" width="30px" fill="#75FB4C">--%>
                <%--                    <path d="M382-240 154-468l57-57 171 171 367-367 57 57-424 424Z"/>--%>
                <%--                </svg>--%>

                <%--                Emprunter--%>

                <%--            </button>--%>

                <div
                        class="gr mx-auto max-w-3xl items-stretch space-y-4 text-left sm:flex sm:space-y-0 sm:space-x-8 sm:text-center hover:cursor-pointer">

                    <a class="flex w-full items-center text-slate-700 rounded-xl border border-black border-opacity-10 px-4 py-6 duration-200 hover:bg-white hover:border-opacity-0 hover:no-underline hover:shadow-lg dark:text-white sm:flex-col sm:hover:shadow-2xl">

                        <div class="w-12">
                            <svg xmlns="http://www.w3.org/2000/svg" height="48px" viewBox="0 -960 960 960" width="48px" fill="#78A75A"><path d="M378-246 154-470l43-43 181 181 384-384 43 43-427 427Z"/></svg>
                        </div>

                        <div>

                            <div class="text-sm opacity-75 dark:text-slate-700">Disponible en
                                stock !
                            </div>

                            <div class="font-semibold text-slate-700 sm:mt-4 sm:mb-2">Je l'emprunte !</div>

                        </div>

                    </a>

                </div>

            </div>

        </div>

        <div class="max-w-2xl mx-auto mt-3 divide-y shadow-lg rounded-xl bg-white px-4 py-3">

            <h4 class="text-2xl font-bold dark:text-slate-700">Titre Livre</h4>

        </div>

        <div class="max-w-2xl mx-auto mt-3 divide-y shadow-lg rounded-xl bg-white px-4 py-3">

            <h4 class="text-2xl font-bold dark:text-slate-700">Nom Auteur</h4>

        </div>

        <ul
                class="max-w-2xl mx-auto mt-10 divide-y shadow-lg rounded-xl bg-white hover:ring-2 hover:ring-inset ring-neutral-700">

            <li>
                <details class="group">

                    <summary
                            class="flex items-center gap-3 px-4 py-3 font-medium marker:content-none hover:cursor-pointer hover:text-white group-open:bg-neutral-700 group-open:rounded-t-xl">
                        <svg
                                class="w-5 h-5 text-gray-500 transition group-open:rotate-90 group-open:text-white"
                             xmlns="http://www.w3.org/2000/svg"
                             width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                  d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z">
                            </path>
                        </svg>
                        <span
                                class="text-2xl text-slate-700 font-bold group-open:text-white select-none">Resumé
                            :</span>

                    </summary>

                    <article class="px-4 pb-4">

                        <hr>

                        <p class="mt-2 select-none group-open:ring-neutral-700">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et ipsum sapien. Vestibulum molestie
                            porttitor augue vitae vulputate. Aliquam nec ex maximus, suscipit diam vel, tristique tellus.
                        </p>

                    </article>

                </details>

            </li>

        </ul>

    </div>

</div>

