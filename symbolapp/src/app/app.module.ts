import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { SymbolsComponent } from './symbols/symbols.component';
import { SymbolDetailComponent } from './symbol-detail/symbol-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { FiltermessagePipe } from './filtermessage.pipe';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    SymbolsComponent,
    SymbolDetailComponent,
    MessagesComponent,
    FiltermessagePipe
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  schemas: [

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
