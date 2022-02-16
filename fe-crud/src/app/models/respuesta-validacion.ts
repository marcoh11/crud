export interface IRespuestaValidacion {
  sCodigoVal?: String;
  sTipoVal?: String;
  sMensajeVal?: String;
}

export class RespuestaValidacion implements IRespuestaValidacion {
  constructor(
    public sCodigoVal?: String,
    public sTipoVal?: String,
    public sMensajeVal?: String
  ) {}
}
