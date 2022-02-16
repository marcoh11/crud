package com.litconsulting.dto;


public class RespuestaDto<T> {
		private String sMensajeDB;
		private String sEstadoDB;
	    private T oRespuesta ; 
	    private boolean boIndVal ; 
	    private boolean boIndError ; 
		public RespuestaDto() {
			super();
		}

		public String getsMensajeDB() {
			return sMensajeDB;
		}

		public void setsMensajeDB(String sMensajeDB) {
			this.sMensajeDB = sMensajeDB;
		}

		public String getsEstadoDB() {
			return sEstadoDB;
		}

		public void setsEstadoDB(String sEstadoDB) {
			this.sEstadoDB = sEstadoDB;
		}

		public T getoRespuesta() {
			return oRespuesta;
		}

		public void setoRespuesta(T oRespuesta) {
			this.oRespuesta = oRespuesta;
		}

		public boolean isBoIndVal() {
			return boIndVal;
		}

		public void setBoIndVal(boolean boIndVal) {
			this.boIndVal = boIndVal;
		}

		public boolean isBoIndError() {
			return boIndError;
		}

		public void setBoIndError(boolean boIndError) {
			this.boIndError = boIndError;
		}
}
