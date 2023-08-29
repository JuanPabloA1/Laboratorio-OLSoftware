import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Role } from '../models/role.model';

@Injectable({
  providedIn: 'root'
})
export class GestionRolService {

  private apiUrl = `${environment.API_URL}/rol`;

  constructor(
    private http: HttpClient
  ) { }

  getAllRoles() {
    return this.http.get<Role[]>(`${this.apiUrl}/all`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  createRole(role: Role) {
    return this.http.post<Role>(`${this.apiUrl}/`, role,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  updateRole(id: number, role: Role) {
    return this.http.put<Role>(`${this.apiUrl}/actualizar/${id}`, role,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  deleteRole(id: number) {
    return this.http.delete(`${this.apiUrl}/delete/${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }
}
