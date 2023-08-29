import { Component, OnInit } from '@angular/core';
import { Area } from '../models/area.model';

import { GestionAreasService } from './gestion-areas.service';

import { MatDialog } from '@angular/material/dialog';
import { PopUpComponent } from '../pop-up/pop-up.component';
import { LoginService } from '../login/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-gestion-areas',
  templateUrl: './gestion-areas.component.html',
  styleUrls: ['./gestion-areas.component.css']
})
export class GestionAreasComponent implements OnInit {

  displayedColumns: string[] = ['areaId', 'name', 'action'];
  dataSource: Area[] = [];
  dialog: any;

  constructor(
    private dialogo: MatDialog,
    private loginService: LoginService,
    private gestionAreaService: GestionAreasService,
    private toasterService: ToastrService
  ) {}

  ngOnInit(): void {
    this.loginService.tokenSubject.subscribe((value: string) => {
      console.log(value);
    })
    this.gestionAreaService.getAllAreas()
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
      { width: "auto" , data:
        { titulo: "Gestión de Areas", entidad: "areas", campos: [
            { name: "nombre", type: "text",property: "name"}
          ], mode: {type: "create", datos: {}}
        }
      }
    );
    this.chargeInfoTable();
  }

  onUpdate(event: any, data: any) {
    event.preventDefault();
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "auto" , data:
        { titulo: "Gestión de Areas", entidad: "areas", campos: [
            { name: "nombre", type: "text", property: "name"}
          ], mode: {type: "update", datos: data}
        }
      }
    );
    this.chargeInfoTable();
  }

  onDelete(event: any, data: any) {
    this.gestionAreaService.deleteArea(data.areaId)
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
      this.gestionAreaService.getAllAreas()
        .subscribe(data => {
        this.dataSource = data;
      });
    } else {
      this.dialog.afterClosed().subscribe(() => {
        this.gestionAreaService.getAllAreas()
          .subscribe(data => {
          this.dataSource = data;
        })
      });
    }
  }

}

