import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Farmacia } from '../../models/farmacia/farmacia';
import { CrudHttpClientService } from 'src/app/arquitetura/shared/services/crud-http-client.service';

@Injectable({
  providedIn: 'root'
})
export class FarmaciaService extends CrudHttpClientService<Farmacia>{
	constructor(protected http: HttpClient) {
		super('', http);
	}
}
