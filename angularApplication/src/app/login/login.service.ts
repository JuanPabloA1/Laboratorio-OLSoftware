import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

import { BehaviorSubject, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { Auth } from '../models/auth.model';
import { Login } from '../models/login.model';
import { Usuario } from '../models/usuario.model';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = `${environment.API_URL}/auth/autentificacion`;
  private apiUrl2 = `${environment.API_URL}/usuario/obtener`;
  tokenSubject = new Subject<string>();
  private user = new BehaviorSubject<Login | null>(null);

  constructor(
    private http: HttpClient,
    private tokenSerive: TokenService,
    private toasterSerive: ToastrService,
    private router: Router
  ) { }

  login(username: string, password: string) {
    return this.http.post<Auth>(this.apiUrl, {user: username, pass: password})
      .pipe(
        tap(response => {
          this.tokenSerive.saveToken(response.jwt);
        })
      );
  }

  async getAuthentication(body: Login) {

    try {
      let response = this.http.post<object>(this.apiUrl, body);
      const r: any = await response.toPromise();
      if (r) {
        this.tokenSerive.saveToken(r.token);
        const user = await this.http.get<Usuario>(`${this.apiUrl2}/${r.username}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          }
        }).toPromise();
        console.log(user)
        const nameRol = `${user.role?.name}`;
        localStorage.setItem('rol', nameRol);
        this.toasterSerive.success('Exitosamente', 'Usuario Registrado')
      }
      return response;
    } catch(Error) {
      this.toasterSerive.error('Error', 'Credenciales Incorrectas');
      return ;
    }
  }

  setToken(token: string) {
    this.tokenSubject.next(token);
  }


}
