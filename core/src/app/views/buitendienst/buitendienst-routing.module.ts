import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BuitendienstComponent } from './buitendienst.component';

const routes: Routes = [
  {
    path: '',
    component: BuitendienstComponent,
    data: {
      title: 'Buitendienst'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BuitendienstRoutingModule {}
