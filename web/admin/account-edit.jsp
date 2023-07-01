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
        <link href="../css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <script defer src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script defer src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
    </head>

    <body class="sb-nav-fixed">
        <?php include_once('includes/navbar.php'); ?>
        <div id="layoutSidenav">
            <?php include_once('includes/sidebar.php'); ?>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <c:set var="c" value="${requestScope.data}"/>

                    <h2 class="mt-4">Cập nhật thông tin cho ${c.tenDangNhap}</h2>
                 
                        <form method="get" action="tai-khoan">
                            <div class="card-body">
                                <input type="hidden" name="hanhDong" value="updateSubmit"/>
                                <table class="table table-bordered">
                                    
                                    <tr>
                                        <th>Mã Admin</th>
                                        <td><input readonly="" class="form-control" id="maAdmin" name="maAdmin" type="text" value="${c.maAdmin}" required /></td>
                                    </tr>
                                    <tr>
                                        <th>Tên đăng nhập</th>
                                        <td><input readonly class="form-control" id="tenDangNhap" name="tenDangNhap" type="text" value="${c.tenDangNhap}" required /></td>
                                    </tr>
                                    <tr>
                                        <th>Họ và tên</th>
                                        <td><input  class="form-control" id="hoVaTen" name="hoVaTen" type="text" value="${c.hoVaTen}" required /></td>
                                    </tr>
                                    <tr>
                                        <th>Email</th>
                                        <td><input  class="form-control" id="emailAdmin" name="emailAdmin" type="text" value="${c.emailAdmin}" required /></td>
                                    </tr>
                                    <tr>
                                        <th>Chức vụ</th>
                                        <td>
                                            <select class="form-select" aria-label="Default select example" name="chucVu"required>
                                                    <option value="Cán bộ tuyển sinh" ${c.chucVu == 'Cán bộ tuyển sinh' ? 'selected' : ''}>Cán bộ tuyển sinh</option>
                                                    <option value="Admin" ${c.chucVu == 'Admin' ? 'selected' : ''}>Admin</option>


                                                </select>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <td colspan="4" style="text-align:center ;"><button type="submit" class="btn btn-primary btn-block">Update</button></td>

                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                 
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