<%@page import="com.online.plant.client.call.PlantsClient"%>
<%@page import="com.online.plant.models.Plant"%>
<%@page import="java.util.List"%>
<%@page import="com.online.plant.models.TransactionDetails"%>
<%@page import="com.online.plant.client.call.TransactionsClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    PlantsClient client = new PlantsClient();
    int id = Integer.parseInt(request.getParameter("id"));
    Plant selectedPlant = new Plant();
    int plantId = -1;
    try {
        plantId = Integer.parseInt(request.getParameter("plantId"));
        selectedPlant = client.getPlant(id);
    } catch (Exception ex) {
    }
    List<Plant> plants = client.getPlants();
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
                            <h1 class="h3 mb-0 text-gray-800">Transaction Plant Add</h1>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 mb-4">

                                <!-- Illustrations -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Transaction No: <%=id%></h6>
                                    </div>
                                    <div class="card-body">
                                        <%if (plantId > 0) {%> <form action="transactions/plant/add" method="post">
                                            <input type="hidden" name="id" value="<%=id%>"/>
                                            <input type="hidden" name="plantId" value="<%=plantId%>"/>
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" readonly class="form-control" value="<%=selectedPlant.getName()%>">
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-4">
                                                    <label>Type</label>
                                                    <input type="text" readonly class="form-control" value="<%=selectedPlant.getType()%>">
                                                </div>
                                                <div class="form-group col-4">
                                                    <label>Age</label>
                                                    <input type="text" readonly class="form-control" value="<%=selectedPlant.getAge()%>">
                                                </div>
                                                <div class="form-group col-4">
                                                    <label>Price</label>
                                                    <input type="text" readonly class="form-control" value="<%=selectedPlant.getPrice()%>">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-6">
                                                    <label>Quantity</label>
                                                    <input type="number" name="quantity" required="" class="form-control" value="0">
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-success">Add Plant</button>
                                        </form>
                                        <%} else {%>
                                        <h6 class="m-0 font-weight-bold text-danger">Please Select Plant Firstly!</h6>
                                        <%}%>
                                        <hr>
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100&percnt;" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Name</th>
                                                        <th>Type</th>
                                                        <th>Age</th>
                                                        <th>Price</th>
                                                        <th>Actions</th>
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
                                                        <td><a class="btn btn-info" href="seller-transaction-plant-add.jsp?id=<%=id%>&plantId=<%=plant.getId()%>">Add Plant</a></td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                        Click here to <a href="seller-transaction-details.jsp?id=<%=id%>">Back &rarr;</a>
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
