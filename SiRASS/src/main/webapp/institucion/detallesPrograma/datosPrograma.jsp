<div class="span11">
    <div class="well">               
        <h3>
            Información Programa:
        </h3>
        <br>
        <div class="row">
            <div class="span3 pull-left">
                <h4>
                    Responsables - Cargo
                </h4>
            </div>
            <div class="span7">
                <%
                while(it3.hasNext()){
                        ResponsablePrograma resp = (ResponsablePrograma)it3.next();
                %>
                <h5>
                    <%=resp.getResponsable()%> - <%=resp.getCargo()%>
                </h5>
                <br>
                <%}%>
            </div>
        </div>
        <div class="row">
            <div class="span4 pull-left">
                <h4>
                    Actividades - Licenciatura - Estud. Solocit.
                </h4>
            </div>
            <div class="span6">
                <table border="0">
                    <%
                    while(it.hasNext()){
                            ActividadPrograma actv = (ActividadPrograma)it.next();
                            String actividadgeneral = "";
                            String actividad1="";
                            String actividad2="";
                            String actividad3="";
                            String actividad="";
                            int contador=0;
                            actividadgeneral = actv.getActividad();
                            for(int i = 0;i<=actividadgeneral.length();i++){
                                if(actividadgeneral.charAt(i)=='-'){
                                    contador++;
                                    if(contador==2){
                                        actividad1=actividad;
                                        actividad="";
                                    }
                                    if(contador==3){
                                        actividad2=actividad;
                                        actividad="";
                                    }
                                    if(contador==4){
                                        actividad3=actividad;
                                        actividad="";
                                        break;
                                    }
                                }
                                actividad+=actividadgeneral.charAt(i);
                            }

                    %>
                    <tr>
                        <td>
                            <%=actividad1%>
                            <br>
                            <%=actividad2%>
                            <br>
                            <%=actividad3%>
                            <br>
                            <br>
                        </td>
                        <td><%=actv.getLicenciatura()%></td>
                        <td><%=actv.getnSolicitados()%></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>           
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Horario
                </h4>
            </div>
            <div class="span8">
                <h5>
                    <%=programaSS.getHorario().getDescripcion() %>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span4 pull-left">
                <h4>
                    Plazas - Vacantes - Ocupadas
                </h4>
            </div>
            <div class="span6">
                <h5>
                    <%=programaSS.getPlazas()%> - <%=programaSS.getVacantes()%> - <%=programaSS.getOcupadas()%>
                </h5>
            </div>
        </div>     
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Tipo:
                </h4>
            </div>
            <div class="span8">
                <%
                while(it4.hasNext()){
                        TipoPrograma tip = (TipoPrograma)it4.next();
                %>
                <h5>
                    <%=tip.getDescripcion()%>
                </h5>
                <%}%>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span1 pull-left">
                <h4>
                    Lugar
                </h4>
            </div>
            <div class="span9">
                <h5 align="center">
                    <%=programaSS.getLugar()%>
                </h5>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="span2 pull-left">
                <h4>
                    Poblaciones
                </h4>
            </div>
            <div class="span8">
                <%
                while(it2.hasNext()){
                        PoblacionPrograma pobl = (PoblacionPrograma)it2.next();
                %>
                <h5>
                    <%=pobl.getDescripcion()%>
                </h5>
                <%}%>
            </div>
        </div>
    </div>
</div>