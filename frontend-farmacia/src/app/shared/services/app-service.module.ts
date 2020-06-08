import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FarmaciaService } from './farmacia/farmacia.service';

@NgModule({
	imports: [
		CommonModule
	],
	declarations: []
})
export class AppServiceModule {
	/**
   * Convção usada para que o módulo 'app' disponibilize as instâncias 'providers'
	 * como singleton para todos os modulos da aplicação.
   */
	static forRoot(): ModuleWithProviders {
		return {
			ngModule: AppServiceModule,
			providers: [
				FarmaciaService
			]
		};
	}
}
