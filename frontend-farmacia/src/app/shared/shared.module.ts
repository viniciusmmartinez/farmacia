import { NgModule } from "@angular/core";
import { AlertModelComponent } from './alert-model/alert-model.component';
import { CommonModule } from '@angular/common';

@NgModule({
    imports:[
        CommonModule
    ],
    declarations:[AlertModelComponent],
    exports:[AlertModelComponent],
    entryComponents:[AlertModelComponent]
})
export class SharedModule{ }