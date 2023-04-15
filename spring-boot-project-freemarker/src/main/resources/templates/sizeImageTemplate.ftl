<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<table style="border: 1px solid black;text-align:center;border-collapse: collapse;">
    <caption>客户跟进情况个人</caption>
    <thead>
    <tr>
        <th colspan="2">The table header</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>The table body</td>
        <td>with two columns</td>
    </tr>
    </tbody>

    <#list contentMap?keys as key>
        <tr>
            <#list (contentMap[key]) as field>
                <td style="border:1px solid black;height:40px;width: 140px">${field}</td>
            </#list>
        </tr>
    </#list>
</table>
<#list desc?keys as key>
    <#list (desc[key]) as field>
        ${field}
    </#list>
</#list>
</body>
</html>