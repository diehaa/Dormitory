<%-- 
    Document   : account
    Created on : 1 thg 7, 2023, 09:38:12
    Author     : phangiabao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard | Dormitory Management </title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script defer src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
    </head>

    <body class="sb-nav-fixed">
        <%@include file="includes/navbar.jsp" %>
        <div id="layoutSidenav">
            <%@include file="includes/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <button type="button" class="btn btn-primary mb-4" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            Tạo tài khoản CBNV
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Tạo tài khoản CBNV</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="admin" method="post">
                                            <input type="hidden" name="action" value="add"/>


                                            <!-- CCCD input -->
                                            <div class="form-floating mb-4">
                                                <input type="text" id="tenDangNhap" class="form-control" name="tenDangNhap" required="" value="<%=tenDangNhap%>"/>
                                                <label class="form-label" for="tenDangNhap">Tên đăng nhập</label>
                                            </div>

                                            <!-- Password input -->
                                            <div class="form-floating mb-4">
                                                <input type="password" id="matKhau" class="form-control" name="matKhau" required=""/>
                                                <label class="form-label" for="matKhau">Mật khẩu</label>
                                            </div>
                                            <div class="form-floating mb-4">
                                                <input type="password" id="matKhauNhapLai" class="form-control" name="matKhauNhapLai" required=""onkeyup="kiemTraMatKhau()"/>
                                                <label class="form-label" for="matKhauNhapLai">Nhập lại Mật khẩu</label>
                                                <span id ="thongBaoLoiMatKhau" style="color: red"></span>
                                            </div>
                                            <!--                                    Infomation-->
                                            <div class="form-floating mb-4">
                                                <input type="text" id="hoVaTen" class="form-control" name="hoVaTen" required="" value="<%=hoVaTen%>"/>
                                                <label class="form-label" for="hoVaTen">Họ và tên</label>
                                            </div>
                                            <div class="form-floating mb-4">
                                                <input type="email" id="emailAdmin" class="form-control" name="emailAdmin" required="" value="<%=emailAdmin%>"/>
                                                <label class="form-label" for="emailAdmin">Email</label>
                                            </div>
                                            <div class="form-floating mb-4">
                                                <select class="form-select" aria-label="Default select example" name="chucVu"required>
                                                    <option value="Cán bộ tuyển sinh" <% if (chucVu.equals("Cán bộ tuyển sinh")) {
                                                            out.print("selected");
                                                        } %> >Cán bộ tuyển sinh</option>
                                                    <option value="Admin" <% if (chucVu.equals("Admin")) {
                                                            out.print("selected");
                                                        }%>>Admin</option>


                                                </select>
                                                <label class="form-label" for="chucVu">Chức vụ</label>
                                            </div>

                                            <div class="alert alert-danger" role="alert">
                                                <%=baoLoi%>
                                            </div>
                                            <!-- Submit button -->
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Tạo tài khoản</button>
                                            </div>




                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- Tại tài khoản -->
                        <%=baoLoi%>


                        <table id="example" class="table table-striped table-bordered align-middle table-responsive" style="width:100%">
                            <thead class="table font-chu-nho text-center align-middle " style="background-color: #f27124; color: white" >

                            <th>Code</th>
                            <th>Username</th>
                            <th>FullName</th>
                            <th>Email</th>
                            <th>Role</th>

                            <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items = "${requestScope.data}" var="c">
                                <tr class="font-chu-nho">

                                    <td>${c.maAdmin}</td>
                                    <td><a href="tai-khoan?hanhDong=update&maAdmin=${c.maAdmin}">${c.tenDangNhap}</a></td>
                                    <td>${c.hoVaTen}</td>
                                    <td>${c.emailAdmin}</td>
                                    <td>${c.chucVu}</td>


                                    <td>
                                        <a class="btn btn-primary" href="#" onclick="doReset('${c.maAdmin}', '${c.tenDangNhap}', '${c.emailAdmin}')" role="button">Reset password</a>
                                        <a class="btn btn-danger" href="#" onclick="doDelete('${c.maAdmin}', '${c.tenDangNhap}')" role="button">Delete</a>
                                    </td>

                                </tr>
                            </c:forEach>



                            </tbody>

                        </table>
                    </div>
                </main>
                <!-- foooter -->
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script>
                                                         function doDelete(maAdmin, tenDangNhap) {
                                                             if (confirm("Bạn có muốn xoá " + tenDangNhap + " không?")) {
                                                                 window.location = "tai-khoan?hanhDong=delete&maAdmin=" + maAdmin;
                                                             }
                                                         }
                                                         $(document).ready(function () {

                                                             $('#example').DataTable({
                                                                 search: {
                                                                     return: false,
                                                                 },
                                                                 lengthMenu: [
                                                                     [10, 25, 50, -1],
                                                                     [10, 25, 50, 'All'],
                                                                 ],
                                                                 order: [[3, 'des']],
                                                             });

                                                         });
        </script>
    </body>

</html>