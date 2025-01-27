package capanegocio;

public class Empleado extends Persona {
    private CargoEmpleado cargo;
    private boolean disponibilidad;

    public Empleado(String cedula, String nombre, String apellido, CargoEmpleado cargo) {
        super(cedula, nombre, apellido);
        this.cargo = cargo;
        this.disponibilidad = true;
    }

    public CargoEmpleado getCargo() {
        return cargo;
    }

    public void setCargo(CargoEmpleado cargo) {
        this.cargo = cargo;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

}
