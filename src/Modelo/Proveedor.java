
package Modelo;

/**
 *
 * @author rodri
 */
public class Proveedor {
    private int id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String razon_social;
    
    public Proveedor(){
        
    }

    public Proveedor(int id, String nombre, String telefono, String direccion, String razon_social) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon_social = razon_social;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }
    
}
