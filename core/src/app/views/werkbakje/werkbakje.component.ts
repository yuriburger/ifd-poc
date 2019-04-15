import { Component, OnInit } from '@angular/core';
import { WerkbakjeService } from './werkbakje.service';
import { Taak } from './taak';

@Component({
  selector: 'app-werkbakje',
  templateUrl: './werkbakje.component.html'
})
export class WerkbakjeComponent implements OnInit {
  taken: Taak[];
  mijnTaken: Taak[];

  constructor(private werkbakjeService: WerkbakjeService) {}

  ngOnInit() {
    this.loadTaken();
    this.loadMijnTaken();
  }

  loadTaken() {
    this.werkbakjeService.getTaken().subscribe(response => {
      this.taken = response['data'];
    });
  }

  loadMijnTaken() {
    this.werkbakjeService.getMijnTaken().subscribe(response => {
      this.mijnTaken = response['data'];
    });
  }

  onAssign(id: String) {
    this.werkbakjeService.assignTask(id).subscribe(response => {
      this.loadTaken();
      this.loadMijnTaken();
    });
  }

  onComplete(id: String) {
    this.werkbakjeService.completeTask(id).subscribe(response => {
      this.loadTaken();
      this.loadMijnTaken();
    });
  }
}
