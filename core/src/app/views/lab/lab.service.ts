import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sample } from './sample';

@Injectable({
  providedIn: 'root'
})
export class LabService {
  apiURL = 'http://localhost:8082';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getSamples(): Observable<Sample[]> {
    return this.http.get<Sample[]>(`${this.apiURL}/lab`);
  }

  onPrepareOrder(): Observable<Object> {
    return this.http.put(`${this.apiURL}/lab/prepare`, {});
  }

  completeTask(id: String): Observable<Sample> {
    return this.http.post<Sample>(
      `${this.apiURL}/lab/` + id,
      '{"action" : "complete"}',
      this.httpOptions
    );
  }
}
