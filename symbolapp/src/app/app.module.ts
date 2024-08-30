import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { SymbolsComponent } from './symbols/symbols.component';
import { SymbolDetailComponent } from './symbol-detail/symbol-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    SymbolsComponent,
    SymbolDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  schemas: [

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
