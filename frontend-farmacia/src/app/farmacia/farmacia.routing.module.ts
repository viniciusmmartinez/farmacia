import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FarmaciaComponent } from './farmacia.component';

const farmaciaRoutes: Routes = [
	{
		path: 'farmacia',
		component: FarmaciaComponent
	}
];

@NgModule({
	imports: [RouterModule.forChild(farmaciaRoutes)],
	exports: [RouterModule]
})
export class FarmaciaRoutingModule { }
