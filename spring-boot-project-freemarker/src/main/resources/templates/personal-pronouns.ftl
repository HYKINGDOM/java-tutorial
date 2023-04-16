<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<table style="border: 1px solid black;text-align:center;border-collapse: collapse;">

    <caption>
        <b style="font-size:20px;text-align:center">客户跟进情况个人</b>
    </caption>

    <caption style="text-align:right">
        <#list dateTime?keys as key>
            <#list (dateTime[key]) as field>
                <small>${field}</small>
            </#list>
        </#list>
    </caption>

    <tr style="background: #b7bbbb">
        <th rowspan="2" style="border:1px solid black;height:40px;width: 80px">部门名称</th>
        <th rowspan="2" style="border:1px solid black;height:40px;width: 80px">员工名称</th>

        <th colspan="3" style="border:1px solid black;height:40px;width: 180px">单日跟进情况(次数)</th>

        <th colspan="2" style="border:1px solid black;height:40px;width: 180px">月累计首次沟通客户数</th>

        <th rowspan="2" style="border:1px solid black;height:40px;width: 160px">月累计跟进老客户数</th>

        <th colspan="3" style="border:1px solid black;height:40px;width: 120px">月跟进情况(次数)</th>

        <th colspan="4" style="border:1px solid black;height:40px;width: 330px">月累计待开发跟进客户数</th>
    </tr>


    <tr style="background: #b7bbbb">
        <th style="border:1px solid black;height:40px;width: 80px">电话/微信</th>
        <th style="border:1px solid black;height:40px;width: 50px">拜访</th>
        <th style="border:1px solid black;height:40px;width: 50px">总计</th>

        <th style="border:1px solid black;height:40px;width: 50px">40%</th>
        <th style="border:1px solid black;height:40px;width: 50px">60%</th>

        <th style="border:1px solid black;height:40px;width: 80px">电话/微信</th>
        <th style="border:1px solid black;height:40px;width: 50px">拜访</th>
        <th style="border:1px solid black;height:40px;width: 50px">总计</th>

        <th style="border:1px solid black;height:40px;width: 90px">0%、20%</th>
        <th style="border:1px solid black;height:40px;width: 90px">40%、60%</th>
        <th style="border:1px solid black;height:40px;width: 100px">80%、100%</th>
        <th style="border:1px solid black;height:40px;width: 50px">总计</th>

    </tr>


    <#list contentMap?keys as key>

        <#if key?starts_with("合计")>
            <tr>
                <#list (contentMap[key]) as field>
                    <td style="background: #b7bbbb;border:1px solid black;height:40px;width: 40px">${field}</td>
                </#list>
            </tr>
        <#elseif key?starts_with("总计")>
            <tr>
                <#list (contentMap[key]) as field>
                    <td style="background: #b7bbbb;border:1px solid black;height:40px;width: 40px">${field}</td>
                </#list>
            </tr>
        <#else>
            <tr>
                <#list (contentMap[key]) as field>
                    <td style="border:1px solid black;height:40px;width: 40px">${field}</td>
                </#list>
            </tr>

        </#if>

    </#list>

</table>

</body>
</html>