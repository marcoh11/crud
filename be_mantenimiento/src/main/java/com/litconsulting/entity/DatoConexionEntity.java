package com.litconsulting.entity;

import java.sql.Connection;

public class DatoConexionEntity {
	private Connection oConexion;  
	private String sControlador;     
    private String sUsuario;      
    private String sContracena;      
    private String sUrl;
	public DatoConexionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Connection getoConexion() {
		return oConexion;
	}
	public void setoConexion(Connection oConexion) {
		this.oConexion = oConexion;
	}
	public String getsControlador() {
		return sControlador;
	}
	public void setsControlador(String sControlador) {
		this.sControlador = sControlador;
	}
	public String getsUsuario() {
		return sUsuario;
	}
	public void setsUsuario(String sUsuario) {
		this.sUsuario = sUsuario;
	}
	public String getsContracena() {
		return sContracena;
	}
	public void setsContracena(String sContracena) {
		this.sContracena = sContracena;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}     
    
  

}
