import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WerkbakjeComponent } from './werkbakje.component';
import { WerkbakjeRoutingModule } from './werkbakje-routing.module';

@NgModule({
  declarations: [WerkbakjeComponent],
  imports: [CommonModule, WerkbakjeRoutingModule]
})
export class WerkbakjeModule {}
