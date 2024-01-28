package com.empleados.gestionempleados.models;

import lombok.Data;

@Data
public class ExceptionModel {

    private String mensaje;

    public ExceptionModel(String mensaje){this.mensaje = mensaje;}
}
