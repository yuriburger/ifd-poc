import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { BuitendienstComponent } from './buitendienst.component';
import { BuitendienstRoutingModule } from './buitendienst-routing.module';

@NgModule({
  declarations: [BuitendienstComponent],
  imports: [CommonModule, FormsModule, BuitendienstRoutingModule]
})
export class BuitendienstModule {}
