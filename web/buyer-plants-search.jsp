<%@page import="java.util.List"%>
<%@page import="com.online.plant.models.Plant"%>
<%@page import="com.online.plant.client.call.PlantsClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String search = request.getParameter("search");
    List<Plant> plants = new PlantsClient().getPlants(search);
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/imports.jsp"%>
    </head>
    <body>
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <%@include file="includes/header.jsp" %>

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Transaction Plans</h1>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 mb-4">

                                <!-- Illustrations -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Plants Search</h6>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-6">
                                            <form action="buyer-plants-search.jsp"
                                                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                                                <div class="input-group w-100">
                                                    <input name="search" type="text" value="<%=search%>" class="form-control bg-light border-0" placeholder="Search for..."
                                                           aria-label="Search" aria-describedby="basic-addon2">
                                                    <div class="input-group-append">
                                                        <button class="btn btn-primary" type="submit">
                                                            <i class="fas fa-search fa-sm"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <br/>

                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Name</th>
                                                    <th>Type</th>
                                                    <th>Age</th>
                                                    <th>Price</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%for (Plant plant : plants) {%>
                                                <tr>
                                                    <td><%=plant.getId()%></td>
                                                    <td><%=plant.getName()%></td>
                                                    <td><%=plant.getType()%></td>
                                                    <td><%=plant.getAge()%></td>
                                                    <td><%=plant.getPrice()%></td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                    <hr>
                                    Click here to <a href="home">Back &rarr;</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End of Main Content -->

                <%@include file="includes/footer.jsp" %>

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <%@include file="includes/tail.jsp" %>
    </body>
</html>
