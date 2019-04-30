import { Component, OnInit } from '@angular/core';
import { LabService } from './lab.service';
import { Sample } from './sample';

@Component({
  selector: 'app-lab',
  templateUrl: './lab.component.html'
})
export class LabComponent implements OnInit {
  refreshTime: string;
  samples: Sample[];

  constructor(private labService: LabService) {}

  ngOnInit() {
    this.refreshTime = Date.now().toString();
    this.loadSamples();
  }

  loadSamples() {
    this.labService.getSamples().subscribe(response => {
      this.samples = response['data'];
    });
  }

  onPrepareOrder() {
    this.labService.onPrepareOrder().subscribe(response => {
      this.refreshTime = Date.now().toString();
      this.loadSamples();
    });
  }

  onComplete(id: String) {
    this.labService.completeTask(id).subscribe(response => {
      this.loadSamples();
    });
  }
}
