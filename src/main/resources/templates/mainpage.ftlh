<#import "blocks/template.ftlh" as t>
<#import "timetable.ftlh" as tt>
<@t.template avatarUrl="/user/avatar?userId=${user.id}">
    <div class="container">
        <div class="row align-items-start">
            <div class="col">
                <@tt.header member="ЧИСЛИТЕЛЬ" weekData=weekData />
            </div>
            <div class="col">
                <@tt.header member="ЗНАМЕНАТЕЛЬ" weekData=weekData />
            </div>
        </div>
    </div>
</@t.template>