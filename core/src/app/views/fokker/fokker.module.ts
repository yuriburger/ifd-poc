import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FokkerComponent } from './fokker.component';
import { FokkerRoutingModule } from './fokker-routing.module';

@NgModule({
  declarations: [FokkerComponent],
  imports: [CommonModule, FormsModule, FokkerRoutingModule]
})
export class FokkerModule {}
