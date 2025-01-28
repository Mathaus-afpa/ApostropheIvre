<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="compte" items="${comptes}">

    <tr class="border-b border-neutral-500 bg-gray-800 border-gray-200 hover:bg-gray-900">

        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
            ${compte.id}
        </th>

        <td class="px-6 py-4">
            ${compte.toString()}
        </td>

        <td class="px-6 py-4 flex justify-center gap-3">
            <a href="#" class="font-normal text-blue-600 tracking-wide hover:underline underline-offset-2">Edition</a>
            <a href="#" class="font-normal text-red-600 tracking-wide hover:underline underline-offset-2">Supprimer</a>
        </td>

    </tr>

</c:forEach>


