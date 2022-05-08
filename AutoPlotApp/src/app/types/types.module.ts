import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TypesComponent } from './types.component';
import { SharedModule } from '../shared';
import { TypesRoutingModule } from './types-routing.module';
import {AgGridModule} from 'ag-grid-angular';
import 'ag-grid-enterprise';

@NgModule({
  imports: [
    SharedModule,
    TypesRoutingModule,
    AgGridModule.withComponents([]),
  ],
  declarations: [
    TypesComponent
  ]
})
export class TypesModule {}
