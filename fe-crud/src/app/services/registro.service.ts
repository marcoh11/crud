import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { IRespuesta, Respuesta } from '../models/respuesta';
import { IRespuestaError } from '../models/respuesta-error';
import { IRespuestaValidacion } from '../models/respuesta-validacion';

@Injectable({
  providedIn: 'root'
})
export class RegistroService {
  sUrl: string = `${environment.cUri}/reporte`
  constructor(
    private http: HttpClient
  ) { }
  respuestaError: IRespuestaError = {
    sCodigoError: '',
    sTipoError: '',
    sMensajeError: ''
  };
  respuestaValidacion: IRespuestaValidacion = {
    sCodigoVal: '',
    sTipoVal: '',
    sMensajeVal: ''
  };
  respuesta: IRespuesta = {
    boIndError: false,
    boIndVal: false,
    oDatError: this.respuestaError,
    oDatVal: this.respuestaValidacion,
    oRespuesta: {}
  };

  mListarRegistros(oObjeto: any){
    return this.http.post<Respuesta>(`${this.sUrl}/listar-registros`, oObjeto)
    .pipe(
      map((r: any) => {
        this.respuesta.oRespuesta = r;
        switch (true) {
          case this.respuesta.boIndError:
            alert(`${this.respuesta.oDatError.sMensajeError}`);
            return this.respuesta; 
          default:
            return this.respuesta;
        }
      })
    );
  }
  mIngresarRegistros(oObjeto: any){
    return this.http.post<Respuesta>(`${this.sUrl}/registrar-cliente`, oObjeto)
    .pipe(
      map((r: any) => {
        this.respuesta.oRespuesta = r.oRespuesta;
        switch (true) {
          case this.respuesta.boIndError:
            alert(`${this.respuesta.oDatError.sMensajeError}`);
            return this.respuesta; 
          default:
            return this.respuesta;
        }
      })
    );
  }
  mEliminarRegistros(oObjeto: any){
    return this.http.post<Respuesta>(`${this.sUrl}/eliminar-registros`, oObjeto)
    .pipe(
      map((r: any) => {
        this.respuesta.oRespuesta = r;
        switch (true) {
          case this.respuesta.boIndError:
            alert(`${this.respuesta.oDatError.sMensajeError}`);
            return this.respuesta; 
          default:
            return this.respuesta;
        }
      })
    );
  }
}
