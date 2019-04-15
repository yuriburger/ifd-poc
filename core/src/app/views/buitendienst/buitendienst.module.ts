import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BuitendienstComponent } from './buitendienst.component';
import { BuitendienstRoutingModule } from './buitendienst-routing.module';

@NgModule({
  declarations: [BuitendienstComponent],
  imports: [CommonModule, BuitendienstRoutingModule]
})
export class BuitendienstModule {}
