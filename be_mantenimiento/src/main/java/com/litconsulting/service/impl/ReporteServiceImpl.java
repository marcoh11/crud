package com.litconsulting.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litconsulting.dto.ReporteDto;
import com.litconsulting.dto.ReporteListarDto;
import com.litconsulting.dto.RespuestaDto;
import com.litconsulting.service.ReporteService;

import com.litconsulting.exception.GeneralException;
import com.litconsulting.repository.ReporteRepository;

@Service("reporteService")
public class ReporteServiceImpl implements ReporteService {
private final Logger O_LOGGER = (Logger) LoggerFactory.getLogger(this.getClass().getName());
@Autowired
private ReporteRepository oReporteRepository;
	@Override
	public List<ReporteListarDto> listarRegistros(Integer nEstado) {
		List<ReporteListarDto> oListaReporteDto=new ArrayList<ReporteListarDto>();
		try {
			oListaReporteDto=oReporteRepository.listarRegistros(nEstado);
			
		}catch(GeneralException e){
	    	O_LOGGER.error("Se ha encontrado un error (reporteServiceImpl/listarRegistros): "+ e.getMessage());
	    	throw new GeneralException (e.getMessage(),e);
		}
		
		return oListaReporteDto;
	}

	@Override
	public RespuestaDto<String> registrarRegistro(ReporteDto reporteDto) {
		RespuestaDto<String> oRespuestaDto;
		try {
			oRespuestaDto=oReporteRepository.registrarRegistro(reporteDto);
			
		}catch(GeneralException e){
	    	O_LOGGER.error("Se ha encontrado un error (reporteServiceImpl/registrarRegistro): "+ e.getMessage());
	    	throw new GeneralException (e.getMessage(),e);
		}
		
		return oRespuestaDto;
	}

	@Override
	public RespuestaDto<String> actualizarRegistro(ReporteDto reporteDto) {
		RespuestaDto<String> oRespuestaDto;
		try {
			oRespuestaDto=oReporteRepository.actualizarRegistro(reporteDto);
			
		}catch(GeneralException e){
	    	O_LOGGER.error("Se ha encontrado un error (reporteServiceImpl/actualizarRegistro): "+ e.getMessage());
	    	throw new GeneralException (e.getMessage(),e);
		}
		
		return oRespuestaDto;
	}

	@Override
	public RespuestaDto<String> eliminarRegistro(Integer nIndice) {
		RespuestaDto<String> oRespuestaDto;
		try {
			oRespuestaDto=oReporteRepository.eliminarRegistro(nIndice);
			
		}catch(GeneralException e){
	    	O_LOGGER.error("Se ha encontrado un error (reporteServiceImpl/eliminarRegistro): "+ e.getMessage());
	    	throw new GeneralException (e.getMessage(),e);
		}
		
		return oRespuestaDto;
	}

}
