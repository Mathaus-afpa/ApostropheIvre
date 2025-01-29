<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% String successMessage = (String) request.getAttribute("successMessage"); %>

<div>
    <% if (errorMessage != null) { %>
    <p class="text-red-500 text-sm"><%= errorMessage %></p>
    <% } else if (successMessage != null) { %>
    <p class="text-green-500 text-sm"><%= successMessage %></p>
    <% } %>
</div>

<div
        class="grid md:grid-cols-3 w-[47rem] mt-56 items-center bg-gray-700 shadow-[0_2px_10px_-3px_rgba(6,81,237,0.3)] rounded-xl overflow-hidden">

    <form action="./connexion" method="POST" class="md:col-span-3 w-full py-6 px-6 sm:px-16 max-md:max-w-xl mx-auto" >

        <input type="hidden" name="action" value="register">

        <div class="mb-6">
            <h3 class="text-green-500 text-xl font-bold">Nouveau membre</h3>
        </div>

        <div class="space-y-6">

            <div>

                <label class="text-gray-400 text-sm mb-2 block" for="login">Identifiant</label>

                <div class="relative flex items-center">
                    <input name="login" id="login" type="text" required
                           class="text-white tracking-normal bg-zinc-500 border border-stone-800 w-full text-sm pl-4 pr-10 py-2.5 rounded-md focus:outline outline-offset-0 outline-green-500 focus:outline-2" placeholder="Entrer identifiant" />
                </div>

            </div>

            <div>

                <label class="text-gray-400 text-sm mb-2 block">Email</label>
                <div class="relative flex items-center">
                    <input name="mail" id="mail" type="email" required
                           class="text-white tracking-normal bg-zinc-500 border border-stone-800 w-full text-sm pl-4 pr-10 py-2.5 rounded-md focus:outline outline-offset-0 outline-green-500 focus:outline-2" placeholder="Entrer email" />
                </div>
            </div>

            <div>
                <label class="text-gray-400 text-sm mb-2 block">Mot de passe</label>
                <div class="relative flex items-center">
                    <input name="password" id="password" type="password" required
                           class="text-white tracking-normal bg-zinc-500 border border-stone-800 w-full text-sm pl-4 pr-10 py-2.5 rounded-md focus:outline outline-offset-0 outline-green-500 focus:outline-2" placeholder="Entrer mot de passe" />
                    <svg xmlns="http://www.w3.org/2000/svg" fill="#bbb" stroke="#bbb" class="w-4 h-4 absolute right-4 cursor-pointer" viewBox="0 0 128 128">
                        <path d="M64 104C22.127 104 1.367 67.496.504 65.943a4 4 0 0 1 0-3.887C1.367 60.504 22.127 24 64 24s62.633 36.504 63.496 38.057a4 4 0 0 1 0 3.887C126.633 67.496 105.873 104 64 104zM8.707 63.994C13.465 71.205 32.146 96 64 96c31.955 0 50.553-24.775 55.293-31.994C114.535 56.795 95.854 32 64 32 32.045 32 13.447 56.775 8.707 63.994zM64 88c-13.234 0-24-10.766-24-24s10.766-24 24-24 24 10.766 24 24-10.766 24-24 24zm0-40c-8.822 0-16 7.178-16 16s7.178 16 16 16 16-7.178 16-16-7.178-16-16-16z" data-original="#000000"></path>
                    </svg>
                </div>
            </div>

            <div class="flex items-center">

                <div class="inline-flex items-center">

                    <label class="flex items-center cursor-pointer relative">

                        <input defaultChecked id="cgu" name="cgu" type="checkbox" required
                               class="peer h-6 w-6 cursor-pointer transition-all appearance-none rounded-full bg-slate-100 shadow hover:shadow-md border border-slate-300 checked:bg-green-500 checked:border-green-500" id="check-custom-style" />

                        <span class="absolute text-slate-800 opacity-0 peer-checked:opacity-100 top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-3.5 w-3.5" viewBox="0 0 20 20" fill="currentColor" stroke="currentColor" stroke-width="1">
                                  <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path>
                                </svg>
                        </span>

                    </label>

                </div>
                <label for="cgu" class="ml-1 pt-1 block text-sm text-gray-400">
                    J'accepte les termes et les conditions.<a href="#"
                               class="font-bold text-green-600 tracking-wider hover:text-lime-300 hover:underline hover:underline-offset-2 ml-1 ease-out duration-300">Termes
                    et Conditions</a>
                </label>
            </div>

        </div>

        <div class="mt-8">
            <button
                    class="group relative w-full flex justify-center py-3 px-4 border border-transparent text-base font-medium rounded-md text-gray-900 bg-green-500 hover:bg-lime-400 focus:outline-none ease-out duration-300"
                    type="submit"
            >
                Créer compte
            </button>
        </div>
        <p class="text-gray-400 text-sm mt-6 text-center">Déjà membre ? <a href="#"
                                                                class="font-bold text-green-600 tracking-wider hover:text-lime-300 hover:underline hover:underline-offset-2 ease-in-out duration-300">Se connecter</a></p>
    </form>
</div>
