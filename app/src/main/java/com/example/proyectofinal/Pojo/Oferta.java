package com.example.proyectofinal.Pojo;

public class Oferta {
    private String nombre, id, empresa_nombre, imagen, id_empresa, profesion, puesto, correo, telefono, giro, direccion, calificacion, transporte, comisiones, bonos
            ,otro1, otro2, otro3, sueldo, edad, estatura, nacionalidad, estado_civil, segundo_idioma, tercer_idioma, nivel_estudios, discapacidad;

    float porcentaje;
    public Oferta(String nombre, String id, String empresa_nombre, String imagen, String id_empresa, String profesion, String puesto, String correo, String telefono, String giro, String direccion, String calificacion, String transporte, String comisiones, String bonos, String otro1, String otro2, String otro3, String sueldo, String edad, String estatura, String nacionalidad, String estado_civil, String segundo_idioma, String tercer_idioma, String nivel_estudios, String discapacidad, float porcentaje) {
        this.nombre = nombre;
        this.id = id;
        this.empresa_nombre = empresa_nombre;
        this.imagen = imagen;
        this.id_empresa = id_empresa;
        this.profesion = profesion;
        this.puesto = puesto;
        this.correo = correo;
        this.telefono = telefono;
        this.giro = giro;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.transporte = transporte;
        this.comisiones = comisiones;
        this.bonos = bonos;
        this.otro1 = otro1;
        this.otro2 = otro2;
        this.otro3 = otro3;
        this.sueldo = sueldo;
        this.edad = edad;
        this.estatura = estatura;
        this.nacionalidad = nacionalidad;
        this.estado_civil = estado_civil;
        this.segundo_idioma = segundo_idioma;
        this.tercer_idioma = tercer_idioma;
        this.nivel_estudios = nivel_estudios;
        this.discapacidad = discapacidad;
        this.porcentaje = porcentaje;
    }

    public Oferta() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpresa_nombre() {
        return empresa_nombre;
    }

    public void setEmpresa_nombre(String empresa_nombre) {
        this.empresa_nombre = empresa_nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getComisiones() {
        return comisiones;
    }

    public void setComisiones(String comisiones) {
        this.comisiones = comisiones;
    }

    public String getBonos() {
        return bonos;
    }

    public void setBonos(String bonos) {
        this.bonos = bonos;
    }

    public String getOtro1() {
        return otro1;
    }

    public void setOtro1(String otro1) {
        this.otro1 = otro1;
    }

    public String getOtro2() {
        return otro2;
    }

    public void setOtro2(String otro2) {
        this.otro2 = otro2;
    }

    public String getOtro3() {
        return otro3;
    }

    public void setOtro3(String otro3) {
        this.otro3 = otro3;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getSegundo_idioma() {
        return segundo_idioma;
    }

    public void setSegundo_idioma(String segundo_idioma) {
        this.segundo_idioma = segundo_idioma;
    }

    public String getTercer_idioma() {
        return tercer_idioma;
    }

    public void setTercer_idioma(String tercer_idioma) {
        this.tercer_idioma = tercer_idioma;
    }

    public String getNivel_estudios() {
        return nivel_estudios;
    }

    public void setNivel_estudios(String nivel_estudios) {
        this.nivel_estudios = nivel_estudios;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }
}
