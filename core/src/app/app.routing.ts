import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DefaultLayoutComponent } from './containers';
import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'buitendienst',
        loadChildren:
          './views/buitendienst/buitendienst.module#BuitendienstModule'
      },
      {
        path: 'fokker',
        loadChildren: './views/fokker/fokker.module#FokkerModule'
      },
      {
        path: 'werkbakje',
        loadChildren: './views/werkbakje/werkbakje.module#WerkbakjeModule'
      },
      {
        path: 'lab',
        loadChildren: './views/lab/lab.module#LabModule'
      }
    ]
  },
  { path: '**', component: P404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
