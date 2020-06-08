import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
		path: 'farmacia',
		loadChildren: './farmacia/farmacia.module#FarmaciaModule',
	},
  { path: '**', pathMatch: 'full', redirectTo: '/farmacia' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
