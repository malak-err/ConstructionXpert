<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2/20/2025
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Enaa</title>
    <head>
        <title>Enaa</title>
        <link  rel="stylesheet"
               href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
               integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
               crossorigin="anonymous">

    </head>


    <br>

    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:if test="${tache != null}">
                <form action="taches/edit" method="post">
                    </c:if>
                    <c:if test="${tache == null}">
                    <form action="taches/new" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${tache != null}">
                                    Edit tache
                                </c:if>
                                <c:if test="${tache  == null}">
                                    Add New tache
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${tache  != null}">
                            <input type="hidden" name="id" value="<c:out value='${tache .id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Name</label>
                            <input type="text"
                                   value="<c:out value='${tache .nom}' />" class="form-control"
                                   name="nom" required="required">
                        </fieldset>


                        <fieldset class="form-group">
                            <label>date de début</label>
                            <input type="text"
                                   value="<c:out value='${tache.startdate}' />" class="form-control"
                                   name="startdate" required>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>date de fin</label>
                            <input type="text"
                                   value="<c:out value='${tache.enddate}' />" class="form-control"
                                   name="enddate" required>
                        </fieldset>
                        <button type="submit" class="btn btn-success" style="background-color: #001c53">Save</button>
                    </form>
            </div>
        </div>
    </div>
</head>
<body>

</body>
</html>
