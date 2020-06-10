import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';
import { NgxPaginationModule } from 'ngx-pagination';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import {InputMaskModule} from 'primeng/inputmask';
import { MatCardModule } from '@angular/material/card';

import { FarmaciaRoutingModule } from './farmacia.routing.module';
import { FarmaciaComponent } from './farmacia.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
	imports: [
		MatCardModule,
		CommonModule,
		RouterModule,
		PaginatorModule,
		NgxPaginationModule,
		FarmaciaRoutingModule,
		TableModule,
		InputMaskModule,
		FormsModule,
		ReactiveFormsModule,
	],
	declarations: [
		FarmaciaComponent
	]
})
export class FarmaciaModule { }
