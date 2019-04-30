import { Component, OnInit } from '@angular/core';
import { Order } from '../buitendienst/order';
import { BuitendienstService } from './buitendienst.service';

@Component({
  selector: 'app-buitendienst',
  templateUrl: './buitendienst.component.html'
})
export class BuitendienstComponent implements OnInit {
  numberOfItems: string;
  orders: Order[];

  constructor(private buitendienstService: BuitendienstService) {}

  ngOnInit() {
    this.loadOrders();
  }

  loadOrders() {
    this.buitendienstService.getOrders().subscribe(response => {
      this.orders = response.map(item => {
        return new Order(item.id, item.numberOfItems, item.status);
      });
    });
  }

  onNewOrder() {
    this.buitendienstService
      .newOrder(+this.numberOfItems)
      .subscribe(order =>
        this.orders.push(
          new Order(order['id'], order['numberOfItems'], 'Opdracht klaargezet')
        )
      );
    this.numberOfItems = '';
  }
}
