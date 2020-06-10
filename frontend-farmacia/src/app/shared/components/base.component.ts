import { HttpErrorResponse } from '@angular/common/http';
import { AbstractControl, FormControl, FormGroup } from '@angular/forms';

export class BaseComponent {

  public home: any = { icon: 'pi pi-home', url: '#' };

  constructor(
  ) {
  }

  /**
	* Trata as mensagens de erro da API
	* @param response
	*/

  getClone(object: any): any {
    return JSON.parse(JSON.stringify(object));
  }


  validaSeCampoLength(formulario: FormGroup, nomeCampo: string, minLength: number): boolean {
    const campo: AbstractControl = formulario.get(nomeCampo);
    const value: string = campo.value + '';
    return (campo.dirty || campo.touched)
      && (value && value.trim().length < minLength);
  }


  validaSeCheckboxValido(formulario: FormGroup, nomeCampo: string, lista: Array<any>): boolean {
    const campo: AbstractControl = formulario.get(nomeCampo);
    return (campo.dirty || campo.touched) && lista.length === 0;
  }

  validaSeCheckboxValidoList(touched: boolean, lista: Array<any>): boolean {
    return touched && lista.length === 0;
  }

  onChangeCheckbox(value: any, isChecked: boolean, list: Array<any>): void {
    if (isChecked) {
      list.push(value);
    } else {
      const index = list.findIndex(x => this.equals(x, value));
      if (index !== -1) {
        list.splice(index, 1);
      }
    }
  }

  showPaginacao(lista: Array<any>): boolean {
    return lista && lista.length > 0;
  }

  findIndex(list: any[], value?: any, lambda?: (x: any) => boolean): number {
    let index = -1;
    if (lambda) {
      index = list.findIndex(lambda);
    } else {
      index = list.findIndex(x => this.equals(x, value));
    }
    return index;
  }

  contains(list: any[], value?: any, lambda?: (x: any) => boolean): boolean {
    let index = -1;
    if (lambda) {
      index = list.findIndex(lambda);
    } else {
      index = list.findIndex(x => this.equals(x, value));
    }
    return index !== -1;
  }

  equals(objectA: any, objectB: any): boolean {
    if (typeof objectA !== typeof objectB) {
      return false;
    }
    if (objectA === null && objectB === null) {
      return true;
    }
    const aKeys = objectA !== null && typeof objectA !== 'string' ? Object.keys(objectA) : [],
      bKeys = objectB !== null && typeof objectB !== 'string' ? Object.keys(objectB) : [];
    if (aKeys.length !== bKeys.length) {
      return false;
    }
    if (aKeys.length === 0) {
      return (objectA === objectB
        || (typeof objectA === 'object' || typeof objectB === 'object'));
    }

    const areDifferent = aKeys.some((key) => {
      return !this.equals(objectA[key], objectB[key]);
    });
    return !areDifferent;
  }


  idRow(values: string[]): string {
    let id = 'row_';
    values.forEach(value => {
      id += value + '_';
    });
    return id.substring(0, id.length - 1);
  }

  idField(values: string[]): string {
    let idField = 'field_';
    values.forEach(value => {
      idField += value + '_';
    });
    return idField.substring(0, idField.length - 1);
  }


  idHashtag(id: string): string {
    return id ? '#' + id : '';
  }

  concatenarComTraco(values: any[]): string {
    let retorno = '';
    values.forEach(value => {
      retorno += value + ' - ';
    });
    return retorno.substring(0, retorno.length - 3);
  }

  getMessageError(error: any, messageDefault: string): string {
    return typeof error.error !== 'string' ? messageDefault : error.error;
  }

  getItemList(list: any[], position: number): any {
    return list.length > 0 ? list[position] : null;
  }

  // INICIO - validação formulario
  validarFormulario(formulario: FormGroup): boolean {
    return this.validarFormularioAll(formulario, []);
  }
  
  validarFormularioAll(formulario: FormGroup, lists: any[]): boolean {
    const keys = Object.keys(formulario.value);
    for (let index = 0; index < keys.length; index++) {
      const field: AbstractControl = formulario.get(keys[index]);
      const value = field.value;
      if (value && typeof value === 'string') {
        field.setValue(value.trim());
      }
      if (field.invalid) {
        return false;
      }
    }
    for (let index = 0; index < lists.length; index++) {
      if (lists[index].length === 0) {
        return false;
      }
    }
    return formulario.valid;
  }

  protected forceValidateAllFormFields(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.forceValidateAllFormFields(control);
      }
    });
  }

  protected limparTodoFormulario(formGroup: FormGroup): void {
    formGroup.reset();
    formGroup.markAsPristine();
    formGroup.markAsUntouched();
    formGroup.updateValueAndValidity();
  }

  protected limparValidacoesFormulario(formGroup: FormGroup): void {
    formGroup.markAsPristine();
    formGroup.markAsUntouched();
    formGroup.updateValueAndValidity();
  }


}
