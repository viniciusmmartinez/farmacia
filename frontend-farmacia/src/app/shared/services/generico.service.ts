import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Entity } from '../models/entity';
import { CrudHttpClientService } from 'src/app/arquitetura/shared/services/crud-http-client.service';

@Injectable()
export class GenericoService extends CrudHttpClientService<Entity<any>> {
	constructor(protected http: HttpClient) {
		super(``, http);
	}
}
