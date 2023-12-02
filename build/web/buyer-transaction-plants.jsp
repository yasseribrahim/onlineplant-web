<%@page import="com.online.plant.models.TransactionDetails"%>
<%@page import="com.online.plant.models.Plant"%>
<%@page import="com.online.plant.client.call.TransactionsClient"%>
<%@page import="com.online.plant.models.Transaction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Transaction transaction = new TransactionsClient().getTransactionDetails(id);
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
                                        <h6 class="m-0 font-weight-bold text-primary">Transaction No: <%=transaction.getId()%></h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label>Buyer</label>
                                            <input type="text" readonly class="form-control" value="<%=transaction.getBuyerId()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Seller By</label>
                                            <input type="text" readonly class="form-control" value="<%=transaction.getSellerId()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Date</label>
                                            <input type="text" readonly class="form-control" value="<%=transaction.getDate()%>">
                                        </div>
                                        <div class="form-group">
                                            <label>Total</label>
                                            <input type="text" readonly class="form-control" value="<%=transaction.getTotal()%>">
                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100&percnt;" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Id</th>
                                                        <th>Name</th>
                                                        <th>Type</th>
                                                        <th>Age</th>
                                                        <th>Price</th>
                                                        <th>Quantity</th>
                                                        <th>Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%for (TransactionDetails details : transaction.getDetailses()) {%>
                                                    <tr>
                                                        <td><%=details.getId()%></td>
                                                        <td><%=details.getName()%></td>
                                                        <td><%=details.getType()%></td>
                                                        <td><%=details.getAge()%></td>
                                                        <td><%=details.getPrice()%></td>
                                                        <td><%=details.getQuantity()%></td>
                                                        <td><%=(details.getQuantity() * details.getPrice())%></td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                        <hr>
                                        Click here to <a href="buyer-transaction-details.jsp?id=<%=transaction.getId()%>">Back &rarr;</a>
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
