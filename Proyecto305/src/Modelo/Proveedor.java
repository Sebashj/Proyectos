package Modelo;

public class Proveedor {
	
	int idproveedor, cantacto;
	String nombreproveedor, descripcion;
	
	public Proveedor() {
		
	}
	public Proveedor(int idproveedor, int cantacto, String nombreproveedor, String descripcion) {
		super();
		this.idproveedor = idproveedor;
		this.cantacto = cantacto;
		this.nombreproveedor = nombreproveedor;
		this.descripcion = descripcion;
	}
	public int getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}
	public int getCantacto() {
		return cantacto;
	}
	public void setCantacto(int cantacto) {
		this.cantacto = cantacto;
	}
	public String getNombreproveedor() {
		return nombreproveedor;
	}
	public void setNombreproveedor(String nombreproveedor) {
		this.nombreproveedor = nombreproveedor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
