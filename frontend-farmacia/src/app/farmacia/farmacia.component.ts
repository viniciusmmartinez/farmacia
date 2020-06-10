import { Component, OnInit } from '@angular/core';
import { Farmacia } from '../shared/models/farmacia/farmacia';
import { FarmaciaService } from '../shared/services/farmacia/farmacia.service';
import { BasePageableComponent } from '../shared/components/base-pageable.component';
import { FiltroPaginado } from '../shared/models/filtro-paginado';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { AlertModelComponent } from '../shared/alert-model/alert-model.component';

@Component({
  selector: 'app-farmacia',
  templateUrl: './farmacia.component.html',
  styleUrls: ['./farmacia.component.css']
})
export class FarmaciaComponent extends BasePageableComponent  implements OnInit {

  bsModalRef: BsModalRef;
  farmacia: Farmacia;
  search:string;
  isCadastro:boolean = false;
  itens: [];

  constructor(
    private service:FarmaciaService,
    private modalService: BsModalService) {
      super();
      this.farmacia = new Farmacia();
  }

  ngOnInit() {
  }

  consultarPaginado(): void {
    let objetoDTO:Farmacia = new Farmacia();
    objetoDTO.name = this.search;

    let filtro: FiltroPaginado = this.getFiltroPaginado(objetoDTO);

		this.searchPageble(this.service.consultarPaginado(filtro)).subscribe(
		  obj => {
        this.itens = obj;
        this.isCadastro = false;
		  },
		  err => {
        console.log("Erro");
      }
		);
		}
		

  public cadastrarLocal(){
    this.isCadastro = true;
  }
  public cadastrar(){
    this.service.post(this.farmacia).subscribe(
      (obj) => 
      {
        this.isCadastro = false;
        this.farmacia = new Farmacia();
        this.handleSucess();
      },
      error => {
        this.handleError();
      });
  }

  handleSucess(){
    this.bsModalRef = this.modalService.show(AlertModelComponent);
    this.bsModalRef.content.message = "Cadastrado efetuado com sucesso";
  }
  
  handleError(){
    this.bsModalRef = this.modalService.show(AlertModelComponent);
    this.bsModalRef.content.type = 'danger';
    this.bsModalRef.content.message = "Ocorreu um erro ao cadastrar";
  }

}
