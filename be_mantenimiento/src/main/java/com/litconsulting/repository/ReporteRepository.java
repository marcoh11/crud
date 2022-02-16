package com.litconsulting.repository;

import java.util.List;

import com.litconsulting.dto.ReporteDto;
import com.litconsulting.dto.ReporteListarDto;
import com.litconsulting.dto.RespuestaDto;

public interface ReporteRepository {
	public List<ReporteListarDto> listarRegistros(Integer nEstado);
	public RespuestaDto<String> registrarRegistro(ReporteDto reporteDto);
	public RespuestaDto<String> actualizarRegistro(ReporteDto reporteDto);
	public RespuestaDto<String> eliminarRegistro(Integer nIndice);
}
