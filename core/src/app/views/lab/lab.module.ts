import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LabComponent } from './lab.component';
import { LabRoutingModule } from './lab-routing.module';

@NgModule({
  declarations: [LabComponent],
  imports: [CommonModule, LabRoutingModule]
})
export class LabModule {}
