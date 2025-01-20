package capanegocio;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona {
    private CargoEmpleado cargo;
    private boolean disponibilidad;
    private List<AsignacionTarea> asignaciones;

    public Empleado(String cedula, String nombre, String apellido, CargoEmpleado cargo) {
        super(cedula, nombre, apellido);
        this.cargo = cargo;
        this.disponibilidad = true;
        this.asignaciones = new ArrayList<>();
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

    public List<AsignacionTarea> getAsignaciones() {
        return asignaciones;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setAsignaciones(List<AsignacionTarea> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public void agregarAsignacione(EstadoAsignacion estadoAsignacion, String asignacion) {
        asignaciones.add(new AsignacionTarea(estadoAsignacion,asignacion));
    }

    public void eliminarAsignacion(AsignacionTarea asignacion) {
        asignaciones.remove(asignacion);
    }

}
