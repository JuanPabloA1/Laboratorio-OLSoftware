import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Dispositivo } from '../models/dispositivo.model';

@Injectable({
  providedIn: 'root'
})
export class GestionDispositivoService {

  private apiUrl: string = `${environment.API_URL}/dispositivo`;

  constructor(
    private http: HttpClient
  ) { }

  getAllDevices() {
    return this.http.get<Dispositivo[]>(`${this.apiUrl}/all`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  createDevice(device: Dispositivo) {
    return this.http.post<Dispositivo>(`${this.apiUrl}/`, device,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  updateDevice(id: number, device: Dispositivo) {
    return this.http.put<Dispositivo>(`${this.apiUrl}/actualizar/${id}`, device,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    });
  }

  deleteDevice(id: number) {
    return this.http.delete<Dispositivo>(`${this.apiUrl}/delete/${id}`,
    {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }
    })
  }

}
