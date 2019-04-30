import { Component, OnInit } from '@angular/core';
import { FokkerService } from './fokker.service';
import { Import } from './import';

@Component({
  selector: 'app-fokker',
  templateUrl: './fokker.component.html'
})
export class FokkerComponent implements OnInit {
  hondName: string;
  imports: Import[];

  constructor(private fokkerService: FokkerService) {}

  ngOnInit() {
    this.loadImports();
  }

  loadImports() {
    this.fokkerService.getImports().subscribe(response => {
      this.imports = response.map(item => {
        return new Import(item.id, item.name, item.reason, item.status);
      });
    });
  }

  onImportHond() {
    this.fokkerService
      .newImportHond(this.hondName)
      .subscribe(hond =>
        this.imports.push(
          new Import(hond['id'], hond['name'], hond['reason'], hond['status'])
        )
      );
    this.hondName = '';
  }
}
