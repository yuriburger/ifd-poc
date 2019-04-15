import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Import } from './import';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FokkerService {
  apiURL = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getImports(): Observable<Import[]> {
    return this.http.get<Import[]>(`${this.apiURL}/import`);
  }

  newImportHond(name: string): Observable<Object> {
    return this.http.post(`${this.apiURL}/import`, { name: name });
  }
}
