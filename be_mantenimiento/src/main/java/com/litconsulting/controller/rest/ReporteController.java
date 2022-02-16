package com.litconsulting.controller.rest;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.litconsulting.service.ReporteService;
import com.litconsulting.dto.ErrorDto;
import com.litconsulting.dto.ReporteDto;
import com.litconsulting.dto.ReporteListarDto;
import com.litconsulting.dto.RespuestaDto;
import com.litconsulting.exception.GeneralException;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
	private final Logger O_LOGGER = (Logger) LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	private ReporteService oReporteService;
	
	
	
	@RequestMapping(value = "listar-registros", method = RequestMethod.POST)
	public ResponseEntity<?> listarRegistros(@RequestBody Map<String, String> oParametro)throws Exception, GeneralException{
		List<ReporteListarDto> oRespuesta= new ArrayList<ReporteListarDto>();
		ErrorDto oErrorDto = null;
		
		try {
			Integer nEstado=new Integer(oParametro.get("nEstado"));
			oRespuesta=oReporteService.listarRegistros(nEstado);
		}catch (GeneralException oError) {
			O_LOGGER.error("SEG-001 - Error Interno (ReporteController/listarRegistros): "+oError.getMessage());
			oErrorDto = new ErrorDto();
			oErrorDto.setsCodigoError("SEG-001");
	        oErrorDto.setsTipoError("Error Interno (ReporteController/listarRegistros)"); 
	        oErrorDto.setsMensajeError(oError.getMessage()); 	  	
			 return new ResponseEntity<List<ReporteListarDto>>(
					 	oRespuesta, 
					 	HttpStatus.BAD_REQUEST);
		
		}
		return ResponseEntity.ok()	    	  
	    	      .body(oRespuesta);
		
	}
	@RequestMapping(value = "registrar-cliente", method = RequestMethod.POST)
	public ResponseEntity<?> registrarCliente(@RequestBody Map<String, String> oParametro)throws Exception, GeneralException{
		RespuestaDto<String> oRespuesta= new RespuestaDto<String>();
		ErrorDto oErrorDto = null;
		ReporteDto oReporteDto=new ReporteDto();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-M-dd");
		try {
			Date dFechaUtil =formato.parse(oParametro.get("dNacimiento"));
			java.sql.Date dFechaSql =new java.sql.Date(dFechaUtil.getTime());
			oReporteDto.setsNombre(oParametro.get("sNombre"));
			oReporteDto.setsApellidoP(oParametro.get("sApellidoP"));
			oReporteDto.setsApellidoM(oParametro.get("sApellidoM"));
			oReporteDto.setdNacimiento(dFechaSql);
			oReporteDto.setsSexo(oParametro.get("sSexo"));
			oReporteDto.setsDireccion(oParametro.get("sDireccion"));
			oReporteDto.setsCorreo(oParametro.get("sCorreo"));
			oRespuesta=oReporteService.registrarRegistro(oReporteDto);
			
			
			
		}catch (GeneralException oError) {
			O_LOGGER.error("SEG-001 - Error Interno (ReporteController/registrarCliente): "+oError.getMessage());
			oErrorDto = new ErrorDto();
			oErrorDto.setsCodigoError("SEG-001");
	        oErrorDto.setsTipoError("Error Interno (ReporteController/registrarCliente)"); 
	        oErrorDto.setsMensajeError(oError.getMessage()); 	  	
			 return new ResponseEntity<RespuestaDto<String>>(
					 	oRespuesta, 
					 	HttpStatus.BAD_REQUEST);
		
		}
		return ResponseEntity.ok()	    	  
	    	      .body(oRespuesta);
		
	}
	@RequestMapping(value = "eliminar-registros", method = RequestMethod.POST)
	public ResponseEntity<?> eliminarRegistros(@RequestBody Map<String, String> oParametro)throws Exception, GeneralException{
		RespuestaDto<String> oRespuesta= new RespuestaDto<String>();
		ErrorDto oErrorDto = null;
		try {
			Integer nIndice=new Integer(oParametro.get("nIndice"));
			oRespuesta=oReporteService.eliminarRegistro(nIndice);

		}catch (GeneralException oError) {
			O_LOGGER.error("SEG-001 - Error Interno (ReporteController/eliminarRegistros): "+oError.getMessage());
			oErrorDto = new ErrorDto();
			oErrorDto.setsCodigoError("SEG-001");
	        oErrorDto.setsTipoError("Error Interno (ReporteController/eliminarRegistros)"); 
	        oErrorDto.setsMensajeError(oError.getMessage()); 	  	
			 return new ResponseEntity<RespuestaDto<String>>(
					 	oRespuesta, 
					 	HttpStatus.BAD_REQUEST);
		
		}
		return ResponseEntity.ok()	    	  
	    	      .body(oRespuesta);
		
	}
}
