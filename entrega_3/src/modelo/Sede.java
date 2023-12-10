package modelo;

public class Sede {

    private String nombre;
    private String ubicacion;
    private String horario;  // Representa ambos, inicio y fin del horario en un solo string

    public Sede(String nombre, String ubicacion, String horario) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horario = horario;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public String generarTexto(){
    	String texto="";
    	texto+= getNombre()+"{";
    	texto+= getUbicacion()+"{";
    	texto+= getHorario();
    	
    	return texto;
    	
    }
}

