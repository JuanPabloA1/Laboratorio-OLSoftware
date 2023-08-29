import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Dispositivo } from '../models/dispositivo.model';
import { GestionDispositivoService } from './gestion-dispositivo.service';
import { ToastrService } from 'ngx-toastr';
import { PopUpComponent } from '../pop-up/pop-up.component';

@Component({
  selector: 'app-gestion-dispositivo',
  templateUrl: './gestion-dispositivo.component.html',
  styleUrls: ['./gestion-dispositivo.component.css']
})
export class GestionDispositivoComponent implements OnInit {

  displayedColumns: string[] = [
    'name',
    'area',
    'stateDevice',
    'typeDevice',
    'factory',
    'model',
    'numberSerie',
    'action'];
  dataSource: Dispositivo[] = [];
  dialog: any;

  constructor(
    private dialogo: MatDialog,
    private gestionDispositivo: GestionDispositivoService,
    private toasterService: ToastrService
  ) {
    if (localStorage.getItem('rol') != 'Administrador') {
      this.displayedColumns.splice(-1, 1);
    }
  }

  ngOnInit(): void {
    this.gestionDispositivo.getAllDevices()
      .subscribe(data => {
        this.dataSource = data;
      });
  }

  onCreate(event: any) {
    event.preventDefault();
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "70%" , data:
        { titulo: "Gestión de Dispositovo", entidad: "dispositivos", campos: [
            { name: "Nombre Dispositivo", type: "text",property: "name"},
            { name: "Area", type: "select",property: "area"},
            { name: "Estado Dispositivo", type: "text",property: "stateDevice"},
            { name: "Tipo Dispositivo", type: "text",property: "typeDevice"},
            { name: "Fabricante", type: "select",property: "factory"},
            { name: "Modelo", type: "select",property: "model"},
            { name: "Numero de Serie", type: "text",property: "numberSerie"},
            { name: "Comentario", type: "text",property: "coment"}
          ], mode: {type: "create", datos: {}}
        }
      }
    );

    this.chargeInfoTable();
  }

  onUpdate(event: any, data: any) {
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "70%" , data:
        { titulo: "Gestión de Dispositovo", entidad: "dispositivos", campos: [
            { name: "Nombre Dispositivo", type: "text",property: "name"},
            { name: "Area", type: "select",property: "area"},
            { name: "Estado Dispositivo", type: "text",property: "stateDevice"},
            { name: "Tipo Dispositivo", type: "text",property: "typeDevice"},
            { name: "Fabricante", type: "select",property: "factory"},
            { name: "Modelo", type: "select",property: "model"},
            { name: "Numero de Serie", type: "text",property: "numberSerie"},
            { name: "Comentario", type: "text",property: "coment"}
          ], mode: {type: "update", datos: data}
        }
      }
    );

    this.chargeInfoTable();
  }

  onDelete(event: any, data: any) {
    this.gestionDispositivo.deleteDevice(data.deviceId)
      .subscribe(() => {
        this.toasterService.success('Exitosamente', 'Se elimino el Dispositiovo');
        this.chargeInfoTable(true);
      }, errorMsg => {
        this.toasterService.error('Error', 'No se elimino el Dispositivo');
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
      this.gestionDispositivo.getAllDevices()
        .subscribe(data => {
        this.dataSource = data;
      });
    } else {
      this.dialog.afterClosed().subscribe(() => {
        this.gestionDispositivo.getAllDevices()
          .subscribe(data => {
          this.dataSource = data;
        })
      });
    }
  }

}
