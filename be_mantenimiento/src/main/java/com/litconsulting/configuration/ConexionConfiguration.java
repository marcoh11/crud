package com.litconsulting.configuration;


import java.sql.DriverManager;

import java.sql.SQLException;


import org.springframework.core.env.Environment;

import com.litconsulting.entity.DatoConexionEntity;
import com.litconsulting.exception.GeneralException;


public class ConexionConfiguration {
	
	public DatoConexionEntity oConexion = new DatoConexionEntity();
	
	public ConexionConfiguration(Environment oPropiedad) {
		  
		  try {
            				
			  oConexion.setsControlador(oPropiedad.getProperty("spring.datasource.driver-class-name") );
			  oConexion.setsUsuario(oPropiedad.getProperty("spring.datasource.username"));
			  oConexion.setsContracena(oPropiedad.getProperty("spring.datasource.password"));		
			  oConexion.setsUrl(oPropiedad.getProperty("spring.datasource.url"));
			
		   } catch (GeneralException oError) {
	            throw new GeneralException("(ConexionConfiguration) Error de Carga Inicial: " + oError.getMessage(),oError);
	 
	        }
	         

				
	}
	
	public boolean conectar() {
        boolean boEstado = false;
        try {
                  
            Class.forName(oConexion.getsControlador());           
            
            oConexion.setoConexion(DriverManager.getConnection(oConexion.getsUrl(), oConexion.getsUsuario(),oConexion.getsContracena() ));
            boEstado = true;
            oConexion.getoConexion().setAutoCommit(false); 
            
        } catch (java.lang.ClassNotFoundException oError) {
            throw new GeneralException("(conectar) Clase no encontrada: " + oError.getMessage(),oError);
 
        }
         catch (SQLException oError) {
            throw new GeneralException("(conectar) Error de Motor de Base de Datos: " + oError.getMessage(),oError);            
    
        }

        return boEstado;
    }
	
	public void desconectar() {
        try {
        	oConexion.getoConexion().close();
        	oConexion.setoConexion(null);            
        } catch (SQLException oError) {
            throw new GeneralException("(desconectar) Error de Motor de Base de Datos: "+ oError.getMessage(),oError); 
         
        }
    }
	
    public void commit() {

        try {
        	oConexion.getoConexion().commit();
        	oConexion.getoConexion().setAutoCommit(true);
        } catch (SQLException oError) {
            throw new GeneralException("(commit) Error de Motor de Base de Datos: " + oError.getMessage(),oError);            
           
        }
          
    }
	
	
	
	

}
