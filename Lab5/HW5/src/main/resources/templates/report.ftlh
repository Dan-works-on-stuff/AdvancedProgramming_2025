<!DOCTYPE html>
<html>
<head>
    <title>Image Repository Report</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; }
        tr:nth-child(even) { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h1>Image Repository Report</h1>
<p>Generated on: ${.now?datetime}</p>
<p>Total images: ${images?size}</p>

<table>
    <tr>
        <th>Name</th>
        <th>Date</th>
        <th>Tags</th>
        <th>Location</th>
    </tr>
    <#list images as image>
        <tr>
            <td>${image.name}</td>
            <td>${image.date}</td>
            <td>${image.tags?join(", ")}</td>
            <td>${image.location}</td>
        </tr>
    </#list>
</table>
</body>
</html>