import { Component, OnInit, Input } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-alert-model',
  templateUrl: './alert-model.component.html',
  styleUrls: ['./alert-model.component.css']
})
export class AlertModelComponent implements OnInit {

  @Input() message: string;
  @Input() type: 'success';
  
  constructor(public bsModalRef: BsModalRef) { }

  ngOnInit() {
  }

  onClose(){
    this.bsModalRef.hide();
  }
}
