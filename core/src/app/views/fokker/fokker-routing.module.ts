import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FokkerComponent } from './fokker.component';

const routes: Routes = [
  {
    path: '',
    component: FokkerComponent,
    data: {
      title: 'Fokker'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FokkerRoutingModule {}
