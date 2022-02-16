import { Component, OnInit } from '@angular/core';
import { RegistroService } from 'src/app/services/registro.service';
import { IRespuesta } from 'src/app/models/respuesta';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-crud',
  templateUrl: './crud.component.html',
  styleUrls: ['./crud.component.scss']
})
export class CrudComponent implements OnInit {

  constructor(
    private registroService: RegistroService
  ) { }

  oLista: any[] = [];

  oClienteRegistro: any = {
    sNombre: "",
    sApellidoP: "",
    sApellidoM: "",
    dNacimiento: '1997-03-13',
    sSexo: "",
    sDireccion: "",
    sCorreo: ""
  }
  ngOnInit(): void {

    this.mObtenerTodos();
  }
  async mObtenerTodos() {

    let oFiltro = {
      nEstado: 1
    }
    let resultado: IRespuesta = await this.registroService.mListarRegistros(oFiltro).toPromise();
    if (resultado.oRespuesta) {
      this.oLista = resultado.oRespuesta;
    }
  }
  async mIngresarRegistro(){
    Swal.fire({
      title: 'Ingresando cliente...',
      text: "Cargando",
      iconColor: '#198754',
      didOpen: () => {
        Swal.showLoading()},
      showConfirmButton:false
    });
    console.log(this.oClienteRegistro)
    let resultado: IRespuesta = await this.registroService.mIngresarRegistros(this.oClienteRegistro).toPromise();
    if (resultado.oRespuesta) {
      Swal.fire({
        title: 'Terminado',
        icon: 'success',
        text: resultado.oRespuesta,
        confirmButtonColor: '#2F4F79',
        iconColor: '#198754'}).then((result) => {
          if (result.isConfirmed) {
            window.location.reload()
          }
        });
    }
  }
  async mEliminarRegistro(idRegistro: any){
    let oFiltro = {
      nIndice: idRegistro
    }
    let resultado: IRespuesta = await this.registroService.mEliminarRegistros(oFiltro).toPromise();
    if (resultado.oRespuesta) {
      Swal.fire({
        title: 'Terminado',
        icon: 'success',
        text: resultado.oRespuesta,
        confirmButtonColor: '#2F4F79',
        iconColor: '#198754'}).then((result) => {
          if (result.isConfirmed) {
            window.location.reload()
          }
        });
    }
  }
  mAvisoEliminarRegistro(idRegistro: any) {
    Swal.fire({
      title: 'Estas seguro?',
      text: "Estas a punto de eliminar a un cliente",
      icon: 'error',
      showCancelButton: true,
      confirmButtonColor: '#198754',
      cancelButtonColor: '#6C757D',
      confirmButtonText: 'Si, eliminalo!',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.mEliminarRegistro(idRegistro)
      }
    })
  }
  mGuardarRegistro() {
    if(
      this.oClienteRegistro.sNombre && this.oClienteRegistro.sApellidoP && this.oClienteRegistro.sApellidoM
      && this.oClienteRegistro.sCorreo && this.oClienteRegistro.dNacimiento && this.oClienteRegistro.sSexo &&this.oClienteRegistro.sDireccion
    ){Swal.fire({
      title: 'Estas seguro?',
      text: "Estas a punto de registrar a un cliente",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#198754',
      cancelButtonColor: '#6C757D',
      confirmButtonText: 'Si, registralo!',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.mIngresarRegistro();
      }
    })}
    else{
      Swal.fire({
        title: 'Error',
        icon: 'warning',
        text: "Falta completar campos",
        iconColor: '#DC3545',
        confirmButtonColor: '#2F4F79'
      });
    }
    
  }

}
