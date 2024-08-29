import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SymbolsComponent } from './symbols/symbols.component';


const routes: Routes = [
  { path: 'first-component', component: SymbolsComponent },  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
