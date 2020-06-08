
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class HttpClientService {
	private static nomeSistema: string = '';
	static urlBase: string = '';

	public static setNomeSistema(nomeSistema: string) {
		HttpClientService.nomeSistema = nomeSistema;
	}

	/**
	 * Recupera o URL padrão para acesso a API de acordo com o ambiente
	 */
	static getBackendUrl(): string {
		if (HttpClientService.urlBase.length == 0) {
			let url: string = window.location.href;
			let sufixo: string = '/' + HttpClientService.nomeSistema.toLowerCase() + '/api/';

			if (environment.backendUrlFromFrontend) {
				let index: number = url.toUpperCase().indexOf('/' + HttpClientService.nomeSistema + '/');
				if (index >= 0) {
					HttpClientService.urlBase = url.substring(0, index) + sufixo;
				}
			}
			else {
				if (environment.backendHttps) {
					HttpClientService.urlBase = 'https';
				}
				else {
					HttpClientService.urlBase = 'http';
				}
				HttpClientService.urlBase = HttpClientService.urlBase + environment.url +
					environment.backendPort + sufixo;
			}
		}

		return HttpClientService.urlBase;
	}

	public queryParams: any = null;

	constructor(
		public url: string,
		protected http: HttpClient,
		private withCredentials: boolean = true
	) {
		// Define o endpoint
		this.url = HttpClientService.getBackendUrl() + url;
	}

	protected options(
		options?: {
			headers?: HttpHeaders;
			observe?: 'body';
			params?: HttpParams;
			reportProgress?: boolean;
			responseType?: any;
			withCredentials?: boolean;
		}
	) {
		if (!options) {
			options = {};
		}

		let headers: HttpHeaders = new HttpHeaders();
		if (options.headers) {
			for (const headerName of options.headers.keys()) {
				headers = headers.set(headerName, options.headers.getAll(headerName));
			}
		}
		options.headers = headers;

		if (!options.responseType) {
			options.responseType = 'json';
		}

		if (!options.withCredentials) {
			options.withCredentials = this.withCredentials;
		}

		return options;
	}

	protected logError(error: any) {
		if (error.status === 0) {
			console.log('Backend indisponível  [' + HttpClientService.getBackendUrl() + ']');
		}
	}
}
