<%@page import="skyforge.sirass.dao.prestador.InscripcionDAO"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String aviso;
    if (request.getParameter("aviso") != null) {
        aviso = request.getParameter("aviso");
        if (aviso.equals("0")) {%>
            <p class="lead">Parece que a&uacute;n no est&aacute;s inscrito en ning&uacute;n 
            <strong>programa de Servicio Social</strong>. Puedes hacerlo pulsando la 
            opci&oacute;n de <em>Inscripci&oacute;n</em> en la barra lateral de 
            navegaci&oacute;n o pulsando el siguiente bot&oacute;n.</p>
            <a href="/SiRASS/prestador/inscripcion.jsp" class="btn btn-primary btn-large">
                Inscripci&oacute;n al Servicio Social
            </a>
<%      } else if (aviso.equals("1")) {%>
            <p class="lead">Tu solicitud de inscripci&oacute;n est&aacute; siendo validada por un responsable de Servicio Social. <a href="#"><strong>Ver estado</strong></a></p>
<%      } else if (aviso.equals("2")) {%>
            <p>Actualmente est&aacute;s inscrito en el siguiente programa de Servicio Social:</p>
            <p class="lead"><strong>Apoyo al &aacute;rea acad&eacute;mico-administrativa en la UACM</strong></p>
            <h3>Horas acumuladas</h3>
<%          // Calcular horas
            int horas = 0;
            double width = 0;
            if (session.getAttribute("inscripcion") != null) {
                Inscripcion inscr = (Inscripcion) session.getAttribute("inscripcion");
                if (inscr.getHorasRealizadas() != null && inscr.getHorasRealizadas() != 0) {
                    horas = inscr.getHorasRealizadas();
                    width = (horas * 5) / 24;
                }
            }
%>
            <div class="progress progress-success" style="height: 30px; border: 1px solid #8c8c8c;">
                <div class="bar" style="width: <%= width + "%" %>; height: 30px;"></div>
            </div>
            <h6><%= horas %>/480 - <%= width + "%"%></h8>
<%      } else if (aviso.equals("3")) {%>
            <p class="lead">Tu inscripci&oacute;n ha sido suspendida. Ll&aacute;ma a las oficinas
                de Servicio Social para obtener m&aacute;s informaci&oacute;n.</p>
<%      } else if (aviso.equals("4")) {%>
            <p class="lead">Est&aacute;s dado de baja del programa de Servicio Social. Ll&aacute;ma a las oficinas
                de Servicio Social para obtener m&aacute;s informaci&oacute;n.</p>
<%      } else if (aviso.equals("5")) {%>
            <p class="lead"><b>Felicidades!</b> Haz completado las horas necesarias para liberar tu Servicio Social.</p>
<%      } else if (aviso.equals("6")) {%>
            <p class="lead">Lo sentimos, se encontraron errores en tu inscripci&oacute;n.</p>
            <%
                InscripcionDAO idao = new InscripcionDAO();
                Inscripcion inscr = (Inscripcion) session.getAttribute("inscripcion");
                String observaciones = idao.getObservaciones(inscr.getIdInscripcion());
                System.out.println(observaciones);
                if (observaciones != null) {%>
            <p class="lead">La persona que revis&oacute; tu inscripci&oacute;n anot&oacute; las 
            siguientes observaciones: </p>
            <p class="well"><%= observaciones%></p>
            <%} else {
                    out.println("<p class=\"lead\">Por favor llama a las oficinas de Servicio Social.</p>");
            }%>
<%      } else if (aviso.equals("7")) {%>
            <p class="lead">Parece que tu inscripci&oacute;n no tiene errores. Ahora genera tu 
                <em><b>Carta Compromiso</b></em> y <em><b>Solicitud de Servicio</b></em> y entr&eacute;galos junto con los 
                respectivos papeles en las oficinas de Servicio Social.</p>
            <a href="#" class="btn btn-inverse btn-small">Generar carta compromiso</a>
            <a href="#" class="btn btn-inverse btn-small">Generar Solicitud de Servicio</a>
<%      } else if (aviso.equals("algo")) {%>
            <p>algo</p>
<%      } else {%>
            <p>Tu inscripci&oacute;n est&aacute; pendiente.</p>
<%      }
    }
%>