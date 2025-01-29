<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<div>--%>
<%--    <form action="./connexion" method="POST">--%>
<%--        <label for="username">Nom d'utilisateur :</label>--%>
<%--        <input type="text" id="username" name="username" required>--%>
<%--        <br><br>--%>
<%--        <label for="password">Mot de passe :</label>--%>
<%--        <input type="password" id="password" name="password" required>--%>
<%--        <br><br>--%>
<%--        <button type="submit">Se connecter</button>--%>
<%--    </form>--%>
<%--</div>--%>

<div class="rounded-b-lg w-[32rem] mt-56">

    <div class="p-8">

        <h2 class="text-center text-3xl font-extrabold text-gray-700">
            Connecte toi ! Petit(e) ivrogne !
        </h2>
        <%--<p class="mt-4 text-center text-gray-400">Sign in to continue</p>--%>
        <form method="POST" action="./connexion" class="mt-8 space-y-6">

            <div class="rounded-md shadow-sm">

                <div>
                    <label class="sr-only" for="username">Identifiant</label>
                    <input placeholder="Identifiant"
                           class="appearance-none relative block w-full px-3 py-3 text-gray-700 rounded-md focus:outline outline-offset-0 outline-gray-600 focus:outline-2 shadow-md sm:text-sm"
                            type="text"
                            id="username" name="username"
                            required/>
                </div>

                <div class="mt-4">
                    <label class="sr-only" for="password">Mot de passe</label>
                    <input
                            placeholder="Mot de passe"
                            class="appearance-none relative block w-full px-3 py-3 text-gray-700 rounded-md focus:outline outline-offset-0 outline-gray-600 focus:outline-2 shadow-md sm:text-sm"
                            required
                            type="password"
                            name="password"
                            id="password"
                    />
                </div>

            </div>
<%--            <div class="flex items-center justify-between mt-4">--%>
<%--                <div class="flex items-center">--%>
<%--                    <input--%>
<%--                            class="h-4 w-4 text-indigo-500 focus:ring-indigo-400 border-gray-600 rounded"--%>
<%--                            type="checkbox"--%>
<%--                            name="remember-me"--%>
<%--                            id="remember-me"--%>
<%--                    />--%>
<%--                    <label class="ml-2 block text-sm text-gray-400" for="remember-me"--%>
<%--                    >Se souvenir de moi</label--%>
<%--                    >--%>
<%--                </div>--%>

<%--                <div class="text-sm">--%>
<%--                    <a--%>
<%--                            class="font-medium text-gray-700 hover:text-green-700 hover:underline"--%>
<%--                            href="#"--%>
<%--                    >--%>
<%--                        Mot de passe oublié ?--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--            </div>--%>
            <div class="flex justify-between">

                <div class="flex items-center gap-1">

                    <div class="inline-flex items-center">

                        <label class="flex items-center cursor-pointer relative">

                            <input defaultChecked type="checkbox" class="peer h-6 w-6 cursor-pointer transition-all appearance-none rounded-full bg-slate-100 shadow hover:shadow-md border border-slate-300 checked:bg-green-500 checked:border-green-500" id="check-custom-style" />

                            <span class="absolute text-slate-800 opacity-0 peer-checked:opacity-100 top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-3.5 w-3.5" viewBox="0 0 20 20" fill="currentColor" stroke="currentColor" stroke-width="1">
                                  <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path>
                                </svg>
                            </span>

                        </label>

                    </div>

                    <span class="text-sm select-none text-gray-400 pt-1">Se souvenir de moi/Rester connecté</span>

                </div>

                <div class="flex items-center text-sm">

                    <a
                            class="pt-1 font-medium text-gray-700 hover:text-green-600 hover:underline hover:underline-offset-4 ease-out duration-300 select-none"
                            href="#">
                        Mot de passe oublié ?
                    </a>

                </div>

            </div>


            <div>
                <button
                        class="group relative w-full flex justify-center py-3 px-4 border border-transparent text-base font-medium rounded-md text-gray-900 bg-green-500 hover:bg-lime-400 focus:outline-none ease-out duration-300"
                        type="submit"
                >
                    Se connecter
                </button>
            </div>
        </form>
    </div>

    <div class="px-8 py-4 bg-gray-700 text-center rounded-xl">

        <span class="text-gray-400">Pas encore membre ?</span>
        <a class="font-medium text-green-500 hover:text-green-400 ease-out duration-300 hover:underline hover:underline-offset-4"
           href="${pageContext.request.contextPath}/inscription"
        >Créer un compte.</a>

    </div>

</div>


