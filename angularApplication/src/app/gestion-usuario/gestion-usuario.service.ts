import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class GestionUsuarioService {

  private apiUrl = `${environment.API_URL}/usuario`;

  constructor(
    private http: HttpClient
  ) { }

  getUsers() {
    return this.http.get<Usuario[]>(`${this.apiUrl}/all`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  createUser(usuario: Usuario) {
    return this.http.post<Usuario>(`${this.apiUrl}/`, usuario,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  updateUser(id: number, usuario: Usuario) {
    return this.http.put<Usuario>(`${this.apiUrl}/actualizar/${id}`, usuario,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  deleteUser(id: number) {
    return this.http.delete<boolean>(`${this.apiUrl}/delete/${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }
}
