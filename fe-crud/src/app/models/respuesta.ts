import { RespuestaError } from "./respuesta-error";
import { RespuestaValidacion } from "./respuesta-validacion";

export interface IRespuesta {
  oRespuesta: any;
  boIndVal: boolean;
  oDatVal: RespuestaValidacion;
  boIndError: boolean;
  oDatError: RespuestaError
}

export class Respuesta implements IRespuesta{
  constructor(
    public oRespuesta: any,
    public boIndVal: boolean,
    public oDatVal: RespuestaValidacion,
    public boIndError: boolean,
    public oDatError: RespuestaError
  ) {}
}
