package com.litconsulting.repository.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.litconsulting.dto.ReporteDto;
import com.litconsulting.dto.ReporteListarDto;
import com.litconsulting.dto.RespuestaDto;
import com.litconsulting.repository.ReporteRepository;

import oracle.jdbc.OracleTypes;

import com.litconsulting.exception.GeneralException;

import com.litconsulting.configuration.ConexionConfiguration;


@Repository("reporteRepositoryImpl")
public class ReporteRepositoryImpl implements ReporteRepository, EnvironmentAware {
	private final Logger O_LOGGER = (Logger) LoggerFactory.getLogger(this.getClass().getName());
	private ResultSet oResultado = null;
	private CallableStatement oDeclaracion = null;
	private Environment oPropiedad;
	private static String S_ESQUEMA_BD_MANTENIMIENTO = "";
	private static String S_PAQUETE_BD_MANTENIMIENTO = "";
	
	@Override
	public void setEnvironment(Environment environment) {
		this.oPropiedad = environment;
		S_ESQUEMA_BD_MANTENIMIENTO = oPropiedad.getProperty("esquema.mantenimiento");
		S_PAQUETE_BD_MANTENIMIENTO = oPropiedad.getProperty("paquete.mantenimiento");

	}
	@Override
	public List<ReporteListarDto> listarRegistros(Integer nEstado) {
	List<ReporteListarDto> oListaReporteDto= new ArrayList<ReporteListarDto>();
	
		try {
			ConexionConfiguration oConfiguracionConexion = new ConexionConfiguration(oPropiedad);
			String sSentencia = "{call " +S_ESQUEMA_BD_MANTENIMIENTO + S_PAQUETE_BD_MANTENIMIENTO
					+ "USP_SEL_REGISTROS(?,?,?,?)}";
			System.out.println(sSentencia);
			oConfiguracionConexion.conectar();

			oDeclaracion = oConfiguracionConexion.oConexion.getoConexion().prepareCall(sSentencia);

			oDeclaracion.setString(1, nEstado.toString());
			oDeclaracion.registerOutParameter(2, OracleTypes.INTEGER);
			oDeclaracion.registerOutParameter(3, OracleTypes.VARCHAR);
			oDeclaracion.registerOutParameter(4, OracleTypes.CURSOR);
			oDeclaracion.execute();

			Integer nNumBD = oDeclaracion.getInt(2);
			String sMensajeBD = oDeclaracion.getString(3);

			if (nNumBD != 1) {

				O_LOGGER.error(
						"Se ha encontrado un error (ReporteRepositoryImpl/listarRegistros) Resultado BD [{}] - Comentario BD [{}] ",
						nNumBD, sMensajeBD);
				oConfiguracionConexion.desconectar();
				throw new GeneralException("Resultado BD [" + nNumBD + "]" + "- Comentario BD [" + sMensajeBD + "]");

			}

			O_LOGGER.info("listarRegistros -> Resultado BD [{}] - Comentario BD [{}]", nNumBD, sMensajeBD);
			oResultado = (ResultSet) oDeclaracion.getObject(4);
			while (oResultado.next()) {
				ReporteListarDto oReporteDto = new ReporteListarDto();
				oReporteDto.setnRegistro(new Integer(oResultado.getString("ID_REGISTRO")));
				oReporteDto.setsNombre(oResultado.getString("S_NOMBRE"));
				oReporteDto.setsApellidoM(oResultado.getString("S_APELLIDO_P"));
				oReporteDto.setsApellidoP(oResultado.getString("S_APELLIDO_M"));
				oReporteDto.setsNacimiento(oResultado.getString("D_FECHA_N"));
				oReporteDto.setsSexo(oResultado.getString("S_SEXO"));
				oReporteDto.setsDireccion(oResultado.getString("S_DIRECCION"));
				oReporteDto.setsCorreo(oResultado.getString("S_CORREO"));
				oListaReporteDto.add(oReporteDto);
			}
			
			oConfiguracionConexion.desconectar();

			
		}
		catch (GeneralException oError) {

			O_LOGGER.error("Se ha encontrado un error (ReporteRepositoryImpl/listarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);

		} catch (SQLException oError) {
			O_LOGGER.error("Se ha encontrado un error SQL (ReporteRepositoryImpl/listarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);
		}
		return oListaReporteDto;
	}

	@Override
	public RespuestaDto<String> registrarRegistro(ReporteDto reporteDto) {
		RespuestaDto<String> oRespuestaDto=new RespuestaDto<String>();
		try {
			ConexionConfiguration oConfiguracionConexion = new ConexionConfiguration(oPropiedad);
			String sSentencia = "{call " + S_ESQUEMA_BD_MANTENIMIENTO + S_PAQUETE_BD_MANTENIMIENTO
					+ "USP_INS_REGISTROS(?,?,?,?,?,?,?,?,?)}";
			oConfiguracionConexion.conectar();
			oDeclaracion = oConfiguracionConexion.oConexion.getoConexion().prepareCall(sSentencia);
			oDeclaracion.setString(1,reporteDto.getsNombre());
			oDeclaracion.setString(2, reporteDto.getsApellidoP());
			oDeclaracion.setString(3, reporteDto.getsApellidoM());
			java.sql.Date dFecha = new java.sql.Date(reporteDto.getdNacimiento().getTime());
			oDeclaracion.setDate(4, dFecha);
			oDeclaracion.setString(5, reporteDto.getsSexo());
			oDeclaracion.setString(6, reporteDto.getsDireccion());
			oDeclaracion.setString(7, reporteDto.getsCorreo());
			oDeclaracion.registerOutParameter(8, OracleTypes.INTEGER);
			oDeclaracion.registerOutParameter(9, OracleTypes.VARCHAR);
			oDeclaracion.execute();
			Integer nNumBD = oDeclaracion.getInt(8);
			String sMensajeBD = oDeclaracion.getString(9);
			oRespuestaDto.setsEstadoDB(nNumBD.toString());
			oRespuestaDto.setoRespuesta(sMensajeBD);
			if (nNumBD != 1) {
				O_LOGGER.error(
						"Se ha encontrado un error (ReporteRepositoryImpl/RegistrarRegistros) Resultado BD [{}] - Comentario BD [{}] ",
						nNumBD, sMensajeBD);
				oConfiguracionConexion.desconectar();
				throw new GeneralException("Resultado BD [" + nNumBD + "]" + "- Comentario BD [" + sMensajeBD + "]");

			}

			O_LOGGER.info("registrarRegistros -> Resultado BD [{}] - Comentario BD [{}]", nNumBD, sMensajeBD);
			
		}
		catch (GeneralException oError) {

			O_LOGGER.error("Se ha encontrado un error (ReporteRepositoryImpl/registrarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);

		} catch (SQLException oError) {
			O_LOGGER.error("Se ha encontrado un error SQL (ReporteRepositoryImpl/registrarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);
		}
		return oRespuestaDto;
	}

	@Override
	public RespuestaDto<String> actualizarRegistro(ReporteDto reporteDto) {
		try {
			ConexionConfiguration oConfiguracionConexion = new ConexionConfiguration(oPropiedad);
			String sSentencia = "{call " + S_PAQUETE_BD_MANTENIMIENTO
					+ "USP_UPD_REGISTRO(?,?,?,?)}";

			oConfiguracionConexion.conectar();

			oDeclaracion = oConfiguracionConexion.oConexion.getoConexion().prepareCall(sSentencia);
			
		}
		catch (GeneralException oError) {

			O_LOGGER.error("Se ha encontrado un error (ReporteRepositoryImpl/actualizarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);

		} catch (SQLException oError) {
			O_LOGGER.error("Se ha encontrado un error SQL (ReporteRepositoryImpl/actualizarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);
		}
		return null;
	}

	@Override
	public RespuestaDto<String> eliminarRegistro(Integer nIndice) {
		RespuestaDto<String> oRespuesta=new RespuestaDto<String>();
		try {
			ConexionConfiguration oConfiguracionConexion = new ConexionConfiguration(oPropiedad);
			String sSentencia = "{call " + S_ESQUEMA_BD_MANTENIMIENTO + S_PAQUETE_BD_MANTENIMIENTO
					+ "USP_DEL_REGISTROS(?,?,?)}";
			
			oConfiguracionConexion.conectar();

			oDeclaracion = oConfiguracionConexion.oConexion.getoConexion().prepareCall(sSentencia);

			oDeclaracion.setString(1, nIndice.toString());
			oDeclaracion.registerOutParameter(2, OracleTypes.INTEGER);
			oDeclaracion.registerOutParameter(3, OracleTypes.VARCHAR);
			oDeclaracion.execute();

			Integer nNumBD = oDeclaracion.getInt(2);
			String sMensajeBD = oDeclaracion.getString(3);
			oRespuesta.setoRespuesta(sMensajeBD);

			O_LOGGER.info("eliminarRegistros -> Resultado BD [{}] - Comentario BD [{}]", nNumBD, sMensajeBD);
		}
		catch (GeneralException oError) {

			O_LOGGER.error("Se ha encontrado un error (ReporteRepositoryImpl/eliminarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);

		} catch (SQLException oError) {
			O_LOGGER.error("Se ha encontrado un error SQL (ReporteRepositoryImpl/eliminarRegistros): "
					+ oError.getMessage());
			throw new GeneralException(oError.getMessage(), oError);
		}
		return oRespuesta;
	}

}
