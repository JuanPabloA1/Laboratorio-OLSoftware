import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Area } from '../models/area.model';

@Injectable({
  providedIn: 'root'
})
export class GestionAreasService {

  private apiUrl = `${environment.API_URL}/area`;

  constructor(
    private http: HttpClient
  ) { }

  getAllAreas() {
    return this.http.get<Area[]>(`${this.apiUrl}/all`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  getArea(id: string) {
    return this.http.get<Area>(`${this.apiUrl}${id}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  createArea(area: Area) {
    return this.http.post<Area>(`${this.apiUrl}/`, area,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  updateArea(id: string, area: Area) {
    return this.http.put<Area>(`${this.apiUrl}/actualizar/${id}`, area,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  deleteArea(id: string) {
    return this.http.delete<object>(`${this.apiUrl}/delete/${id}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
        "Content-Type": "application/json",
      },

    });
  }
}
