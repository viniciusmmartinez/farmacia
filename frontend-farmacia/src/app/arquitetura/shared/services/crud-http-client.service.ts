import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { HttpClientService } from './http-client.service';
import { Entity } from 'src/app/shared/models/entity';
import { FiltroPaginado } from 'src/app/shared/models/filtro-paginado';


export class CrudHttpClientService<T extends Entity<any>> extends HttpClientService {
	constructor(public uri: string, protected http: HttpClient) {
		super(uri, http);
	}

	/**
	 * Recupera um registro em particular, ou todos (caso não seja passado
	 * o parâmetro id)
	 * @param id
	 */
	public get(id?: any): Observable<T|T[]> {
		let url = this.url;

		if (id) {
				url += '/' + id;
		}

		return this.http.get<T>(url, this.options());
	}

	/**
	 * Insere um registro
	 * @param entity
	 */
	public post(entity: T): Observable<T> {
		return this.http.post<T>(this.url, entity, this.options());
	}

	/**
	 * Altera um registro
	 * @param entity
	 */
	public put(entity: T): Observable<T> {
		return this.http.put<T>(this.url, entity, this.options());
	}

	/**
	 * Altera um registro
	 * @param entity
	 */
	public excluir(uri, entity: T): Observable<T> {
		return this.http.put<T>(this.url+uri, entity, this.options());
	}

	/**
	 * Exclui um registro
	 * @param id
	 */
	public delete(id: number): Observable<boolean> {
		return this.http.delete<boolean>(this.url + '/' + id, this.options());
	}

	/**
	 * Consultar com paginação
	 */
	public consultarPaginado(filtro: FiltroPaginado): Observable<any> {
		return this.http.get(this.url + '/search?searchTerm='+filtro.filtro.name+'&page='+filtro.pageNumber +'&size='+filtro.pageSize, this.options());
	}

}
