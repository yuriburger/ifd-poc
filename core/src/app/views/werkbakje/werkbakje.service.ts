import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Taak } from './taak';

@Injectable({
  providedIn: 'root'
})
export class WerkbakjeService {
  apiURL = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getTaken(): Observable<Taak[]> {
    return this.http.get<Taak[]>(`${this.apiURL}/werkbakje`);
  }

  getMijnTaken(): Observable<Taak[]> {
    return this.http.get<Taak[]>(`${this.apiURL}/werkbakje/mijn`);
  }

  assignTask(id: String): Observable<Taak> {
    return this.http.put<Taak>(
      `${this.apiURL}/werkbakje/` + id,
      '{"assignee" : "Yuri Burger"}',
      this.httpOptions
    );
  }

  completeTask(id: String): Observable<Taak> {
    return this.http.post<Taak>(
      `${this.apiURL}/werkbakje/` + id,
      '{"action" : "complete"}',
      this.httpOptions
    );
  }
}
