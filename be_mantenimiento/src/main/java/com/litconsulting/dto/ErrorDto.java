package com.litconsulting.dto;

public class ErrorDto {
	

	    private String sCodigoError;
	    private String sTipoError;
	    private String sMensajeError;
		public ErrorDto() {
			super();
		}
		public String getsCodigoError() {
			return sCodigoError;
		}
		public void setsCodigoError(String sCodigoError) {
			this.sCodigoError = sCodigoError;
		}
		public String getsTipoError() {
			return sTipoError;
		}
		public void setsTipoError(String sTipoError) {
			this.sTipoError = sTipoError;
		}
		public String getsMensajeError() {
			return sMensajeError;
		}
		public void setsMensajeError(String sMensajeError) {
			this.sMensajeError = sMensajeError;
		}

	    


}
