<%--<div>--%>
<%--    <div class="w-[66rem] bg-gray-100 flex items-center justify-center bg-gray-100 font-sans">--%>
<%--        <div class="w-full p-2">--%>
<%--            <div class="bg-white shadow-md my-6 rounded-lg">--%>
<%--                <table class="min-w-max w-full table-auto rounded-lg">--%>
<%--                    <thead>--%>
<%--                    <tr class="bg-gray-500 text-gray-600 uppercase text-sm leading-normal rounded">--%>
<%--                        <th class="py-3 px-6 text-left">ID</th>--%>
<%--                        <th class="py-3 px-6 text-left">VALEUR</th>--%>
<%--                        <th class="py-3 px-6 text-center">EDITION</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody class="text-gray-600 text-sm font-light">--%>

<%--                    <tr class="border-b border-gray-200 hover:bg-gray-100">--%>

<%--                        <td class="py-3 px-6 text-left whitespace-nowrap">--%>
<%--                        </td>--%>

<%--                        <td class="py-3 px-6 text-left">--%>
<%--                        </td>--%>

<%--&lt;%&ndash;                    ----------------- Colonne édition -------------------&ndash;%&gt;--%>
<%--                        <td class="py-3 px-6 text-center">--%>
<%--                            <div class="flex item-center justify-center gap-2">--%>
<%--                                <div class="w-4 transform hover:text-purple-500 hover:scale-110">--%>
<%--                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">--%>
<%--                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />--%>
<%--                                    </svg>--%>
<%--                                </div>--%>
<%--                                <div class="w-4 transform hover:text-purple-500 hover:scale-110">--%>
<%--                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">--%>
<%--                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />--%>
<%--                                    </svg>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </td>--%>

<%--                    </tr>--%>

<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<jsp:include page="../Modules/tableau.jsp">
    <jsp:param name="module" value="COMPTES" />
    <jsp:param name="info" value="true" />
</jsp:include>
<jsp:include page="../Modules/tableau.jsp">
    <jsp:param name="module" value="LIBRAIRES" />
</jsp:include>