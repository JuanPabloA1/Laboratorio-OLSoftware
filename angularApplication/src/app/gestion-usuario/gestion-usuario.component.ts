import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from '../models/usuario.model';
import { PopUpComponent } from '../pop-up/pop-up.component';

import { GestionUsuarioService } from './gestion-usuario.service';


@Component({
  selector: 'app-gestion-usuario',
  templateUrl: './gestion-usuario.component.html',
  styleUrls: ['./gestion-usuario.component.css']
})
export class GestionUsuarioComponent implements OnInit {

  displayedColumns: string[] = [
  'typeIdentity',
  'numberIdentity',
  'user',
  'pass',
  'phone',
  'role',
  'area',
  'action'
];
  dataSource: Usuario[] = [];
  dialog: any;

  constructor(
    private gestionUsuarios: GestionUsuarioService,
    private toasterService: ToastrService,
    private dialogo: MatDialog
  ) {}

  ngOnInit(): void {
    this.gestionUsuarios.getUsers()
      .subscribe(data => {
        this.dataSource = data;
    })
    if (localStorage.getItem('rol') != 'Administrador') {
      this.displayedColumns.splice(-1, 1);
    }
  }

  onCreate(event: any) {
    event.preventDefault();
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "70%" ,data:
        { titulo: "Gestión de Usuarios", entidad: "usuarios", campos: [
            { name: "Tipo identificacion", type: "text", property: "typeIdentity" },
            { name: "Número identificación", type: "number" , property:  "numberIdentity"},
            { name: "Primer nombre", type: "text" , property: "firstName"},
            { name: "Segundo nombre", type: "text" , property: "secondName"},
            { name: "Primer apellido", type: "text" , property: "firstLastName"},
            { name: "Segundo apellido", type: "text" , property: "secondLastName"},
            { name: "Correo electronico", type: "text" , property: "email"},
            { name: "Usuario", type: "text" , property: "user"},
            { name: "Contraseña", type: "text" , property: "pass"},
            { name: "Teléfono", type: "number" , property: "phone"},
            { name: "Role", type: "select" , property: "role"},
            { name: "Area", type: "select" , property: "area"}
          ], mode: {type: "create", datos: {}}
        }
      }
    );

    this.chargeInfoTable();
  }

  onUpdate(event: any,data: any) {
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "70%" ,data:
        { titulo: "Gestión de Usuarios", entidad: "usuarios", campos: [
            { name: "Tipo identificacion", type: "text", property: "typeIdentity" },
            { name: "Número identificación", type: "number" , property:  "numberIdentity"},
            { name: "Primer nombre", type: "text" , property: "firstName"},
            { name: "Segundo nombre", type: "text" , property: "secondName"},
            { name: "Primer apellido", type: "text" , property: "firstLastName"},
            { name: "Segundo apellido", type: "text" , property: "secondLastName"},
            { name: "Correo electronico", type: "text" , property: "email"},
            { name: "Usuario", type: "text" , property: "user"},
            { name: "Contraseña", type: "text" , property: "pass"},
            { name: "Teléfono", type: "number" , property: "phone"},
            { name: "Role", type: "select" , property: "role"},
            { name: "Area", type: "select" , property: "area"}
          ], mode: {type: "update", datos: data}
        }
      }
    );

    this.chargeInfoTable();
  }

  onDelete(event: any, data: any) {
    this.gestionUsuarios.deleteUser(data.userId)
      .subscribe(() => {
        this.toasterService.success("Se elimino correctamente el area", "Operación Exitosa");
        this.chargeInfoTable(true);
      }, errorMsg => {
        this.toasterService.error("No se elimino correctamente el area", "Error");
      });
  }

  validateRole() {
    if (localStorage.getItem('rol') == 'Administrador') {
      return true;
    } else {
      return false;
    }
  }

  chargeInfoTable(bandera?: boolean) {
    if (bandera) {
      this.gestionUsuarios.getUsers()
        .subscribe(data => {
        this.dataSource = data;
      });
    } else {
      this.dialog.afterClosed().subscribe(() => {
        this.gestionUsuarios.getUsers()
          .subscribe(data => {
          this.dataSource = data;
        })
      });
    }
  }

}
