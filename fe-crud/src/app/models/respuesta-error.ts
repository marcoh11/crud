export interface IRespuestaError {
  sCodigoError: String;
  sTipoError: String;
  sMensajeError: String;
}

export class RespuestaError implements IRespuestaError{
  constructor(
    public sCodigoError: String,
    public sTipoError: String,
    public sMensajeError: String
  ) {}
}
