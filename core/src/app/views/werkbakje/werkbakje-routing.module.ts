import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WerkbakjeComponent } from './werkbakje.component';

const routes: Routes = [
  {
    path: '',
    component: WerkbakjeComponent,
    data: {
      title: 'Werkbakje'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WerkbakjeRoutingModule {}
