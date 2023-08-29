import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Fabricante } from '../models/fabricante.model';

@Injectable({
  providedIn: 'root'
})
export class GestionFabricanteService {

  private apiUrl: string = `${environment.API_URL}/fabricante`;

  constructor(
    private http: HttpClient
  ) { }

  getAllFactory() {
    return this.http.get<Fabricante[]>(`${this.apiUrl}/all`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  createFactory(fabricante: Fabricante) {
    return this.http.post<Fabricante>(`${this.apiUrl}/`, fabricante,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  updateFactory(id: number, fabricante: Fabricante) {
    return this.http.put<Fabricante>(`${this.apiUrl}/actualizar/${id}`, fabricante,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }

  deleteFactory(id: number) {
    return this.http.delete<boolean>(`${this.apiUrl}/delete/${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        }
      });
  }
}
