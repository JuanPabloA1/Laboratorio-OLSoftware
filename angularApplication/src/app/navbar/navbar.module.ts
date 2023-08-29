import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GestionAreasComponent } from '../gestion-areas/gestion-areas.component';
import { GestionDispositivoComponent } from '../gestion-dispositivo/gestion-dispositivo.component';
import { GestionUsuarioComponent } from '../gestion-usuario/gestion-usuario.component';
import { GestionFabricanteComponent } from '../gestion-fabricante/gestion-fabricante.component';
import { GestionModeloComponent } from '../gestion-modelo/gestion-modelo.component';
import { GestionRolComponent } from '../gestion-rol/gestion-rol.component';
import { NavbarComponent } from './navbar.component';
import { MaterialModule } from '../material/material.module';
import { NavRoutingModule } from './nav-routing.module';



@NgModule({
  declarations: [
    GestionAreasComponent,
    GestionUsuarioComponent,
    GestionDispositivoComponent,
    GestionFabricanteComponent,
    GestionModeloComponent,
    GestionRolComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    NavRoutingModule,
  ]
})
export class NavbarModule { }
