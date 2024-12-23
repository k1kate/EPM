<#macro header member, weekData>
    <body>
    <#list weekData.groupTimeTable.timeTables as timeTable>
        <#if timeTable.type == member>
            <div class="schedule-container">
                <table class="schedual-table">
                    <thead class="table-head-first">
                    <tr>
                        <th scope="col">${timeTable.type}</th>
                    </tr>
                    </thead>
                    <tbody class="table-body">
                    <#list timeTable.weeks as week>
                        <#if week.day??>

                            <th style="padding-left: 15px;
                                 background-color: #fff2e9;" scope="col">${week.day}</th>


                            <#list week.groupDiscipline as groupDiscipline>
                                <#list groupDiscipline.discipline as discipline>
                                    <#if discipline.disc?? && discipline.disc?trim?has_content>
                                        <tr>
                                            <td class="table-card">
                                                <span style="display: block;">${groupDiscipline.number! ""}</span>
                                                <span style="display: block; font-weight: normal">${discipline.timePair! ""}</span>
                                                <span style="display: block;">${discipline.aud! ""}</span>
                                            </td>
                                            <td>
                                                ${discipline.teacher! ""}
                                                <p class="custom-p">${discipline.groupName! ""}</p>
                                            </td>
                                            <td>

                                                <span style="display: block;">${discipline.disc! ""}</span>
                                                <span style="display: block;">${discipline.group_name! ""}</span>
                                            </td>
                                        </tr>
                                    </#if>
                                </#list>
                            </#list>
                        </#if>
                    </#list>
                    </tbody>
                </table>
            </div>
        </#if>
    </#list>
    </body>
</#macro>