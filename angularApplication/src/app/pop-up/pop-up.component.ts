import { Component, Inject, Input, OnInit, Output } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { GestionAreasService } from '../gestion-areas/gestion-areas.service';
import { GestionDispositivoService } from '../gestion-dispositivo/gestion-dispositivo.service';
import { GestionFabricanteService } from '../gestion-fabricante/gestion-fabricante.service';
import { GestionModeloService } from '../gestion-modelo/gestion-modelo.service';
import { GestionRolService } from '../gestion-rol/gestion-rol.service';
import { GestionUsuarioService } from '../gestion-usuario/gestion-usuario.service';
import { ToastrService } from 'ngx-toastr';
import { Area } from '../models/area.model';
import { Dispositivo } from '../models/dispositivo.model';
import { Fabricante } from '../models/fabricante.model';
import { Modelo } from '../models/modelo.model';
import { Role } from '../models/role.model';
import { Usuario } from '../models/usuario.model';

interface Field {
  name: string,
  type: string,
  property: string
}

interface FieldsDinamic {
  [key: string]: any
}

interface FieldSelectDinamic {
  [key: string]: any
}

@Component({
  selector: 'app-pop-up',
  templateUrl: './pop-up.component.html',
  styleUrls: ['./pop-up.component.css']
})
export class PopUpComponent implements OnInit {

  campos: Field[] = [];
  titulo: string = "";
  private entidad: string = "";
  propiedades: FieldsDinamic = {};

  areas: Area[] = [];
  roles: Role[] = [];
  fabricante: Fabricante[] = [];

  infoSelect: FieldSelectDinamic = {};
  name: string = '';
  mode: string = '';

  constructor(
    @Inject(MAT_DIALOG_DATA) public datos: any,
    public dialogRef: MatDialogRef<PopUpComponent>,
    private gestionDispositivo: GestionDispositivoService,
    private gestionFabricante: GestionFabricanteService,
    private gestionUsuario: GestionUsuarioService,
    private gestionModelo: GestionModeloService,
    private gestionArea: GestionAreasService,
    private gestionRol: GestionRolService,
    private toasterService: ToastrService
  ) {
    this.entidad = this.datos.entidad;
    this.agregarControl(this.datos.campos);
    this.mode = this.datos.mode.type;
    this.campos = this.datos.campos;
  }

  ngOnInit(): void {
    this.titulo = this.datos.titulo;

    this.fillFieldsSelect();
  }

  setValue(event: any, property: string) {
    this.propiedades[property] = event.target.value;
  }

  createData(event: any) {
    switch (this.entidad) {
      case "usuarios": {
          this.gestionUsuario.createUser(<Usuario> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Usuario Creado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se creo el Usuario');
          });

        break;
      }
      case "fabricante": {
        this.gestionFabricante.createFactory(<Fabricante> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Fabricante Creado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se creo el Fabricante');
          });
        break;
      }
      case "modelos": {
        this.gestionModelo.createModel(<Modelo> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Modelo Creado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se creo el Modelo');
          });
          break;
      }
      case "areas": {
        this.gestionArea.createArea(<Area> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Area Creada');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se creo el Area');
          });
          break;
      }
      case "dispositivos": {
        this.gestionDispositivo.createDevice(<Dispositivo> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Dispositivo Creado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se creo el Dispositivo');
          });
          break;
      }
      case "roles": {
        this.gestionRol.createRole(<Role> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Rol Creado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se creo el Rol');
          });
          break;
      }
    }
  }

  updateData(event: any) {
    const id = this.datos.mode.datos;
    switch (this.entidad) {
      case "usuarios": {
        this.gestionUsuario.updateUser(id.userId, <Usuario> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Dispositivo Actualizado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se actualizo el Dispositivo');
          });
        break;
      }
      case "fabricante": {
        this.gestionFabricante.updateFactory(id.factoryId,<Fabricante> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Fabricante Actualizado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se actualizo el Fabricante');
          });
        break;
      }
      case "areas": {
        this.gestionArea.updateArea(id.areaId,<Area> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Area Actualizada');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se actualizo el Area');
          });
          break;
      }
      case "modelos": {
        this.gestionModelo.updateModel(id.modelId,<Modelo> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Modelo Actualizado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se actualizo el Modelo');
          });
          break;
      }
      case "dispositivos": {
        this.gestionDispositivo.updateDevice(id.deviceId ,<Dispositivo> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Dispositivo Actualizado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se actualizo el Dispositivo');
          });
          break;
      }
      case "roles": {
        this.gestionRol.updateRole(id.rolId, <Role> this.formatData())
          .subscribe(() => {
            this.toasterService.success('Exitosamente', 'Rol Actualizado');
            this.dialogRef.close();
          }, errorMsg => {
            this.toasterService.error('Error', 'No se actualizo el Rol');
          });
          break;
      }
    }
  }

  formatData() {
    try {
      let data: any = {};
      let counter: number = 0;
      for (let key in this.propiedades) {
        if (this.campos[counter].type === "number") {
          data[key] = parseInt(this.propiedades[key]);
        } else {
          data[key] = this.propiedades[key];
        }
        counter++;
      }
      return data;
    } catch (error) {
      console.log(error);
    }

  }

  agregarControl(data: Field[]) {
    data.map((campo: Field) => {

      if (this.datos.mode.type === "update") {
        this.propiedades[campo.property] = this.datos.mode.datos[campo.property];
      } else {
        if (campo.type === "select") {
          this.propiedades[campo.property] = {name: ""};
        } else {
          this.propiedades[campo.property] = "";
        }
      }
    })
  }

  selectionSelects(value: any, itemProperty: string) {
    const result = this.infoSelect[itemProperty].find((a:any) =>
      a.name == value);
    this.propiedades[itemProperty] = result;
  }

  fillFieldsSelect() {
    switch (this.entidad) {
      case "usuarios": {
        this.gestionArea.getAllAreas()
          .subscribe(a => {
            this.infoSelect["area"] = a;
          });
        this.gestionRol.getAllRoles()
          .subscribe(r => {
            this.infoSelect["role"] = r;
          })
        break;
      }
      case "modelos": {
        this.gestionFabricante.getAllFactory()
          .subscribe(f => {
            this.infoSelect["factory"] = f;
          })
        break;
      }
      case "dispositivos": {
        this.gestionArea.getAllAreas()
          .subscribe(a => {
            this.infoSelect["area"] = a;
          });
        this.gestionFabricante.getAllFactory()
          .subscribe(f => {
            this.infoSelect["factory"] = f;
          })
        this.gestionModelo.getAllModel()
          .subscribe(m => {
            this.infoSelect["model"] = m;
          })
        break;
      }
    }
  }

}
