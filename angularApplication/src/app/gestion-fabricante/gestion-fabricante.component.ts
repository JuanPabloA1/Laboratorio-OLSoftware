import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Fabricante } from '../models/fabricante.model';
import { PopUpComponent } from '../pop-up/pop-up.component';
import { GestionFabricanteService } from './gestion-fabricante.service';

@Component({
  selector: 'app-gestion-fabricante',
  templateUrl: './gestion-fabricante.component.html',
  styleUrls: ['./gestion-fabricante.component.css']
})
export class GestionFabricanteComponent implements OnInit {

  dataSource: Fabricante[] = [];
  displayedColumns: string[] = [
  'factoryId',
  'name',
  'action'
  ];
  dialog: any;


  constructor(
    private gestionFabricante: GestionFabricanteService,
    private toasterService: ToastrService,
    private dialogo: MatDialog
  ) {
    if (localStorage.getItem('rol') != 'Administrador') {
      this.displayedColumns.splice(-1, 1);
    }
  }

  ngOnInit(): void {
    this.gestionFabricante.getAllFactory()
      .subscribe(data => {
        this.dataSource = data;
    });
  }

  onCreate(event: any) {
    event.preventDefault();
    this.dialog = this.dialogo.open(PopUpComponent,
      { width: "auto" , data:
        { titulo: "Gestión de Fabricantes", entidad: "fabricante", campos: [
            { name: "nombre", type: "text", property: "name"}
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
        { titulo: "Gestión de Fabricantes", entidad: "fabricante", campos: [
            { name: "nombre", type: "text",  property: "name"}
          ], mode: {type: "update", datos: data}
        }
      }
    );
    this.chargeInfoTable();
  }

  onDelete(event: any, data: any) {
    this.gestionFabricante.deleteFactory(data.factoryId)
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
      this.gestionFabricante.getAllFactory()
        .subscribe(data => {
        this.dataSource = data;
      });
    } else {
      this.dialog.afterClosed().subscribe(() => {
        this.gestionFabricante.getAllFactory()
          .subscribe(data => {
          this.dataSource = data;
        })
      });
    }
  }

}
