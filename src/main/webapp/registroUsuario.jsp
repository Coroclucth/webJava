<%@include file="lib/header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <form>
        <div class="form-group">
            <label>Id</label>
            <input type="text" class="form-control" name="id">
        </div>
   <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Correo electrónico</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Contraseña</label>
    <input type="password" class="form-control" id="exampleInputPassword1">
  </div>
  <button type="submit" class="btn btn-primary">Registrarse</button>
</form>
    <br> 
    <h3>Mensaje</h3>
    <div class="alert alert-secondary" role="alert">
        <%
            String identificacion= request.getParameter("id");
            String nombre = request.getParameter("name");
            try{
            int edad = Integer.parseInt(request.getParameter("age"));
            }catch (Exception e){
            System.out.println(e);
            }
        String tipoUsuario=request.getParameter("user_type");
        String saludo="Su nombre es "+nombre+" su rol es "+tipoUsuario;
              out.print(saludo);
        %>
    </div>
</div>
        <%@include file="lib/footer.jsp" %>