import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { GestionAreasComponent } from '../gestion-areas/gestion-areas.component';
import { GestionUsuarioComponent } from '../gestion-usuario/gestion-usuario.component';
import { GestionDispositivoComponent } from '../gestion-dispositivo/gestion-dispositivo.component';
import { GestionFabricanteComponent } from '../gestion-fabricante/gestion-fabricante.component';
import { GestionModeloComponent } from '../gestion-modelo/gestion-modelo.component';
import { GestionRolComponent } from '../gestion-rol/gestion-rol.component';
import { AuthGuardGuard } from '../guards/auth-guard.guard';
import { NavbarComponent } from './navbar.component';

const routes: Routes = [

      {
        path: '',
        component: NavbarComponent,
        children: [
          {
            path: '',
            redirectTo: 'area',
            pathMatch: 'full'
          },
          {
            path: 'area',
            canActivate: [AuthGuardGuard],
            component: GestionAreasComponent
          },
          {
            path: 'usuario',
            component: GestionUsuarioComponent
          },
          {
            path: 'dispositivo',
            component: GestionDispositivoComponent
          },
          {
            path: 'fabricante',
            component: GestionFabricanteComponent
          },
          {
            path: 'modelo',
            component: GestionModeloComponent
          },
          {
            path: 'rol',
            component: GestionRolComponent
          }
        ]
      },
]

@NgModule({
  declarations: [

  ],
  imports: [
    RouterModule.forChild(routes),
    CommonModule,
  ],
  exports: [RouterModule]
})
export class NavRoutingModule { }
