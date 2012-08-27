<%-- 
    Document   : index
    Created on : 26-ago-2012, 11:55:01
    Author     : gomezhyuuga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>

    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="Test" method="get" name="myform">
            <input name="fecha[]" type="text" value="20/02/2012" />
            <input name="fecha[]" type="text" value="21/02/2012" />
            <input name="fecha[]" type="text" value="22/02/2012" />
            <input name="fecha[]" type="text" value="23/02/2012" />
            <input name="horaEntrada[]" type="text" value="10:00" />
            <input name="horaEntrada[]" type="text" value="11:00" />
            <input name="horaEntrada[]" type="text" value="12:00" />
            <input name="salida" type="text" value="10:00" />
            <input name="salida" type="text" value="11:00" />
            <input name="salida" type="text" value="12:00" />
            <input type="submit" value="ENVIAR" />
            <p>
		<script type="text/javascript">
			function registroHora(fecha, horaEntrada, horaSalida, horasDia, minutosDia) {
				this.fecha = fecha;
				this.horaEntrada = horaEntrada;
				this.horaSalida = horaSalida;
				this.horasDia = horasDia;
				this.minutosDia = minutosDia;
			}
			var registro1 = new registroHora(new Date("10/02/2012"), "20:00", "24:00");
			var registro2 = new registroHora(new Date("11/02/2012"), "18:00", "20:00");
			var registro3 = new registroHora(new Date("12/02/2012"), "16:00", "22:00");
			console.log(registro1);
			var registros = new Array();
			registros.push(registro1);
			registros.push(registro2);
			registros.push(registro3);
			console.log(registros);
			console.log(JSON.stringify(registros));
			$.ajax({
			  url: "/SiRASS/Test",
			  data: JSON.stringify(registros),
			  processData: false,
			  dataType: "json",
			  success:function(a) { console.log("SUCCESS " + a )},
			  error:function() {}
			});
		</script>
		</p>
        </form>
    </body>
</html>
