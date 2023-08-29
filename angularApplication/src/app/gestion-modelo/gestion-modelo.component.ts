import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Modelo } from '../models/modelo.model';
import { PopUpComponent } from '../pop-up/pop-up.component';
import { GestionModeloService } from './gestion-modelo.service';

@Component({
  selector: 'app-gestion-modelo',
  templateUrl: './gestion-modelo.component.html',
  styleUrls: ['./gestion-modelo.component.css']
})
export class GestionModeloComponent implements OnInit {

  displayedColumns: string[] = ['modelId', 'name', 'factory', 'action'];
  dataSource: Modelo[] = [];
  dialog: any;

  constructor(
    private dialogo: MatDialog,
    private toasterService: ToastrService,
    private gestionModelo: GestionModeloService
  ) {
    if (localStorage.getItem('rol') != 'Administrador') {
      this.displayedColumns.splice(-1, 1);
    }
  }

  ngOnInit(): void {
    this.gestionModelo.getAllModel()
      .subscribe(data => {
        this.dataSource = data;
      })
  }

  onCreate(event: any) {
    event.preventDefault();
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "auto" , data:
        { titulo: "Gestión de Modelos", entidad: "modelos", campos: [
            { name: "name", type: "text",property: "name"},
            { factory: "fabricante", type: "select", property: "factory"}
          ], mode: {type: "create", datos: {}}
        }
      }
    );
    this.chargeInfoTable();
  }

  onUpdate(event: any, data: any) {
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "auto" , data:
        { titulo: "Gestión de Modelos", entidad: "modelos", campos: [
            { name: "name", type: "text",property: "name"},
            { name: "fabricante", type: "select", property: "factory"}
          ], mode: {type: "update", datos: data}
        }
      }
    );
    this.chargeInfoTable();
  }

  onDelete(event: any, data: any) {
    this.gestionModelo.deleteModel(data.modelId)
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
      this.gestionModelo.getAllModel()
        .subscribe(data => {
        this.dataSource = data;
      });
    } else {
      this.dialog.afterClosed().subscribe(() => {
        this.gestionModelo.getAllModel()
          .subscribe(data => {
          this.dataSource = data;
        })
      });
    }
  }

}
