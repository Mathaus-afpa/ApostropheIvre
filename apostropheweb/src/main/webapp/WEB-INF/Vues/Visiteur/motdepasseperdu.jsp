<%--
  Created by IntelliJ IDEA.
  User: Fabulous
  Date: 29/01/2025
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="rounded-b-lg w-[32rem] mt-56">

    <h2 class="text-center text-3xl font-extrabold text-gray-700">
        Oh non ! Comment ça tu as perdu ton mot de passe petit(e) étourdi(e) ?!
    </h2>

    <div class="p-8">

        <div class="px-8 py-4 bg-gray-700 text-center rounded-xl">
            <p class="text-gray-400">Pas de panique ! Renseignez l'adresse mail liée à votre compte et vous recevrez un </p>
            <p class="font-medium text-green-500 animate-pulse ease-linear duration-300">Email de récupération.</p>
        </div>

        <form method="POST" action="./connexion" class="mt-8 space-y-6">

            <div class="rounded-md shadow-sm">

                <div>
                    <label class="sr-only" for="mail">Email</label>
                    <input placeholder="Adresse email"
                           class="appearance-none relative block w-full px-3 py-3 text-gray-700 rounded-md focus:outline outline-offset-0 outline-gray-600 focus:outline-2 shadow-md sm:text-sm"
                           type="text"
                           id="mail" name="mail"
                           required/>
                </div>

            </div>

            <div>
                <button
                        class="group relative w-full flex justify-center py-3 px-4 border border-transparent text-base font-medium rounded-md text-gray-900 bg-green-500 hover:bg-lime-400 focus:outline-none ease-out duration-300"
                        type="submit"
                >
                    Recevoir
                </button>
            </div>
        </form>
    </div>

</div>
