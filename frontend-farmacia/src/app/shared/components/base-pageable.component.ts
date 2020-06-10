import { ObserveOnSubscriber } from "rxjs/internal/operators/observeOn";
import { Observable, Subject } from "rxjs";

import { BaseComponent } from './base.component';
import { Pageable } from '../models/pageable';
import { FiltroPaginado } from '../models/filtro-paginado';

export abstract class BasePageableComponent extends BaseComponent {
    public totalRegistros: number = 0;
    public rowsPerPage: number = 4;
    public pageOptions: number[] = [10, 20, 30];
    public loading = false;
    public filtroPaginado: FiltroPaginado = new FiltroPaginado();

    constructor() {
        super();
    }

    abstract consultarPaginado(): void;

    protected getFiltroPaginado(filtro: any): FiltroPaginado {
        this.filtroPaginado.filtro = filtro;
        return this.filtroPaginado;
    }

    protected searchPageble(observable: Observable<any>): Observable<any> {
        let sub = new Subject();

        observable.subscribe(
            (pageable: any) => {
                this.totalRegistros = pageable.totalElements;
                sub.next(pageable.content);
            },
            error => { sub.error(error) 
            });

        return sub;
    }

    protected paginar(event: any) {
        this.filtroPaginado.pageNumber = event.page;
        this.filtroPaginado.pageSize = event.rows;
        this.consultarPaginado();
    }

    protected ordenarPaginado(event: any): any {
        this.filtroPaginado.orderField = event.sortField;
        this.filtroPaginado.order = event.sortOrder;
		this.consultarPaginado();

        return null;
    }
}
