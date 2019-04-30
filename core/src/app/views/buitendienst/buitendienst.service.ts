import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Order } from './order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuitendienstService {
  apiURL = 'http://localhost:8082';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiURL}/orders`);
  }

  newOrder(numberOfItems: number): Observable<Object> {
    return this.http.post(`${this.apiURL}/orders`, {
      numberOfItems: numberOfItems
    });
  }
}
