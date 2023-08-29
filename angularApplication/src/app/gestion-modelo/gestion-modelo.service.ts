import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Modelo } from '../models/modelo.model';

@Injectable({
  providedIn: 'root'
})
export class GestionModeloService {

  private apiUrl = `${environment.API_URL}/modelo`;

  constructor(
    private http: HttpClient
  ) { }

  getAllModel() {
    return this.http.get<Modelo[]>(`${this.apiUrl}/all`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  createModel(modelo: Modelo) {
    return this.http.post<Modelo>(`${this.apiUrl}/`, modelo,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  updateModel(id: number, modelo: Modelo) {
    return this.http.put<Modelo>(`${this.apiUrl}/actualizar/${id}`, modelo,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  deleteModel(id: number) {
    return this.http.delete(`${this.apiUrl}/delete/${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }
}
