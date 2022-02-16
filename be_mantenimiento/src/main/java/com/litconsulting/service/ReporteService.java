package com.litconsulting.service;
import java.util.List;

import com.litconsulting.dto.ReporteDto;
import com.litconsulting.dto.ReporteListarDto;
import com.litconsulting.dto.RespuestaDto;

public interface ReporteService {
	public List<ReporteListarDto> listarRegistros(Integer nEstado);
	public RespuestaDto<String> registrarRegistro(ReporteDto reporteDto);
	public RespuestaDto<String> actualizarRegistro(ReporteDto reporteDto);
	public RespuestaDto<String> eliminarRegistro(Integer nIndice);
}
