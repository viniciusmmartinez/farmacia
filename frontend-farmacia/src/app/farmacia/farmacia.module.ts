import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FarmaciaRoutingModule } from './farmacia.routing.module';
import { FarmaciaComponent } from './farmacia.component';

@NgModule({
	imports: [
		RouterModule,
		FarmaciaRoutingModule
	],
	declarations: [
		FarmaciaComponent
	]
})
export class FarmaciaModule { }
