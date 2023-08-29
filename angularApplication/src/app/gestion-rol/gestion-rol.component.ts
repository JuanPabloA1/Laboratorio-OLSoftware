import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Role } from '../models/role.model';
import { PopUpComponent } from '../pop-up/pop-up.component';
import { GestionRolService } from './gestion-rol.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-gestion-rol',
  templateUrl: './gestion-rol.component.html',
  styleUrls: ['./gestion-rol.component.css']
})
export class GestionRolComponent implements OnInit {

  dataSource: Role[] = [];
  displayedColumns: string[] = ['rolId', 'name', 'action'];
  dialog: any;

  constructor(
    private dialogo: MatDialog,
    private toastrService: ToastrService,
    private gestionRol: GestionRolService
  ) {
    if (localStorage.getItem('rol') != 'Administrador') {
      this.displayedColumns.splice(-1, 1);
    }
  }

  ngOnInit(): void {
    this.gestionRol.getAllRoles()
      .subscribe(data => {
        this.dataSource = data;
      })
  }

  onCreate(event: any) {
    event.preventDefault();
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "auto" , data:
        { titulo: "Gestión de Roles", entidad: "roles", campos: [
            { name: "Nombre rol", type: "text",property: "name"}
          ], mode: {type: "create", datos: {}}
        }
      }
    );
    this.chargeInfoTable();

  }

  onUpdate(event: any, data:any) {
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "auto" , data:
        { titulo: "Gestión de Roles", entidad: "roles", campos: [
            { name: "Nombre rol", type: "text",property: "name"}
          ], mode: {type: "update", datos: data}
        }
      }
    );
    this.chargeInfoTable();
  }

  onDelete(event: any, data:any) {
    this.gestionRol.deleteRole(data.rolId)
      .subscribe(() => {
        this.toastrService.success('Exitosamente', 'Se elimino el Rol');
        this.chargeInfoTable(true);
      }, errorMsg => {
        this.toastrService.error('Error', 'No se elimino el Rol');
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
      this.gestionRol.getAllRoles()
        .subscribe(data => {
        this.dataSource = data;
      });
    } else {
      this.dialog.afterClosed().subscribe(() => {
        this.gestionRol.getAllRoles()
          .subscribe(data => {
          this.dataSource = data;
        })
      });
    }
  }

}
